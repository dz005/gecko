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
 * 项目
 * </p>
 *
 * @author zhi.deng
 * @since 2018-06-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_project")
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 标题
     */
    private String title;
    /**
     * 拥有者
     */
    private Long owner;
    /**
     * 图标样式
     */
    @TableField("icon_class")
    private String iconClass;
    /**
     * 编码
     */
    private String encoded;
    /**
     * 描述
     */
    private String description;
    /**
     * code 包
     */
    @TableField("code_package")
    private String codePackage;
    /**
     * xml 相对路径
     */
    @TableField("xml_path")
    private String xmlPath;
    /**
     * js 相对路径
     */
    @TableField("js_path")
    private String jsPath;
    /**
     * html 相对路径
     */
    @TableField("html_path")
    private String htmlPath;
    /**
     * 数据库数量
     */
    @TableField("db_count")
    private Integer dbCount;
    /**
     * 表数量
     */
    @TableField("table_count")
    private Integer tableCount;
    /**
     * 模板数量
     */
    @TableField("template_count")
    private Integer templateCount;
    /**
     * 成员数量
     */
    @TableField("member_count")
    private Integer memberCount;


    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String TITLE = "title";

    public static final String OWNER = "owner";

    public static final String ICON_CLASS = "icon_class";

    public static final String ENCODED = "encoded";

    public static final String DESCRIPTION = "description";

    public static final String CODE_PACKAGE = "code_package";

    public static final String XML_PATH = "xml_path";

    public static final String JS_PATH = "js_path";

    public static final String HTML_PATH = "html_path";

    public static final String DB_COUNT = "db_count";

    public static final String TABLE_COUNT = "table_count";

    public static final String TEMPLATE_COUNT = "template_count";

    public static final String MEMBER_COUNT = "member_count";

}
