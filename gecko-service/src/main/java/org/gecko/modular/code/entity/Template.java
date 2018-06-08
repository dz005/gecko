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
 * 模板
 * </p>
 *
 * @author zhi.deng
 * @since 2018-06-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_template")
public class Template implements Serializable {

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
     * 标题
     */
    private String title;
    /**
     * 模板类型
     */
    @TableField("template_type")
    private String templateType;
    /**
     * 文件名
     */
    @TableField("file_name")
    private String fileName;
    /**
     * 文件相对路径
     */
    @TableField("file_path")
    private String filePath;
    /**
     * 文件类型
     */
    @TableField("file_type")
    private String fileType;
    /**
     * 模板内容
     */
    private String content;
    /**
     * 是否激活
     */
    private Boolean activated;


    public static final String ID = "id";

    public static final String PROJECT_ID = "project_id";

    public static final String TITLE = "title";

    public static final String TEMPLATE_TYPE = "template_type";

    public static final String FILE_NAME = "file_name";

    public static final String FILE_PATH = "file_path";

    public static final String FILE_TYPE = "file_type";

    public static final String CONTENT = "content";

    public static final String ACTIVATED = "activated";

}
