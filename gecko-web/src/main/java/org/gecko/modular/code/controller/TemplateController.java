package org.gecko.modular.code.controller;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.extern.slf4j.Slf4j;
import org.gecko.core.base.JsonResponse;
import org.gecko.core.gen.enums.TemplateType;
import org.gecko.modular.code.entity.Project;
import org.gecko.modular.code.entity.Template;
import org.gecko.modular.code.service.IProjectService;
import org.gecko.modular.code.service.ITemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author: dengzhi
 * @date: 2018/5/28
 */
@Slf4j
@Controller
@RequestMapping("/template")
public class TemplateController {

    private static final String PREFIX = "/modular/code/template/";

    @Autowired
    private ITemplateService templateService;
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
        model.addAttribute("templateTypes", TemplateType.values());
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
    public String to_update(Model model, @PathVariable Long id) {
        model.addAttribute("bean", templateService.selectById(id));
        model.addAttribute("templateTypes", TemplateType.values());
        return PREFIX + "update";
    }


    @ResponseBody
    @GetMapping("/selectPage")
    public Page<Template> selectPage(@RequestParam(defaultValue = "1") int pageNumber,
                                     @RequestParam(defaultValue = "10") int pageSize,
                                     @RequestParam Long projectId,
                                     String title) {
        return templateService.selectPage(new Page<>(pageNumber, pageSize),
                Condition.create()
                        .eq(Template.PROJECT_ID, projectId)
                        .like(StringUtils.hasText(title), Template.TITLE, title));
    }

    @ResponseBody
    @PostMapping("/delete/{id}")
    public JsonResponse delete(@PathVariable Long id) {
        Template template = templateService.selectById(id);
        Long projectId = template.getProjectId();
        Assert.notNull(projectId, "projectId is null");
        templateService.deleteById(id);
        recountTemplate(projectId);
        return JsonResponse.SUCCESS;
    }

    @ResponseBody
    @PostMapping("/add")
    public JsonResponse add(Template template) {
        Long projectId = template.getProjectId();
        Assert.notNull(projectId, "projectId is null");
        //TODO 暂时置为激活
        template.setActivated(true);
        templateService.insert(template);
        recountTemplate(projectId);
        return JsonResponse.SUCCESS;
    }

    @ResponseBody
    @PostMapping("/update")
    public JsonResponse update(Template template) {
        Assert.notNull(template.getId(), "id is null");
        templateService.updateById(template);
        return JsonResponse.SUCCESS;
    }

    /**
     * 重新统计模板数量
     *
     * @param projectId
     */
    protected void recountTemplate(Long projectId) {
        int templateCount = templateService.selectCount(Condition.create().eq(Template.PROJECT_ID, projectId));
        Project project = projectService.selectById(projectId);
        project.setTemplateCount(templateCount);
        projectService.updateById(project);
    }

}
