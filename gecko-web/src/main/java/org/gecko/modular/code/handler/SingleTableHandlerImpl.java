package org.gecko.modular.code.handler;

import com.google.common.base.CaseFormat;
import org.gecko.core.gen.converter.DataTypeConverter;
import org.gecko.core.gen.converter.WidgetTypeConverter;
import org.gecko.core.gen.enums.DataType;
import org.gecko.core.gen.enums.FieldGroupType;
import org.gecko.core.gen.enums.WidgetType;
import org.gecko.core.metadata.ColumnMetaData;
import org.gecko.core.metadata.PrimaryKeyMetaData;
import org.gecko.core.metadata.TableMetaData;
import org.gecko.modular.code.entity.Db;
import org.gecko.modular.code.entity.Field;
import org.gecko.modular.code.entity.Table;
import org.gecko.modular.code.service.IFieldService;
import org.gecko.modular.code.service.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 导入单个表实现
 *
 * @author: dengzhi
 * @date: 2018/6/4
 */
@Component
public class SingleTableHandlerImpl implements SingleTableHandler {

    @Autowired
    private ITableService tableService;
    @Autowired
    private IFieldService fieldService;
    @Autowired
    private DataTypeConverter dataTypeConverter;
    @Autowired
    private WidgetTypeConverter widgetTypeConverter;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void handle(Db db, String tablePrefix, TableMetaData tmd, List<ColumnMetaData> cmdList, List<PrimaryKeyMetaData> pkmdList) {
        Table table = convertTable(db, tablePrefix, tmd);
        tableService.insert(table);
        List<Field> fieldList = convertFields(table, cmdList, pkmdList);
        if (fieldList != null && !fieldList.isEmpty()) {
            fieldService.insertBatch(fieldList);
        }
    }

    protected Table convertTable(Db db, String tablePrefix, TableMetaData tmd) {
        String tableName = tmd.getTableName();
        String entityName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL,
                tablePrefix != null && tableName.startsWith(tablePrefix) ?
                        tableName.substring(tablePrefix.length()) : tableName);
        Table table = new Table();
        table.setDbId(db.getId());
        table.setProjectId(db.getProjectId());
        table.setTableName(tableName);
        table.setEntityName(entityName);
        table.setTitle(StringUtils.hasText(tmd.getRemarks()) ? tmd.getRemarks() : entityName);
        table.setFieldGroupType(FieldGroupType.NONE.name());
        return table;
    }

    protected List<Field> convertFields(Table table, List<ColumnMetaData> cmdList, List<PrimaryKeyMetaData> pkmdList) {
        if (cmdList != null && !cmdList.isEmpty()) {
            List<Field> fieldList = new ArrayList<>(cmdList.size());
            Field field;
            ColumnMetaData cmd;
            for (int i = 0; i < cmdList.size(); i++) {
                cmd = cmdList.get(i);
                field = new Field();
                field.setTableId(table.getId());
                field.setColumnName(cmd.getColumnName());
                field.setSortNum(i);
                field.setName(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, cmd.getColumnName()));
                field.setRequired(cmd.getNullable() == 0);
                field.setTitle(StringUtils.hasText(cmd.getRemarks()) ? cmd.getRemarks() : field.getName());
                field.setColumnType(cmd.getTypeName());
                DataType dataType = dataTypeConverter.convert(cmd);
                field.setDataType(dataType.name());
                WidgetType widgetType = widgetTypeConverter.convert(dataType);
                field.setWidgetType(widgetType.name());
                fieldList.add(field);
            }
            //设置主键标识
            if (pkmdList != null && !pkmdList.isEmpty()) {
                Set<String> pkNames = pkmdList.stream().map(pk -> pk.getColumnName()).collect(Collectors.toSet());
                fieldList.stream().forEach(f -> f.setPrimaryKey(pkNames.contains(f.getColumnName())));
            }
            return fieldList;
        } else {
            return null;
        }
    }

}
