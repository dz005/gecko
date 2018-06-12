package org.gecko.modular.code.controller;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.extern.slf4j.Slf4j;
import org.gecko.core.base.JsonResponse;
import org.gecko.core.gen.enums.DbType;
import org.gecko.modular.code.entity.Db;
import org.gecko.modular.code.entity.Project;
import org.gecko.modular.code.entity.Table;
import org.gecko.modular.code.service.IDbService;
import org.gecko.modular.code.service.IProjectService;
import org.gecko.modular.code.service.ITableService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 数据库管理控制器
 *
 * @author: dengzhi
 * @date: 2018/5/28
 */
@Slf4j
@Controller
@RequestMapping("/db")
public class DbController {

    private static final String PREFIX = "/modular/code/db/";

    @Resource
    private IDbService dbService;
    @Resource
    private ITableService tableService;
    @Resource
    private IProjectService projectService;

    /**
     * 跳转到列表
     *
     * @return
     */
    @GetMapping("/list")
    public String list(Model model, @RequestParam Long projectId) {
        model.addAttribute("projectId", projectId);
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
        model.addAttribute("dbTypes", DbType.values());
        return PREFIX + "add";
    }

    /**
     * 跳转到修改
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/to_update/{id}")
    public String to_update(Model model, @PathVariable Long id) {
        model.addAttribute("bean", dbService.selectById(id));
        model.addAttribute("dbTypes", DbType.values());
        return PREFIX + "update";
    }

    @ResponseBody
    @GetMapping("/selectPage")
    public Page<Db> selectPage(@RequestParam(defaultValue = "1") int pageNumber,
                               @RequestParam(defaultValue = "10") int pageSize,
                               @RequestParam Long projectId,
                               Db db) {
        return dbService.selectPage(new Page<>(pageNumber, pageSize),
                Condition.create().eq(Db.PROJECT_ID, projectId));
    }

    @ResponseBody
    @PostMapping("/delete/{id}")
    public JsonResponse delete(@PathVariable Long id) {
        int tableCount = tableService.selectCount(Condition.create().eq(Table.DB_ID, id));
        Assert.isTrue(tableCount == 0, "请删除表的配置");
        Db db = dbService.selectById(id);
        Long projectId = db.getProjectId();
        Assert.notNull(projectId, "projectId is null");
        dbService.deleteById(id);
        recountDb(projectId);
        return JsonResponse.SUCCESS;
    }

    @ResponseBody
    @PostMapping("/add")
    public JsonResponse add(Db db) {
        Long projectId = db.getProjectId();
        Assert.notNull(projectId, "projectId is null");
        dbService.insert(db);
        recountDb(projectId);
        return JsonResponse.SUCCESS;
    }

    @ResponseBody
    @PostMapping("/update")
    public JsonResponse update(Db db) {
        Assert.notNull(db.getId(), "id is null");
        dbService.updateById(db);
        return JsonResponse.SUCCESS;
    }

    /**
     * 重新统计数据库数量
     *
     * @param projectId
     */
    protected void recountDb(Long projectId) {
        int dbCount = dbService.selectCount(Condition.create().eq(Db.PROJECT_ID, projectId));
        Project project = projectService.selectById(projectId);
        project.setDbCount(dbCount);
        projectService.updateById(project);
    }

}
