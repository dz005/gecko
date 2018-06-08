package org.gecko.core.gen.enums;

import lombok.Getter;

/**
 * 组件类型
 *
 * @author: dengzhi
 * @date: 2018/5/31
 */
@Getter
public enum WidgetType {

    NONE("静态域"),
    TEXT("文本框"),
    PASSWORD("密码"),
    FILE("文件"),
    DATE("日期"),
    DATE_TIME("日期时间"),
    TIME("时间"),
    YEAR("年"),
    MONTH("年月"),
    WEEK("年周"),
    NUMBER("数字"),
    EMAIL("邮箱"),
    URL("网址"),
    SWITCH("开关"),
    TEXT_AREA("文本域"),
    CHECK_BOX("复选框"),
    RADIO("单选框"),
    RANGE("滑动选择框"),
    KNOB("旋钮"),
    COLOR("颜色选择框"),
    SELECT("下拉框");

    private String name;
    private String title;

    WidgetType(String title) {
        this.name = name();
        this.title = title;
    }
}
