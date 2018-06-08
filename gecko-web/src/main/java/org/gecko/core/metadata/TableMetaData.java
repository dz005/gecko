package org.gecko.core.metadata;

import lombok.Data;

/**
 * 表元数据
 *
 * @author: dengzhi
 * @date: 2018/5/28
 */
@Data
public class TableMetaData {
    /**
     * 表类别(可为null)
     */
    private String tableCat;
    /**
     * 表模式（可能为空）,在oracle中获取的是命名空间,其它数据库未知
     */
    private String tableSchem;
    /**
     * 表类型,典型的类型是 "TABLE"、"VIEW"、"SYSTEM TABLE"、"GLOBAL TEMPORARY"、"LOCAL TEMPORARY"、"ALIAS" 和 "SYNONYM"。
     */
    private String tableType;
    /**
     * 表名
     **/
    private String tableName;
    /**
     * 表备注
     */
    private String remarks;

}
