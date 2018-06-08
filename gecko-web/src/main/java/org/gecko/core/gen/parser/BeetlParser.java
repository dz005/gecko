package org.gecko.core.gen.parser;

import lombok.extern.slf4j.Slf4j;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.StringTemplateResourceLoader;
import org.gecko.core.gen.enums.TemplateType;
import org.gecko.core.gen.modal.GenEntity;
import org.gecko.core.gen.modal.GenGlobal;
import org.gecko.core.gen.modal.GenTemplate;

import java.io.IOException;

/**
 * Beetl 模板解析器
 *
 * @author: dengzhi
 * @date: 2018/6/7
 */
@Slf4j
public class BeetlParser implements Parser {

    private GroupTemplate groupTemplate;

    public BeetlParser() {
        Configuration cfg = null;
        try {
            cfg = Configuration.defaultConfiguration();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        groupTemplate = new GroupTemplate(new StringTemplateResourceLoader(), cfg);
    }

    @Override
    public boolean support(TemplateType type) {
        return TemplateType.BEETL.equals(type);
    }

    @Override
    public String parse(GenTemplate template, GenEntity entity, GenGlobal global) throws Exception {
        Template t = groupTemplate.getTemplate(template.getContent());
        t.binding(GEN_GLOBAL, global);
        t.binding(GEN_ENTITY, entity);
        return t.render();
    }
}
