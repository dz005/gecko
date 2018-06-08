package org.gecko.core.gen.modal;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 校验规则
 *
 * @author: dengzhi
 * @date: 2018/5/31
 */
@Data
public class GenFieldRule {
    /**
     * 规则名称
     */
    @NotNull
    private String name;
    /**
     * 规则值
     */
    @NotBlank
    private String value;
    /**
     * 校验失败提醒
     */
    private String message;
}
