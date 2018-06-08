package org.gecko.core.metadata;

import com.google.common.collect.Lists;

import java.sql.*;
import java.text.MessageFormat;
import java.util.*;


/**
 * 数据库元数据服务实现
 *
 * @author: dengzhi
 * @date: 2018/5/28
 */
public class MetaDataServiceImpl implements MetaDataService {

    private Connection getConnection(ConnectionInfo connectionInfo) throws SQLException {
        return DriverManager.getConnection(connectionInfo.getUrl(), connectionInfo.getUsername(), connectionInfo.getPassword());
    }

    private void checkTableName(String tableName) {
        if (tableName == null || tableName.length() == 0) {
            throw new IllegalArgumentException("tableName is null");
        }
    }

    @Override
    public DbMetaData getDbMetaData(ConnectionInfo connectionInfo) throws SQLException {
        DbMetaData result = new DbMetaData();
        ResultSet rs = null;
        try (Connection conn = getConnection(connectionInfo);) {
            DatabaseMetaData dbmd = conn.getMetaData();
            result.setUserName(dbmd.getUserName());
            result.setSystemFunctions(dbmd.getSystemFunctions());
            result.setTimeDateFunctions(dbmd.getTimeDateFunctions());
            result.setStringFunctions(dbmd.getStringFunctions());
            result.setSchemaTerm(dbmd.getSchemaTerm());
            result.setUrl(dbmd.getURL());
            result.setReadOnly(dbmd.isReadOnly());
            result.setDatabaseProductName(dbmd.getDatabaseProductName());
            result.setDatabaseProductVersion(dbmd.getDatabaseProductVersion());
            result.setDriverName(dbmd.getDriverName());
            result.setDriverVersion(dbmd.getDriverVersion());
            rs = dbmd.getTableTypes();
            List<String> tableTypes = new LinkedList<>();
            while (rs.next()) {
                tableTypes.add(rs.getString("TABLE_TYPE"));
            }
            result.setTableTypes(String.join(",", tableTypes));
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return result;
    }

    @Override
    public List<String> getSchemaNameList(ConnectionInfo connectionInfo) throws SQLException {
        List<String> result = new LinkedList<>();
        try (Connection conn = getConnection(connectionInfo);
             ResultSet rs = conn.getMetaData().getSchemas()) {
            while (rs.next()) {
                result.add(rs.getString("TABLE_SCHEM"));
            }
        }
        return result;
    }

    @Override
    public List<TableMetaData> getTableMetaDataList(ConnectionInfo connectionInfo) throws SQLException {
        List<TableMetaData> result = new LinkedList<>();
        String tableNamePattern = "%";
        String[] types = {"TABLE"};
        TableMetaData tableMetaData;
        try (Connection conn = getConnection(connectionInfo);
             ResultSet rs = conn.getMetaData().getTables(null, null, tableNamePattern, types)) {
            while (rs.next()) {
                tableMetaData = new TableMetaData();
                tableMetaData.setTableCat(rs.getString("TABLE_CAT"));
                tableMetaData.setTableSchem(rs.getString("TABLE_SCHEM"));
                tableMetaData.setTableName(rs.getString("TABLE_NAME"));
                tableMetaData.setTableType(rs.getString("TABLE_TYPE"));
                tableMetaData.setRemarks(rs.getString("REMARKS"));
                result.add(tableMetaData);
            }
        }
        return result;
    }

    @Override
    public List<ColumnMetaData> getColumnMetaDataList(ConnectionInfo connectionInfo, String tableName) throws SQLException {
        Map<String, List<ColumnMetaData>> map = getColumnMetaDataList(connectionInfo, Lists.newArrayList(tableName));
        if (map != null && map.size() == 1) {
            return map.values().iterator().next();
        } else {
            throw new RuntimeException(MessageFormat.format("exception to get the ColumnMetaData of {0}", tableName));
        }
    }

    @Override
    public Map<String, List<ColumnMetaData>> getColumnMetaDataList(ConnectionInfo connectionInfo, List<String> tableNames) throws SQLException {
        Map<String, List<ColumnMetaData>> result = new HashMap<>(tableNames.size());
        ColumnMetaData metaData;
        List<ColumnMetaData> list;
        try (Connection conn = getConnection(connectionInfo)) {
            DatabaseMetaData dbmd = conn.getMetaData();
            for (String tableName : tableNames) {
                checkTableName(tableName);
                try (ResultSet rs = dbmd.getColumns(null, null, tableName, null)) {
                    list = new ArrayList<>();
                    while (rs.next()) {
                        metaData = new ColumnMetaData();
                        metaData.setTableCat(rs.getString("TABLE_CAT"));
                        metaData.setTableSchem(rs.getString("TABLE_SCHEM"));
                        metaData.setTableName(rs.getString("TABLE_NAME"));
                        metaData.setColumnName(rs.getString("COLUMN_NAME"));
                        metaData.setDataType(rs.getInt("DATA_TYPE"));
                        metaData.setTypeName(rs.getString("TYPE_NAME"));
                        metaData.setColumnSize(rs.getInt("COLUMN_SIZE"));
                        metaData.setDecimalDigits(rs.getInt("DECIMAL_DIGITS"));
                        metaData.setNumPrecRadix(rs.getInt("NUM_PREC_RADIX"));
                        metaData.setNullable(rs.getInt("NULLABLE"));
                        metaData.setRemarks(rs.getString("REMARKS"));
                        metaData.setColumnDef(rs.getString("COLUMN_DEF"));
                        metaData.setCharOctetLength(rs.getInt("CHAR_OCTET_LENGTH"));
                        metaData.setOrdinalPosition(rs.getInt("ORDINAL_POSITION"));
                        list.add(metaData);
                    }
                    result.put(tableName, list);
                }
            }
        }
        return result;
    }

    @Override
    public List<PrimaryKeyMetaData> getPrimaryKeyMetaDataList(ConnectionInfo connectionInfo, String tableName) throws SQLException {
        Map<String, List<PrimaryKeyMetaData>> map = getPrimaryKeyMetaDataList(connectionInfo, Lists.newArrayList(tableName));
        if (map != null && map.size() == 1) {
            return map.values().iterator().next();
        } else {
            throw new RuntimeException(MessageFormat.format("exception to get the PrimaryKeyMetaData of {0}", tableName));
        }
    }

    @Override
    public Map<String, List<PrimaryKeyMetaData>> getPrimaryKeyMetaDataList(ConnectionInfo connectionInfo, List<String> tableNames) throws SQLException {
        Map<String, List<PrimaryKeyMetaData>> result = new HashMap<>(tableNames.size());
        PrimaryKeyMetaData metaData;
        List<PrimaryKeyMetaData> list;
        try (Connection conn = getConnection(connectionInfo)) {
            DatabaseMetaData dbmd = conn.getMetaData();
            for (String tableName : tableNames) {
                checkTableName(tableName);
                try (ResultSet rs = dbmd.getPrimaryKeys(null, null, tableName)) {
                    list = new ArrayList<>();
                    while (rs.next()) {
                        metaData = new PrimaryKeyMetaData();
                        metaData.setTableCat(rs.getString("TABLE_CAT"));
                        metaData.setTableSchem(rs.getString("TABLE_SCHEM"));
                        metaData.setTableName(rs.getString("TABLE_NAME"));
                        metaData.setColumnName(rs.getString("COLUMN_NAME"));
                        metaData.setKeySeq(rs.getShort("KEY_SEQ"));
                        metaData.setPkName(rs.getString("PK_NAME"));
                        list.add(metaData);
                    }
                    result.put(tableName, list);
                }
            }
        }
        return result;
    }

    @Override
    public List<IndexMetaData> getIndexMetaDataList(ConnectionInfo connectionInfo, String tableName) throws SQLException {
        Map<String, List<IndexMetaData>> map = getIndexMetaDataList(connectionInfo, Lists.newArrayList(tableName));
        if (map != null && map.size() == 1) {
            return map.values().iterator().next();
        } else {
            throw new RuntimeException(MessageFormat.format("exception to get the IndexMetaData of {0}", tableName));
        }
    }

    @Override
    public Map<String, List<IndexMetaData>> getIndexMetaDataList(ConnectionInfo connectionInfo, List<String> tableNames) throws SQLException {
        Map<String, List<IndexMetaData>> result = new HashMap<>(tableNames.size());
        IndexMetaData metaData;
        List<IndexMetaData> list;
        //该参数为 true时,仅返回唯一值的索引; 该参数为 false时,返回所有索引
        boolean unique = false;
        //该参数为true时,允许结果是接近的数据值或这些数据值以外的值;该参数为 false时,要求结果是精确结果
        boolean approximate = true;
        try (Connection conn = getConnection(connectionInfo)) {
            DatabaseMetaData dbmd = conn.getMetaData();
            for (String tableName : tableNames) {
                checkTableName(tableName);
                try (ResultSet rs = dbmd.getIndexInfo(null, null, tableName, unique, approximate)) {
                    list = new ArrayList<>();
                    while (rs.next()) {
                        metaData = new IndexMetaData();
                        metaData.setTableCat(rs.getString("TABLE_CAT"));
                        metaData.setTableSchem(rs.getString("TABLE_SCHEM"));
                        metaData.setTableName(rs.getString("TABLE_NAME"));
                        metaData.setColumnName(rs.getString("COLUMN_NAME"));
                        metaData.setNonUnique(rs.getBoolean("NON_UNIQUE"));
                        metaData.setIndexQualifier(rs.getString("INDEX_QUALIFIER"));
                        metaData.setIndexName(rs.getString("INDEX_NAME"));
                        metaData.setType(rs.getShort("TYPE"));
                        metaData.setOrdinalPosition(rs.getShort("ORDINAL_POSITION"));
                        metaData.setColumnName(rs.getString("COLUMN_NAME"));
                        metaData.setAscOrDesc(rs.getString("ASC_OR_DESC"));
                        metaData.setCardinality(rs.getInt("CARDINALITY"));
                        metaData.setPages(rs.getInt("PAGES"));
                        metaData.setFilterCondition(rs.getString("FILTER_CONDITION"));
                        list.add(metaData);
                    }
                    result.put(tableName, list);
                }
            }
        }
        return result;
    }
}
