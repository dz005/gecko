package org.gecko.core.gen.parser;

import lombok.extern.slf4j.Slf4j;
import org.gecko.core.gen.enums.TemplateType;
import org.gecko.core.gen.modal.GenEntity;
import org.gecko.core.gen.modal.GenGlobal;
import org.gecko.core.gen.modal.GenTemplate;
import org.springframework.util.Assert;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Beetl 模板解析器组合
 *
 * @author: dengzhi
 * @date: 2018/6/7
 */
@Slf4j
public class ParserComposite implements Parser {

    private final List<Parser> parsers = new LinkedList<>();
    private final Map<TemplateType, Parser> parserCache = new ConcurrentHashMap<>();

    private Parser getParser(TemplateType templateType) {
        Parser result = parserCache.get(templateType);
        if (result == null) {
            for (Parser parser : parsers) {
                if (log.isTraceEnabled()) {
                    log.trace("Testing if method installer [{}] installType [{}]", parser, templateType);
                }
                if (parser.support(templateType)) {
                    result = parser;
                    this.parserCache.put(templateType, result);
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public boolean support(TemplateType templateType) {
        return getParser(templateType) != null;
    }

    @Override
    public String parse(GenTemplate template, GenEntity entity, GenGlobal global) throws Exception {
        TemplateType templateType = template.getType();
        Parser parser = getParser(templateType);
        Assert.notNull(parser, MessageFormat.format("Unknown templateType [{0}]", templateType));
        return parser.parse(template, entity, global);
    }

    public ParserComposite addParser(Parser parser) {
        this.parsers.add(parser);
        return this;
    }

    public ParserComposite addParsers(List<? extends Parser> parsers) {
        if (parsers != null) {
            for (Parser parser : parsers) {
                this.parsers.add(parser);
            }
        }
        return this;
    }

    public List<Parser> getParsers() {
        return Collections.unmodifiableList(this.parsers);
    }

}
