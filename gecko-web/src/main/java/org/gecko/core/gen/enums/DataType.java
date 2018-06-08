package org.gecko.core.gen.enums;

import lombok.Getter;

/**
 * 字段类型
 *
 * @author: dengzhi
 * @date: 2018/6/6
 */
@Getter
public enum DataType {
    BASE_INT("int", null),
    BASE_LONG("long", null),
    BASE_CHAR("char", null),
    BASE_BYTE("byte", null),
    BASE_BOOLEAN("boolean", null),
    BASE_SHORT("short", null),
    BASE_FLOAT("float", null),
    BASE_DOUBLE("double", null),
    STRING("String", null),
    LONG("Long", null),
    INTEGER("Integer", null),
    FLOAT("Float", null),
    DOUBLE("Double", null),
    BOOLEAN("Boolean", null),
    BYTE("Byte", null),
    BYTE_ARRAY("byte[]", null),
    CHARACTER("Character", null),
    OBJECT("Object", null),
    DATE("Date", "java.util.Date"),
    TIME("Time", "java.querys.Time"),
    BLOB("Blob", "java.querys.Blob"),
    CLOB("Clob", "java.querys.Clob"),
    TIMESTAMP("Timestamp", "java.querys.Timestamp"),
    BIG_INTEGER("BigInteger", "java.math.BigInteger"),
    BIG_DECIMAL("BigDecimal", "java.math.BigDecimal"),
    LOCAL_DATE("LocalDate", "java.time.LocalDate"),
    LOCAL_TIME("LocalTime", "java.time.LocalTime"),
    LOCAL_DATE_TIME("LocalDateTime", "java.time.LocalDateTime");

    private String name;
    private String type;
    private String pkg;

    DataType(String type, String pkg) {
        this.name = name();
        this.type = type;
        this.pkg = pkg;
    }

}
