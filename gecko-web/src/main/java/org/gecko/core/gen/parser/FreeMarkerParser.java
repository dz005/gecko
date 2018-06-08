package org.gecko.core.gen.parser;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.gecko.core.gen.enums.TemplateType;
import org.gecko.core.gen.modal.GenEntity;
import org.gecko.core.gen.modal.GenGlobal;
import org.gecko.core.gen.modal.GenTemplate;

import java.io.StringWriter;
import java.io.Writer;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * FreeMarker 模板解析器
 *
 * @author: dengzhi
 * @date: 2018/6/7
 */
@Slf4j
public class FreeMarkerParser implements Parser {

    private StringTemplateLoader resourceLoader = new StringTemplateLoader();
    private Configuration cfg;

    public FreeMarkerParser() {
        try {
            cfg = new Configuration();
            cfg.setTemplateLoader(resourceLoader);
            cfg.setLocale(Locale.CHINA);
            cfg.setDefaultEncoding("UTF-8");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public boolean support(TemplateType type) {
        return TemplateType.FREEMARKER.equals(type);
    }

    @Override
    public String parse(GenTemplate template, GenEntity entity, GenGlobal global) throws Exception {
        String name = "FTL_" + template.getId();
        resourceLoader.putTemplate(name, template.getContent());
        Template t = cfg.getTemplate(name);
        Writer write = new StringWriter();
        Map<String, Object> root = new HashMap<>();
        root.put(GEN_GLOBAL, global);
        root.put(GEN_ENTITY, entity);
        t.process(root, write);
        return write.toString();
    }

    public static void main(String[] args) throws Exception {
        String credential="RERFSWdRQWdsckQ0aGRFcytqcUZFcUNwMEU3WndpUWFiNW1uTHZYeXczSEZVRlNiQ2Y4Tm44UjFHa2xhOVZRSElsaWhmUFFZdFlaY081R01MTDMvcmEwMzJwNEpPdlh1UDlsdXBDR0FEWlVTNjFoQm5VOW9iL0FRMkgwUDB4dklpSnpIUTN3Mm93MU9sMHJPUkhjK3h2L1J4RlVEOHYvKz9hcHBJZD0xJmtleUlkPTE%3D";
        credential = URLDecoder.decode(credential, "UTF-8");
        credential = new String(Base64.decodeBase64((credential)));
        System.out.println(credential);

//        GenTemplate template = new GenTemplate();
//        template.setContent("hi ${g.author}");
//        GenEntity entity = new GenEntity();
//        entity.setName("test");
//        GenGlobal global = new GenGlobal();
//        global.setAuthor("zhi.deng");
//        Parser p = new FreeMarkerParser();
//        System.out.println(p.parse(template, entity, global));
    }
}
