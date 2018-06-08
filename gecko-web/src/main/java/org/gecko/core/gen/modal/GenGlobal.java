package org.gecko.core.gen.modal;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

/**
 * 全局参数
 *
 * @author: dengzhi
 * @date: 2018/5/31
 */
@Data
public class GenGlobal {
    /**
     * UUID
     */
    @NotBlank
    private String uuid = UUID.randomUUID().toString();
    /**
     * 作者
     */
    private String author;
    /**
     * 编码
     */
    private String encoding;
    /**
     * code 包
     */
    @NotBlank
    private String codePackage;
    /**
     * xml 相对路径
     */
    private String xmlPath;
    /**
     * js 相对路径
     */
    private String jsPath;
    /**
     * html 相对路径
     */
    private String htmlPath;

}
