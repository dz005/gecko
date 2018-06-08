package org.gecko.modular.code.controller;

import com.baomidou.mybatisplus.mapper.Condition;
import lombok.extern.slf4j.Slf4j;
import org.gecko.core.base.JsonResponse;
import org.gecko.core.gen.enums.DataType;
import org.gecko.core.gen.enums.RuleType;
import org.gecko.core.gen.enums.WidgetType;
import org.gecko.modular.code.entity.Field;
import org.gecko.modular.code.service.IFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字段管理
 *
 * @author: dengzhi
 * @date: 2018/6/5
 */
@Slf4j
@Controller
@RequestMapping("/field")
public class FieldController {

    private static final String PREFIX = "/modular/code/field/";

    @Autowired
    private IFieldService fieldService;

    /**
     * 跳转到添加
     *
     * @return
     */
    @GetMapping("/to_add")
    public String toAdd(Model model, @RequestParam Long tableId) {
        model.addAttribute("tableId", tableId);
        return PREFIX + "add";
    }

    /**
     * 跳转到配置页面
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/to_config/{id}")
    public String to_update(@PathVariable Long id, Model model) {
        model.addAttribute("bean", fieldService.selectById(id));
        model.addAttribute("widgetTypes", WidgetType.values());
        model.addAttribute("dataTypes", DataType.values());
        model.addAttribute("ruleTypes", RuleType.values());
        return PREFIX + "config";
    }

    @ResponseBody
    @GetMapping("/selectList")
    public List<Field> selectList(@RequestParam Long tableId) {
        return fieldService.selectList(Condition.create().eq(Field.TABLE_ID, tableId).orderBy(Field.SORT_NUM));
    }

    @ResponseBody
    @PostMapping("/delete/{id}")
    public JsonResponse delete(@PathVariable Long id) {
        fieldService.deleteById(id);
        return JsonResponse.SUCCESS;
    }

    @ResponseBody
    @PostMapping("/add")
    public JsonResponse add(Field field) {
        fieldService.insert(field);
        return JsonResponse.SUCCESS;
    }

    @ResponseBody
    @PostMapping("/update")
    public JsonResponse update(Field field) {
        Assert.notNull(field.getId(), "id is null");
        fieldService.updateById(field);
        return JsonResponse.SUCCESS;
    }

}
