package org.gecko.core.gen.modal;

import lombok.Data;
import org.gecko.core.gen.enums.DataType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 字段
 *
 * @author: dengzhi
 * @date: 2018/5/31
 */
@Data
public class GenField {

    /**
     * 数据库字段名
     */
    @NotBlank
    private String columnName;
    /**
     * 名称
     */
    @NotBlank
    private String name;
    /**
     * 标题
     */
    @NotBlank
    private String title;
    /**
     * 数据类型
     */
    @NotNull
    private DataType dataType;
    /**
     * 控件类型
     */
    @NotBlank
    private String widgetType;
    /**
     * 是否是主键
     */
    private Boolean primaryKey;
    /**
     * 是否能为空
     */
    private Boolean required;
    /**
     * 在新增页显示
     */
    private Boolean showAdd;
    /**
     * 在编辑页显示
     */
    private Boolean showEdit;
    /**
     * 在编辑页上不可编辑
     */
    private Boolean disabledEdit;
    /**
     * 在明细页显示
     */
    private Boolean showDetail;
    /**
     * 在列表显示
     */
    private Boolean showList;
    /**
     * 支持导入
     */
    private Boolean supportImport;
    /**
     * 支持导出
     */
    private Boolean supportExport;
    /**
     * 支持数据字典
     */
    private Boolean supportDict;
    /**
     * 数据字典名称
     */
    private String dictName;
    /**
     * 帮助提示
     */
    private String helpTip;
    /**
     * 默认值
     */
    private String defaultValue;
    /**
     * 单位
     */
    private String unit;
    /**
     * 字段值选项
     */
    private List<GenFieldValueOption> valueOptions;
    /**
     * 字段规则
     */
    private List<GenFieldRule> rules;

}
