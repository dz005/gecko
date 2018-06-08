package org.gecko.modular.code.provider;

import com.baomidou.mybatisplus.mapper.Condition;
import org.gecko.core.base.converter.ConvertUtils;
import org.gecko.core.gen.modal.GenTemplate;
import org.gecko.modular.code.converter.GenTemplateConverter;
import org.gecko.modular.code.entity.Template;
import org.gecko.modular.code.service.ITemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @author: dengzhi
 * @date: 2018/6/6
 */
@Component
public class GenTemplateProviderImpl implements GenTemplateProvider {

    @Autowired
    private ITemplateService templateService;
    @Autowired
    private GenTemplateConverter genTemplateConverter;

    @Override
    public List<GenTemplate> getGenTemplateList(Long projectId) {
        List<Template> templateList = templateService.selectList(Condition.create()
                .eq(Template.PROJECT_ID, projectId)
                .eq(Template.ACTIVATED, 1));
        if (templateList != null && !templateList.isEmpty()) {
            return ConvertUtils.convert(genTemplateConverter, templateList);
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public GenTemplate getGenTemplate(Long templateId) {
        Template template = templateService.selectById(templateId);
        return genTemplateConverter.convert(template);
    }

}
