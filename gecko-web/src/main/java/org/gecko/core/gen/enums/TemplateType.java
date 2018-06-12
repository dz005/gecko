package org.gecko.core.gen.enums;

import lombok.Getter;

/**
 * 模板类型
 *
 * @author: dengzhi
 * @date: 2018/5/31
 */
@Getter
public enum TemplateType {

    BEETL("Beetl"),
    FREEMARKER("FreeMarker");

    private String name;
    private String title;

    TemplateType(String title) {
        this.name = name();
        this.title = title;
    }
}
