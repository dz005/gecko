package org.gecko.config.web;

import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeetlConfig {

    private static final String PREFIX = "WEB-INF/views/";
    private static final String SUFFIX = ".html";
    private static final String CONTENT_TYPE = "text/html;charset=UTF-8";

    @Bean
    public BeetlSpringViewResolver getBeetlSpringViewResolver() {
        BeetlGroupUtilConfiguration config = new BeetlGroupUtilConfiguration();
        config.setResourceLoader(new ClasspathResourceLoader(this.getClass().getClassLoader(), PREFIX));
        config.init();
        BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
        beetlSpringViewResolver.setConfig(config);
        beetlSpringViewResolver.setSuffix(SUFFIX);
        beetlSpringViewResolver.setContentType(CONTENT_TYPE);
        beetlSpringViewResolver.setOrder(0);
        return beetlSpringViewResolver;
    }

}
