package org.gecko.core.gen.parser;

import org.gecko.core.gen.enums.TemplateType;
import org.gecko.core.gen.modal.GenEntity;
import org.gecko.core.gen.modal.GenGlobal;
import org.gecko.core.gen.modal.GenTemplate;

/**
 * 模板解析器
 *
 * @author: dengzhi
 * @date: 2018/6/7
 */
public interface Parser {

    String GEN_GLOBAL = "g";
    String GEN_ENTITY = "t";

    /**
     * 是否支持该模板类型
     *
     * @param type
     * @return
     */
    boolean support(TemplateType type);

    /**
     * 解析
     *
     * @param template
     * @param entity
     * @param global
     * @throws Exception
     */
    String parse(GenTemplate template, GenEntity entity, GenGlobal global) throws Exception;

}
