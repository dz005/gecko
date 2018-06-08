package org.gecko.core.gen.modal;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 字段组
 *
 * @author: dengzhi
 * @date: 2018/5/31
 */
@Data
public class GenFieldGroup {

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
     * 排序
     */
    private int sortNum;
    /**
     * 字段组下的字段集合
     */
    private List<GenField> fields;
}
