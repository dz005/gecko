package org.gecko.modular.code.controller;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.extern.slf4j.Slf4j;
import org.gecko.core.base.JsonResponse;
import org.gecko.core.gen.enums.DataType;
import org.gecko.core.gen.enums.FieldGroupType;
import org.gecko.core.gen.enums.WidgetType;
import org.gecko.core.metadata.ConnectionInfo;
import org.gecko.core.metadata.MetaDataService;
import org.gecko.core.metadata.TableMetaData;
import org.gecko.modular.code.entity.Db;
import org.gecko.modular.code.entity.Table;
import org.gecko.modular.code.handler.TableImportHandler;
import org.gecko.modular.code.service.IDbService;
import org.gecko.modular.code.service.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toCollection;

/**
 * 表管理
 *
 * @author: dengzhi
 * @date: 2018/5/28
 */
@Slf4j
@Controller
@RequestMapping("/table")
public class TableController {

    private static final String PREFIX = "/modular/code/table/";

    @Autowired
    private IDbService dbService;
    @Autowired
    private ITableService tableService;
    @Autowired
    private MetaDataService metaDataService;
    @Autowired
    private TableImportHandler tableImportHandler;

    /**
     * 跳转到列表
     *
     * @return
     */
    @GetMapping("/list")
    public String list(Model model, @RequestParam Long projectId) {
        List<Db> dbList = dbService.selectList(Condition.create().eq(Db.PROJECT_ID, projectId));
        model.addAttribute("projectId", projectId);
        model.addAttribute("dbList", dbList);
        return PREFIX + "list";
    }

    /**
     * 跳转到添加
     *
     * @return
     */
    @GetMapping("/to_add")
    public String toAdd(Model model, @RequestParam Long projectId) {
        model.addAttribute("projectId", projectId);
        return PREFIX + "add";
    }

    /**
     * 跳转到修改
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/to_config/{id}")
    public String to_update(@PathVariable Long id, Model model) {
        model.addAttribute("bean", tableService.selectById(id));
        model.addAttribute("fieldSetType", FieldGroupType.values());
        model.addAttribute("widgetTypes", WidgetType.values());
        model.addAttribute("dataTypes", DataType.values());
        return PREFIX + "config";
    }

    /**
     * 跳转到表导入页面
     *
     * @return
     */
    @GetMapping("/to_import")
    public String to_import(Model model, @RequestParam Long projectId) {
        List<Db> dbList = dbService.selectList(Condition.create().eq(Db.PROJECT_ID, projectId));
        model.addAttribute("dbList", dbList);
        return PREFIX + "import";
    }

    @ResponseBody
    @RequestMapping("/table_select")
    public JsonResponse tableSelect(@RequestParam Long dbId) throws SQLException {
        Db db = dbService.selectById(dbId);
        ConnectionInfo connectionInfo = new ConnectionInfo(db.getUrl(), db.getUsername(), db.getPassword());
        List<TableMetaData> tmdList = metaDataService.getTableMetaDataList(connectionInfo);
        List<Table> tableList = tableService.selectList(Condition.create().eq(Table.DB_ID, dbId));
        LinkedHashSet<String> selectedList = tableList.stream()
                .map(t -> t.getTableName())
                .collect(toCollection(LinkedHashSet::new));
        List<String> unSelectedList = tmdList.stream()
                .map(t -> t.getTableName())
                .filter(n -> !selectedList.contains(n))
                .collect(Collectors.toList());
        Map<String, Object> result = new HashMap<>(2);
        result.put("tablePrefix", db.getTablePrefix());
        result.put("selectedList", selectedList);
        result.put("unSelectedList", unSelectedList);
        return JsonResponse.buildSuccess(result);
    }


    @ResponseBody
    @PostMapping("/table_import")
    public JsonResponse tableImport(@RequestParam Long dbId,
                                    @RequestParam String tablePrefix,
                                    @RequestParam("tableNames[]") List<String> tableNames) throws Exception {
        Map<String, Object> errorMap = tableImportHandler.handle(dbId, tablePrefix, tableNames);
        return JsonResponse.buildSuccess(errorMap.keySet());
    }

    @ResponseBody
    @GetMapping("/selectPage")
    public Page<Table> selectPage(@RequestParam(defaultValue = "1") int pageNumber,
                                  @RequestParam(defaultValue = "10") int pageSize,
                                  @RequestParam Long projectId,
                                  String tableName) {
        return tableService.selectPage(new Page<>(pageNumber, pageSize),
                Condition.create()
                        .eq(Table.PROJECT_ID, projectId)
                        .like(StringUtils.hasText(tableName), Table.TABLE_NAME, tableName));
    }

    @ResponseBody
    @PostMapping("/delete/{id}")
    public JsonResponse delete(@PathVariable Long id) {
        tableService.deleteById(id);
        return JsonResponse.SUCCESS;
    }

    @ResponseBody
    @PostMapping("/add")
    public JsonResponse add(Table table) {
        tableService.insert(table);
        return JsonResponse.SUCCESS;
    }

    @ResponseBody
    @PostMapping("/update")
    public JsonResponse update(Table table) {
        Assert.notNull(table.getId(), "id is null");
        tableService.updateById(table);
        return JsonResponse.SUCCESS;
    }

}
