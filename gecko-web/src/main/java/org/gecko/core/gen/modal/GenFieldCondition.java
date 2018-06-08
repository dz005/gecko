package org.gecko.core.gen.modal;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 查询条件
 *
 * @author: dengzhi
 * @date: 2018/5/31
 */
@Data
public class GenFieldCondition {

    /**
     * 字段
     */
    @NotNull
    private GenField field;
    /**
     * 条件符
     */
    @NotBlank
    private String symbol;
    /**
     * 排序
     */
    private int sortNum;

}
