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
 * 模板内容
 * </p>
 *
 * @author zhi.deng
 * @since 2018-06-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_template_file")
public class TemplateFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("template_id")
    private Long templateId;
    /**
     * 模板内容
     */
    private String content;
    /**
     * 文件类型
     */
    @TableField("file_type")
    private String fileType;


    public static final String ID = "id";

    public static final String TEMPLATE_ID = "template_id";

    public static final String CONTENT = "content";

    public static final String FILE_TYPE = "file_type";

}
