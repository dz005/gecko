package org.gecko.core.metadata;

import lombok.Data;

/**
 * 字段元数据
 *
 * @author: dengzhi
 * @date: 2018/5/28
 */
@Data
public class ColumnMetaData {
    /**
     * 表类别(可为null)
     */
    private String tableCat;
    /**
     * 表模式（可能为空）,在oracle中获取的是命名空间,其它数据库未知
     */
    private String tableSchem;
    /**
     * 表名
     **/
    private String tableName;
    /**
     * 列名
     */
    private String columnName;
    /**
     * 对应的java.sql.Types的SQL类型(列类型ID)
     */
    private Integer dataType;
    /**
     * java.sql.Types类型名称(列类型名称)
     */
    private String typeName;
    /**
     * 列大小
     */
    private Integer columnSize;
    /**
     * 小数位数
     */
    private Integer decimalDigits;
    /**
     * 基数（通常是10或2）
     */
    private Integer numPrecRadix;
    /**
     * 0 (columnNoNulls) - 该列不允许为空
     * 1 (columnNullable) - 该列允许为空
     * 2 (columnNullableUnknown) - 不确定该列是否为空
     */
    private Integer nullable;
    /**
     * 列描述
     */
    private String remarks;
    /**
     * 默认值
     */
    private String columnDef;
    /**
     * 对于 char 类型，该长度是列中的最大字节数
     */
    private Integer charOctetLength;
    /**
     * 表中列的索引（从1开始）
     */
    private Integer ordinalPosition;
}
