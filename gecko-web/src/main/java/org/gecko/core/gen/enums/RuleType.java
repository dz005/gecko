package org.gecko.core.gen.enums;

import lombok.Getter;

/**
 * 规则项类型
 *
 * @author: dengzhi
 * @date: 2018/5/31
 */
@Getter
public enum RuleType {

    required(WidgetType.SWITCH),
    remote(WidgetType.URL),
    email(WidgetType.SWITCH),
    url(WidgetType.SWITCH),
    date(WidgetType.SWITCH),
    dateISO(WidgetType.SWITCH),
    number(WidgetType.SWITCH),
    digits(WidgetType.SWITCH),
    creditcard(WidgetType.SWITCH),
    equalTo(WidgetType.TEXT),
    accept(WidgetType.TEXT),
    maxlength(WidgetType.NUMBER),
    minlength(WidgetType.NUMBER),
    max(WidgetType.NUMBER),
    min(WidgetType.NUMBER);

    private String name;
    private WidgetType widgetType;

    RuleType(WidgetType widgetType) {
        this.name = name();
        this.widgetType = widgetType;
    }
}
