package org.gecko.modular.code.controller;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.extern.slf4j.Slf4j;
import org.gecko.core.base.JsonResponse;
import org.gecko.modular.code.entity.Project;
import org.gecko.modular.code.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 项目管理
 *
 * @author: dengzhi
 * @date: 2018/6/1
 */
@Slf4j
@Controller
@RequestMapping("/project")
public class ProjectController {

    private static final String PREFIX = "/modular/code/project/";

    @Autowired
    private IProjectService projectService;


    /**
     * 跳转到项目主页
     *
     * @return
     */
    @GetMapping("/index/{id}")
    public String index(Model model, @PathVariable Long id) {
        model.addAttribute("projectId", id);
        return PREFIX + "index";
    }

    /**
     * 跳转到列表
     *
     * @return
     */
    @GetMapping("/list")
    public String list() {
        return PREFIX + "list";
    }

    /**
     * 跳转到添加
     *
     * @return
     */
    @GetMapping("/to_add")
    public String toAdd() {
        return PREFIX + "add";
    }

    /**
     * 跳转到修改
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/to_update/{id}")
    public String to_update(@PathVariable Long id, Model model) {
        model.addAttribute("bean", projectService.selectById(id));
        return PREFIX + "update";
    }

    @ResponseBody
    @GetMapping("/selectPage")
    public Page<Project> selectPage(@RequestParam(defaultValue = "1") int pageNumber,
                                    @RequestParam(defaultValue = "10") int pageSize,
                                    String name) {
        return projectService.selectPage(new Page<>(pageNumber, pageSize),
                Condition.create().like(StringUtils.hasText(name), Project.NAME, name));
    }

    @ResponseBody
    @PostMapping("/delete/{id}")
    public JsonResponse delete(@PathVariable Long id) {
        projectService.deleteById(id);
        return JsonResponse.SUCCESS;
    }

    @ResponseBody
    @PostMapping("/add")
    public JsonResponse add(Project project) {
        projectService.insert(project);
        return JsonResponse.SUCCESS;
    }

    @ResponseBody
    @PostMapping("/update")
    public JsonResponse update(Project project) {
        Assert.notNull(project.getId(), "id is null");
        projectService.updateById(project);
        return JsonResponse.SUCCESS;
    }

}
