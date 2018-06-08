package org.gecko.core.metadata;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 数据库元数据服务接口
 *
 * @author: dengzhi
 * @date: 2018/5/28
 */
public interface MetaDataService {

    /**
     * 返回数据库元数据
     *
     * @param connectionInfo
     * @return
     * @throws SQLException
     */
    DbMetaData getDbMetaData(ConnectionInfo connectionInfo) throws SQLException;


    /**
     * 返回表模式列表(对应于oracle中的Tablespace)
     *
     * @param connectionInfo
     * @return
     * @throws SQLException
     */
    List<String> getSchemaNameList(ConnectionInfo connectionInfo) throws SQLException;

    /**
     * 返回所有表元数据集合
     *
     * @param connectionInfo
     * @return
     * @throws SQLException
     */
    List<TableMetaData> getTableMetaDataList(ConnectionInfo connectionInfo) throws SQLException;

    /**
     * 返回指定表的字段元数据集合
     *
     * @param connectionInfo
     * @param tableName
     * @return
     * @throws SQLException
     */
    List<ColumnMetaData> getColumnMetaDataList(ConnectionInfo connectionInfo, String tableName) throws SQLException;

    /**
     * 返回指定表的字段元数据集合
     *
     * @param connectionInfo
     * @param tableNames
     * @return
     * @throws SQLException
     */
    Map<String, List<ColumnMetaData>> getColumnMetaDataList(ConnectionInfo connectionInfo, List<String> tableNames) throws SQLException;

    /**
     * 返回指定表的主键元数据集合
     *
     * @param connectionInfo
     * @param tableName
     * @return
     * @throws SQLException
     */
    List<PrimaryKeyMetaData> getPrimaryKeyMetaDataList(ConnectionInfo connectionInfo, String tableName) throws SQLException;

    /**
     * 返回指定表的主键元数据集合
     *
     * @param connectionInfo
     * @param tableNames
     * @return
     * @throws SQLException
     */
    Map<String, List<PrimaryKeyMetaData>> getPrimaryKeyMetaDataList(ConnectionInfo connectionInfo, List<String> tableNames) throws SQLException;

    /**
     * 返回指定表的索引元数据集合
     *
     * @param connectionInfo
     * @param tableName
     * @return
     * @throws SQLException
     */
    List<IndexMetaData> getIndexMetaDataList(ConnectionInfo connectionInfo, String tableName) throws SQLException;

    /**
     * 返回指定表的索引元数据集合
     *
     * @param connectionInfo
     * @param tableNames
     * @return
     * @throws SQLException
     */
    Map<String, List<IndexMetaData>> getIndexMetaDataList(ConnectionInfo connectionInfo, List<String> tableNames) throws SQLException;
}
