package org.gecko.core.gen.enums;

import lombok.Getter;

/**
 * 查询条件符
 *
 * @author: dengzhi
 * @date: 2018/5/31
 */
@Getter
public enum ConditionSymbol {

    EQ("="),
    NE("!="),
    GT(">"),
    GE(">="),
    LT("<"),
    LE("<="),
    IN("IN"),
    LIKE("LIKE"),
    LEFT_LIKE("LEFT LIKE"),
    RIGHT_LIKE("RIGHT LIKE"),
    BETWEEN("BETWEEN");

    private String name;
    private String symbol;

    ConditionSymbol(String symbol) {
        this.name = name();
        this.symbol = symbol;
    }

}
