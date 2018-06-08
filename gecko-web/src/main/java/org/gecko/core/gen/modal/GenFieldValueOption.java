package org.gecko.core.gen.modal;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 字段值选项
 *
 * @author: dengzhi
 * @date: 2018/5/31
 */
@Data
public class GenFieldValueOption {

    /**
     * 字段值
     */
    @NotBlank
    private String value;
    /**
     * 标题
     */
    @NotBlank
    private String title;

}
