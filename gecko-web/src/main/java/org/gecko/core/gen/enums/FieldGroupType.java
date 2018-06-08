package org.gecko.core.gen.enums;

import lombok.Getter;

/**
 * 字段组类型
 *
 * @author: dengzhi
 * @date: 2018/6/7
 */
@Getter
public enum FieldGroupType {

    NONE("不分组"),
    FIELD_SET("字段集"),
    TAB("选项卡");

    private String name;
    private String title;

    FieldGroupType(String title) {
        this.name = name();
        this.title = title;
    }
}
