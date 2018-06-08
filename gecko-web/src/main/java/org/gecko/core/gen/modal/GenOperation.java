package org.gecko.core.gen.modal;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 自定义操作
 *
 * @author: dengzhi
 * @date: 2018/5/31
 */
@Data
public class GenOperation {
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
     * 图标样式
     */
    private String iconClass;
}
