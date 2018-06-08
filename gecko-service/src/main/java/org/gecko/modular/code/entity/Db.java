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
 * 数据库
 * </p>
 *
 * @author zhi.deng
 * @since 2018-06-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_db")
public class Db implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("project_id")
    private Long projectId;
    /**
     * 标题
     */
    private String title;
    /**
     * 类型
     */
    private String type;
    private String url;
    private String username;
    private String password;
    /**
     * 表名前缀
     */
    @TableField("table_prefix")
    private String tablePrefix;
    /**
     * 数据源限定词
     */
    @TableField("db_qualifier")
    private String dbQualifier;


    public static final String ID = "id";

    public static final String PROJECT_ID = "project_id";

    public static final String TITLE = "title";

    public static final String TYPE = "type";

    public static final String URL = "url";

    public static final String USERNAME = "username";

    public static final String PASSWORD = "password";

    public static final String TABLE_PREFIX = "table_prefix";

    public static final String DB_QUALIFIER = "db_qualifier";

}
