package org.gecko.core.metadata;

import lombok.Data;

/**
 * @author: dengzhi
 * @date: 2018/5/28
 */
@Data
public class IndexMetaData {
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
     * 索引值是否可以不唯一,TYPE为 tableIndexStatistic时索引值为 false
     */
    private Boolean nonUnique;
    /**
     * 索引类别（可能为空）,TYPE为 tableIndexStatistic 时索引类别为 null
     */
    private String indexQualifier;
    /**
     * 索引的名称 ;TYPE为 tableIndexStatistic 时索引名称为 null
     */
    private String indexName;
    /**
     * 索引类型：
     * tableIndexStatistic - 此标识与表的索引描述一起返回的表统计信息
     * tableIndexClustered - 此为集群索引
     * tableIndexHashed - 此为散列索引
     * tableIndexOther - 此为某种其他样式的索引
     */
    private Short type;
    /**
     * 在索引列顺序号;TYPE为 tableIndexStatistic 时该序列号为零
     */
    private Short ordinalPosition;
    /**
     * 列名;TYPE为 tableIndexStatistic时列名称为 null
     */
    private String columnName;
    /**
     * 列排序顺序:升序还是降序[A:升序; B:降序];如果排序序列不受支持,可能为 null;TYPE为 tableIndexStatistic时排序序列为 null
     */
    private String ascOrDesc;
    /**
     * 基数;TYPE为 tableIndexStatistic 时,它是表中的行数;否则,它是索引中唯一值的数量
     */
    private Integer cardinality;
    /**
     * TYPE为 tableIndexStatisic时,它是用于表的页数,否则它是用于当前索引的页数
     */
    private Integer pages;
    /**
     * 过滤器条件,如果有的话(可能为 null)
     */
    private String filterCondition;
}
