package org.gecko.core.metadata;

import lombok.Data;

/**
 * 主键元数据
 *
 * @author: dengzhi
 * @date: 2018/5/28
 */
@Data
public class PrimaryKeyMetaData {

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
     * 序列号(主键内值1表示第一列的主键，值2代表主键内的第二列)
     */
    private Short keySeq;
    /**
     * 主键名称
     */
    private String pkName;

}
