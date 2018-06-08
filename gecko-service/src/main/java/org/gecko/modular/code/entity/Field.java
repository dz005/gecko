package org.gecko.modular.code.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 字段
 * </p>
 *
 * @author zhi.deng
 * @since 2018-06-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_field")
public class Field implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 表ID
     */
    @TableField("table_id")
    private Long tableId;
    /**
     * 字段组ID
     */
    @TableField("field_group_id")
    private Long fieldGroupId;
    /**
     * 数据库字段名称
     */
    @TableField("column_name")
    private String columnName;
    /**
     * 数据库字段类型
     */
    @TableField("column_type")
    private String columnType;
    /**
     * 标题
     */
    private String title;
    /**
     * 名称
     */
    private String name;
    /**
     * 数据类型
     */
    @TableField("data_type")
    private String dataType;
    /**
     * 是否是主键
     */
    @TableField("primary_key")
    private Boolean primaryKey;
    /**
     * 是否能为空
     */
    private Boolean required;
    /**
     * 控件类型
     */
    @TableField("widget_type")
    private String widgetType;
    /**
     * 在新增页显示
     */
    @TableField("show_add")
    private Boolean showAdd;
    /**
     * 在编辑页显示
     */
    @TableField("show_edit")
    private Boolean showEdit;
    /**
     * 在编辑页上不可编辑
     */
    @TableField("disabled_edit")
    private Boolean disabledEdit;
    /**
     * 在明细页显示
     */
    @TableField("show_detail")
    private Boolean showDetail;
    /**
     * 在列表显示
     */
    @TableField("show_list")
    private Boolean showList;
    /**
     * 支持导入
     */
    @TableField("support_import")
    private Boolean supportImport;
    /**
     * 支持导出
     */
    @TableField("support_export")
    private Boolean supportExport;
    /**
     * 数据字典名称
     */
    @TableField("dict_name")
    private String dictName;
    /**
     * 帮助提示
     */
    @TableField("help_tip")
    private String helpTip;
    /**
     * 占位符
     */
    private String placeholder;
    /**
     * 默认值
     */
    @TableField("default_value")
    private String defaultValue;
    /**
     * 单位
     */
    private String unit;
    /**
     * 排序
     */
    @TableField("sort_num")
    private Integer sortNum;


    public static final String ID = "id";

    public static final String TABLE_ID = "table_id";

    public static final String FIELD_GROUP_ID = "field_group_id";

    public static final String COLUMN_NAME = "column_name";

    public static final String COLUMN_TYPE = "column_type";

    public static final String TITLE = "title";

    public static final String NAME = "name";

    public static final String DATA_TYPE = "data_type";

    public static final String PRIMARY_KEY = "primary_key";

    public static final String REQUIRED = "required";

    public static final String WIDGET_TYPE = "widget_type";

    public static final String SHOW_ADD = "show_add";

    public static final String SHOW_EDIT = "show_edit";

    public static final String DISABLED_EDIT = "disabled_edit";

    public static final String SHOW_DETAIL = "show_detail";

    public static final String SHOW_LIST = "show_list";

    public static final String SUPPORT_IMPORT = "support_import";

    public static final String SUPPORT_EXPORT = "support_export";

    public static final String DICT_NAME = "dict_name";

    public static final String HELP_TIP = "help_tip";

    public static final String PLACEHOLDER = "placeholder";

    public static final String DEFAULT_VALUE = "default_value";

    public static final String UNIT = "unit";

    public static final String SORT_NUM = "sort_num";

}
