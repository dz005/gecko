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
 * 自定义表
 * </p>
 *
 * @author zhi.deng
 * @since 2018-06-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_table")
public class Table implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 项目ID
     */
    @TableField("project_id")
    private Long projectId;
    /**
     * 数据ID
     */
    @TableField("db_id")
    private Long dbId;
    /**
     * 表名
     */
    @TableField("table_name")
    private String tableName;
    /**
     * 实体名称
     */
    @TableField("entity_name")
    private String entityName;
    /**
     * 标题
     */
    private String title;
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
     * 支持分页
     */
    @TableField("support_pagination")
    private Boolean supportPagination;
    /**
     * 支持记录操作日志
     */
    @TableField("support_log")
    private Boolean supportLog;
    /**
     * 支持新增
     */
    @TableField("support_add")
    private Boolean supportAdd;
    /**
     * 支持编辑
     */
    @TableField("support_edit")
    private Boolean supportEdit;
    /**
     * 支持明细
     */
    @TableField("support_detail")
    private Boolean supportDetail;
    /**
     * 支持列表
     */
    @TableField("support_list")
    private Boolean supportList;
    /**
     * 支持删除
     */
    @TableField("support_delete")
    private Boolean supportDelete;
    /**
     * 字段组类型
     */
    @TableField("field_group_type")
    private String fieldGroupType;


    public static final String ID = "id";

    public static final String PROJECT_ID = "project_id";

    public static final String DB_ID = "db_id";

    public static final String TABLE_NAME = "table_name";

    public static final String ENTITY_NAME = "entity_name";

    public static final String TITLE = "title";

    public static final String SUPPORT_IMPORT = "support_import";

    public static final String SUPPORT_EXPORT = "support_export";

    public static final String SUPPORT_PAGINATION = "support_pagination";

    public static final String SUPPORT_LOG = "support_log";

    public static final String SUPPORT_ADD = "support_add";

    public static final String SUPPORT_EDIT = "support_edit";

    public static final String SUPPORT_DETAIL = "support_detail";

    public static final String SUPPORT_LIST = "support_list";

    public static final String SUPPORT_DELETE = "support_delete";

    public static final String FIELD_GROUP_TYPE = "field_group_type";

}
