package org.gecko.modular.code.handler;

import com.baomidou.mybatisplus.mapper.Condition;
import lombok.extern.slf4j.Slf4j;
import org.gecko.core.metadata.*;
import org.gecko.modular.code.entity.Db;
import org.gecko.modular.code.entity.Project;
import org.gecko.modular.code.entity.Table;
import org.gecko.modular.code.service.IDbService;
import org.gecko.modular.code.service.IProjectService;
import org.gecko.modular.code.service.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 导入表实现
 *
 * @author: dengzhi
 * @date: 2018/6/4
 */
@Slf4j
@Component
public class TableImportHandlerImpl implements TableImportHandler {

    @Autowired
    private IProjectService projectService;
    @Autowired
    private IDbService dbService;
    @Autowired
    private ITableService tableService;
    @Autowired
    private MetaDataService metaDataService;
    @Autowired
    private SingleTableHandler singleTableHandler;

    @Override
    public Map<String, Object> handle(Long dbId, String tablePrefix, List<String> tableNames) throws SQLException {
        Map<String, Object> errorMap = new HashMap<>(tableNames.size());
        Db db = dbService.selectById(dbId);
        ConnectionInfo connectionInfo = new ConnectionInfo(db.getUrl(), db.getUsername(), db.getPassword());
        List<TableMetaData> tmdList = metaDataService.getTableMetaDataList(connectionInfo).stream()
                .filter(t -> tableNames.contains(t.getTableName()))
                .collect(Collectors.toList());
        Map<String, List<ColumnMetaData>> cmdMap = metaDataService.getColumnMetaDataList(connectionInfo, tableNames);
        Map<String, List<PrimaryKeyMetaData>> pkmdMap = metaDataService.getPrimaryKeyMetaDataList(connectionInfo, tableNames);
        String tableName;
        for (TableMetaData tmd : tmdList) {
            tableName = tmd.getTableName();
            try {
                singleTableHandler.handle(db, tablePrefix, tmd, cmdMap.get(tableName), pkmdMap.get(tableName));
            } catch (Exception e) {
                log.error(MessageFormat.format("表 [{0}] 导入出错", tableName), e);
                errorMap.put(tableName, e.getMessage());
            }
        }
        recountTable(db.getProjectId());
        return errorMap;
    }

    /**
     * 重新统计数据表数量
     *
     * @param projectId
     */
    protected void recountTable(Long projectId) {
        int tableCount = tableService.selectCount(Condition.create().eq(Table.PROJECT_ID, projectId));
        Project project = projectService.selectById(projectId);
        project.setTableCount(tableCount);
        projectService.updateById(project);
    }

}
