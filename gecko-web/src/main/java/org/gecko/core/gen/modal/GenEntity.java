package org.gecko.core.gen.modal;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 实体对象
 *
 * @author: dengzhi
 * @date: 2018/5/31
 */
@Data
public class GenEntity {

    /**
     * 数据源限定词
     */
    private String dbQualifier;
    /**
     * 表名
     */
    @NotBlank
    private String tableName;
    /**
     * 实体名
     */
    @NotBlank
    private String name;
    /**
     * 注释
     */
    private String comment;
    /**
     * 支持导入
     */
    private Boolean supportImport;
    /**
     * 支持导出
     */
    private Boolean supportExport;
    /**
     * 支持分页
     */
    private Boolean supportPagination;
    /**
     * 支持记录操作日志
     */
    private Boolean supportLog;
    /**
     * 支持新增
     */
    private Boolean supportAdd;
    /**
     * 支持编辑
     */
    private Boolean supportEdit;
    /**
     * 支持明细
     */
    private Boolean supportDetail;
    /**
     * 支持列表
     */
    private Boolean supportList;
    /**
     * 支持删除
     */
    private Boolean supportDelete;
    /**
     * 字段集类型:NONE-不分组,FIELD_SET-字段集,TAB-选项卡
     */
    private String fieldGroupType;
    /**
     * 所有字段组
     */
    private List<GenFieldGroup> fieldGroups;
    /**
     * 主键
     */
    private List<GenField> pkFields;
    /**
     * 所有字段
     */
    private List<GenField> fields;
    /**
     * 自定义操作
     */
    private List<GenOperation> operations;
    /**
     * 查询条件
     */
    private List<GenFieldCondition> fieldConditions;

}
