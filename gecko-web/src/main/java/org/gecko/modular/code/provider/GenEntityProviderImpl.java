package org.gecko.modular.code.provider;

import com.baomidou.mybatisplus.mapper.Condition;
import org.gecko.core.gen.enums.DataType;
import org.gecko.core.gen.enums.FieldGroupType;
import org.gecko.core.gen.modal.GenEntity;
import org.gecko.core.gen.modal.GenField;
import org.gecko.core.gen.modal.GenFieldCondition;
import org.gecko.core.gen.modal.GenFieldGroup;
import org.gecko.modular.code.entity.*;
import org.gecko.modular.code.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: dengzhi
 * @date: 2018/6/6
 */
@Component
public class GenEntityProviderImpl implements GenEntityProvider {

    @Autowired
    private IDbService dbService;
    @Autowired
    private ITableService tableService;
    @Autowired
    private IFieldService fieldService;
    @Autowired
    private IFieldGroupService fieldGroupService;
    @Autowired
    private IFieldConditionService fieldConditionService;

    @Override
    public List<GenEntity> getGenEntityList(Long projectId, List<Long> tableIds) {
        List<Table> tableList = tableService.selectList(Condition.create()
                .eq(Db.PROJECT_ID, projectId)
                .in(Db.ID, tableIds));
        if (tableList != null && !tableList.isEmpty()) {
            List<GenEntity> result = new ArrayList<>(tableList.size());
            List<Db> dbList = dbService.selectList(Condition.create().eq(Db.PROJECT_ID, projectId));
            Map<Long, String> dbQualifierMap = dbList.stream().collect(Collectors.toMap(Db::getId, Db::getDbQualifier));
            for (Table table : tableList) {
                result.add(convertGenEntity(table, dbQualifierMap.get(table.getDbId())));
            }
            return result;
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    protected GenEntity convertGenEntity(Table table, String dbQualifier) {
        GenEntity genEntity = new GenEntity();
        genEntity.setDbQualifier(dbQualifier);
        genEntity.setTableName(table.getTableName());
        genEntity.setName(table.getEntityName());
        genEntity.setComment(table.getTitle());
        genEntity.setSupportAdd(table.getSupportAdd());
        genEntity.setSupportEdit(table.getSupportEdit());
        genEntity.setSupportList(table.getSupportList());
        genEntity.setSupportDelete(table.getSupportDelete());
        genEntity.setSupportDetail(table.getSupportDetail());
        genEntity.setSupportImport(table.getSupportImport());
        genEntity.setSupportExport(table.getSupportExport());
        genEntity.setSupportPagination(table.getSupportPagination());
        genEntity.setSupportLog(table.getSupportLog());
        genEntity.setFieldGroupType(table.getFieldGroupType());

        //是否支持字段分组
        boolean supportFieldGroup = table.getFieldGroupType() != null
                && !FieldGroupType.NONE.name().equals(table.getFieldGroupType());
        //字段分组
        GenFieldGroup defaultGenFieldGroup = new GenFieldGroup();
        defaultGenFieldGroup.setTitle("默认分组");
        defaultGenFieldGroup.setName("default");
        defaultGenFieldGroup.setFields(new LinkedList<>());
        Map<Long, GenFieldGroup> genFieldGroupMap = new HashMap<>();
        if (supportFieldGroup) {
            List<FieldGroup> fieldGroups = fieldGroupService.selectList(Condition.create()
                    .eq(FieldGroup.TABLE_ID, table.getId()));
            if (fieldGroups != null && !fieldGroups.isEmpty()) {
                GenFieldGroup genFieldGroup;
                for (FieldGroup fieldGroup : fieldGroups) {
                    genFieldGroup = new GenFieldGroup();
                    genFieldGroup.setTitle(fieldGroup.getTitle());
                    genFieldGroup.setSortNum(fieldGroup.getSortNum());
                    genFieldGroup.setFields(new LinkedList<>());
                    genFieldGroupMap.put(fieldGroup.getId(), genFieldGroup);
                }
            }
        }

        //查询条件
        List<FieldCondition> fieldConditions = fieldConditionService.selectList(Condition.create()
                .eq(FieldCondition.TABLE_ID, table.getId()));
        Map<Long, FieldCondition> fieldConditionMap = fieldConditions.stream()
                .collect(Collectors.toMap(FieldCondition::getId, Function.identity()));
        List<GenFieldCondition> genFieldConditions = new ArrayList<>(fieldConditions.size());

        //字段
        List<Field> fieldList = fieldService.selectList(Condition.create()
                .eq(Field.TABLE_ID, table.getId())
                .orderBy(Field.SORT_NUM));
        List<GenField> genFields = new ArrayList<>(fieldList.size());
        List<GenField> pkGenFields = new ArrayList<>(fieldList.size());
        Map<Long, GenField> genFieldMap = new HashMap<>(16);
        GenField genField;
        FieldCondition fieldCondition;
        GenFieldCondition genFieldCondition;
        for (Field field : fieldList) {
            genField = convertGenField(field);
            genFields.add(genField);
            genFieldMap.put(field.getId(), genField);
            if (field.getPrimaryKey()) {
                pkGenFields.add(genField);
            }
            //字段分组
            if (supportFieldGroup) {
                GenFieldGroup genFieldGroup = null;
                if (field.getFieldGroupId() != null) {
                    genFieldGroup = genFieldGroupMap.get(field.getFieldGroupId());
                }
                if (genFieldGroup == null) {
                    genFieldGroup = defaultGenFieldGroup;
                }
                genFieldGroup.getFields().add(genField);
            }
            //查询条件
            fieldCondition = fieldConditionMap.get(field.getId());
            if (fieldCondition != null) {
                genFieldCondition = new GenFieldCondition();
                genFieldCondition.setField(genField);
                genFieldCondition.setSymbol(fieldCondition.getSymbol());
                genFieldCondition.setSortNum(fieldCondition.getSortNum());
                genFieldConditions.add(genFieldCondition);
            }
        }
        genEntity.setFields(genFields);
        genEntity.setPkFields(pkGenFields);

        //设置字段分组
        if (supportFieldGroup) {
            List<GenFieldGroup> genFieldGroups = genFieldGroupMap.values()
                    .stream()
                    .filter(fs -> !fs.getFields().isEmpty())
                    .sorted(Comparator.comparing(GenFieldGroup::getSortNum))
                    .collect(Collectors.toCollection(LinkedList::new));
            if (!genFieldGroups.isEmpty()) {
                if (!defaultGenFieldGroup.getFields().isEmpty()) {
                    genFieldGroups.add(defaultGenFieldGroup);
                }
            }
            genEntity.setFieldGroups(genFieldGroups);
        } else {
            genEntity.setFieldGroups(Collections.EMPTY_LIST);
        }

        //设置查询条件
        if (!genFieldConditions.isEmpty()) {
            genEntity.setFieldConditions(genFieldConditions.stream()
                    .sorted(Comparator.comparing(GenFieldCondition::getSortNum))
                    .collect(Collectors.toList()));
        } else {
            genEntity.setFieldConditions(Collections.EMPTY_LIST);
        }

        return genEntity;
    }

    protected GenField convertGenField(Field field) {
        GenField genField = new GenField();
        genField.setColumnName(field.getColumnName());
        genField.setName(field.getName());
        genField.setDataType(DataType.valueOf(field.getDataType()));
        genField.setTitle(field.getTitle());
        genField.setDefaultValue(field.getDefaultValue());
        genField.setHelpTip(field.getHelpTip());
        genField.setDictName(field.getDictName());
        genField.setSupportDict(StringUtils.hasText(field.getDictName()));
        genField.setPrimaryKey(field.getPrimaryKey());
        genField.setWidgetType(field.getWidgetType());
        genField.setRequired(field.getRequired());
        return genField;
    }

}
