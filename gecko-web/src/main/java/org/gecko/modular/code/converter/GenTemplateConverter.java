package org.gecko.modular.code.converter;

import org.gecko.core.base.converter.Converter;
import org.gecko.core.gen.enums.TemplateType;
import org.gecko.core.gen.modal.GenTemplate;
import org.gecko.modular.code.entity.Template;
import org.springframework.stereotype.Component;

/**
 * @author: dengzhi
 * @date: 2018/6/8
 */
@Component
public class GenTemplateConverter implements Converter<Template, GenTemplate> {

    @Override
    public GenTemplate convert(Template source) {
        GenTemplate target = new GenTemplate();
        target.setId(source.getId());
        target.setType(TemplateType.valueOf(source.getTemplateType()));
        target.setFileName(source.getFileName());
        target.setFilePath(source.getFilePath());
        target.setContent(source.getContent());
        return target;
    }

}

