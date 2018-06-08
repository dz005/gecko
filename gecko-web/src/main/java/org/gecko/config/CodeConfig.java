package org.gecko.config;

import org.gecko.core.gen.converter.DataTypeConverter;
import org.gecko.core.gen.converter.WidgetTypeConverter;
import org.gecko.core.gen.parser.BeetlParser;
import org.gecko.core.gen.parser.FreeMarkerParser;
import org.gecko.core.gen.parser.Parser;
import org.gecko.core.gen.parser.ParserComposite;
import org.gecko.core.metadata.MetaDataService;
import org.gecko.core.metadata.MetaDataServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: dengzhi
 * @date: 2018/5/28
 */
@Configuration
public class CodeConfig {

    @Bean
    public MetaDataService metaDataService() {
        return new MetaDataServiceImpl();
    }

    @Bean
    public DataTypeConverter dataTypeConverter() {
        return new DataTypeConverter();
    }

    @Bean
    public WidgetTypeConverter WidgetTypeConverter() {
        return new WidgetTypeConverter();
    }

    @Bean
    public Parser parser() {
        ParserComposite parser = new ParserComposite();
        parser.addParser(new BeetlParser());
        parser.addParser(new FreeMarkerParser());
        return parser;
    }
}
