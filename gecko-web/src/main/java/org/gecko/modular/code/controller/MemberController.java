package org.gecko.modular.code.controller;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.extern.slf4j.Slf4j;
import org.gecko.modular.code.entity.ProjectMember;
import org.gecko.modular.code.service.IProjectMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: dengzhi
 * @date: 2018/6/1
 */
@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

    private static final String PREFIX = "/modular/code/member/";

    @Autowired
    private IProjectMemberService projectMemberService;

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

    @ResponseBody
    @GetMapping("/selectPage")
    public Page<ProjectMember> selectPage(@RequestParam(defaultValue = "1") int pageNumber,
                                          @RequestParam(defaultValue = "10") int pageSize) {
        return projectMemberService.selectPage(new Page<>(pageNumber, pageSize),
                Condition.create());
    }

}
