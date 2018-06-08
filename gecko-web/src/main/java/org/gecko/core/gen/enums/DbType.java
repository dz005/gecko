package org.gecko.core.gen.enums;

import lombok.Getter;

/**
 * 数据库类型
 *
 * @author: dengzhi
 * @date: 2018/5/31
 */
@Getter
public enum DbType {

    MySQL("com.mysql.jdbc.Driver"),
    Oracle("oracle.jdbc.driver.OracleDriver"),
    SQLServer("com.microsoft.sqlserver.jdbc.SQLServerDriver"),
    PostgreSQL("org.postgresql.Driver");

    private String name;
    private String driverClassName;

    DbType(String driverClassName) {
        this.name = name();
        this.driverClassName = driverClassName;
    }

}
