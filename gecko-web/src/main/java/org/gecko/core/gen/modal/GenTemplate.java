package org.gecko.core.gen.modal;

import lombok.Data;
import org.gecko.core.gen.enums.TemplateType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 模板
 *
 * @author: dengzhi
 * @date: 2018/5/31
 */
@Data
public class GenTemplate {

    /**
     * 模板ID
     */
    private Long id;
    /**
     * 模板类型
     */
    @NotNull
    private TemplateType type;
    /**
     * 模板内容
     */
    private String content;
    /**
     * 文件类型
     */
    private String fileType;
    /**
     * 文件名
     */
    @NotBlank
    private String fileName;
    /**
     * 文件相对路径
     */
    private String filePath;
}
