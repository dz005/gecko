package org.gecko.core.metadata;

import lombok.Data;

import java.util.List;

/**
 * 数据库元数据
 *
 * @author: dengzhi
 * @date: 2018/5/28
 */
@Data
public class DbMetaData {

    /**
     * 数据库已知的用户
     */
    private String userName;
    /**
     * 数据库的系统函数(逗号分隔)
     */
    private String systemFunctions;
    /**
     * 数据库的时间和日期函数(逗号分隔)
     */
    private String timeDateFunctions;
    /**
     * 数据库的字符串函数(逗号分隔)
     */
    private String stringFunctions;
    /**
     * 数据库供应商用于schema的首选术语
     */
    private String schemaTerm;
    /**
     * 数据库URL
     */
    private String url;
    /**
     * 是否只读
     */
    private Boolean readOnly;
    /**
     * 数据库的产品名称
     */
    private String databaseProductName;
    /**
     * 数据库的版本
     */
    private String databaseProductVersion;
    /**
     * 驱动程序的名称
     */
    private String driverName;
    /**
     * 驱动程序的版本
     */
    private String driverVersion;

    /**
     * 数据库表的类型(逗号分隔)
     */
    private String tableTypes;

}
