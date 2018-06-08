package org.gecko.modular.code.controller;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.extern.slf4j.Slf4j;
import org.gecko.core.base.JsonResponse;
import org.gecko.modular.code.entity.Db;
import org.gecko.modular.code.service.IDbService;
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
        dbService.deleteById(id);
        return JsonResponse.SUCCESS;
    }

    @ResponseBody
    @PostMapping("/add")
    public JsonResponse add(Db db) {
        dbService.insert(db);
        return JsonResponse.SUCCESS;
    }

    @ResponseBody
    @PostMapping("/update")
    public JsonResponse update(Db db) {
        Assert.notNull(db.getId(), "id is null");
        dbService.updateById(db);
        return JsonResponse.SUCCESS;
    }


}
