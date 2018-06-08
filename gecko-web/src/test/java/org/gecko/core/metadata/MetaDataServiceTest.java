package org.gecko.core.metadata;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class MetaDataServiceTest {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/gecko?useSSL=false";
        String username = "root";
        String password = "root";
        MetaDataService metaDataService = new MetaDataServiceImpl();
        ConnectionInfo connectionInfo = new ConnectionInfo(url, username, password);
        System.out.println(metaDataService.getDbMetaData(connectionInfo));
        System.out.println(metaDataService.getSchemaNameList(connectionInfo));
        List<TableMetaData> tableMetaDataList = metaDataService.getTableMetaDataList(connectionInfo);
        tableMetaDataList.stream().forEach(System.out::println);
        List<String> tableNames = tableMetaDataList.stream().map(t -> t.getTableName()).collect(Collectors.toList());
        System.out.println(tableNames);
        System.out.println();
        metaDataService.getColumnMetaDataList(connectionInfo, tableNames).values().stream().forEach(System.out::println);
        System.out.println();
        metaDataService.getPrimaryKeyMetaDataList(connectionInfo, tableNames).values().stream().forEach(System.out::println);
        System.out.println();
        metaDataService.getIndexMetaDataList(connectionInfo, tableNames).values().stream().forEach(System.out::println);
    }
}
