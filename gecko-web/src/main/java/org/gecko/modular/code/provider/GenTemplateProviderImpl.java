package org.gecko.modular.code.provider;

import com.baomidou.mybatisplus.mapper.Condition;
import org.gecko.core.gen.enums.TemplateType;
import org.gecko.core.gen.modal.GenTemplate;
import org.gecko.modular.code.entity.Template;
import org.gecko.modular.code.entity.TemplateFile;
import org.gecko.modular.code.service.ITemplateFileService;
import org.gecko.modular.code.service.ITemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: dengzhi
 * @date: 2018/6/6
 */
@Component
public class GenTemplateProviderImpl implements GenTemplateProvider {

    @Autowired
    private ITemplateService templateService;
    @Autowired
    private ITemplateFileService templateFileService;

    @Override
    public List<GenTemplate> getGenTemplateList(Long projectId) {
        List<Template> templateList = templateService.selectList(Condition.create().eq(Template.PROJECT_ID, projectId));
        if (templateList != null && !templateList.isEmpty()) {
            Map<Long, Template> templateMap = templateList.stream().collect(Collectors.toMap(Template::getId, Function.identity()));
            List<TemplateFile> templateFileList = templateFileService.selectList(Condition.create().in(TemplateFile.TEMPLATE_ID, templateMap.keySet()));
            List<GenTemplate> result = new ArrayList<>(templateFileList.size());
            String content;
            Template template;
            for (TemplateFile tf : templateFileList) {
                content = tf.getContent();
                if (StringUtils.hasText(content)) {
                    template = templateMap.get(tf.getId());
                    result.add(convert(template, tf));
                }
            }
            return result;
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public GenTemplate getGenTemplate(Long templateId) {
        TemplateFile templateFile = templateFileService.selectOne(Condition.create().eq(TemplateFile.TEMPLATE_ID, templateId));
        if (templateFile != null && StringUtils.hasText(templateFile.getContent())) {
            Template template = templateService.selectById(templateId);
            return convert(template, templateFile);
        } else {
            return null;
        }
    }

    protected GenTemplate convert(Template template, TemplateFile templateFile) {
        GenTemplate target = new GenTemplate();
        target.setId(template.getId());
        target.setType(TemplateType.valueOf(template.getTemplateType()));
        target.setFileName(template.getFileName());
        target.setFilePath(template.getFilePath());
        target.setFileType(templateFile.getFileType());
        target.setContent(templateFile.getContent());
        return target;
    }
}
