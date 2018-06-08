package org.gecko.config.web;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean characterEncodingFilter() {
        FilterRegistrationBean filter = new FilterRegistrationBean(new CharacterEncodingFilter());
        filter.addUrlPatterns("/*");
        filter.addInitParameter("encoding", "UTF-8");
        filter.addInitParameter("forceEncoding", "true");
        return filter;
    }

    /*------------------------------------ Shiro ----------------------------------------*/
    //@Bean
//    public FilterRegistrationBean shiroFilter() {
//        FilterRegistrationBean filter = new FilterRegistrationBean(new DelegatingFilterProxy());
//        filter.setAsyncSupported(true);
//        filter.addUrlPatterns("/*");
//        filter.addInitParameter("targetFilterLifecycle", "true");
//        return filter;
//    }
    /*------------------------------------ SSO ----------------------------------------*/

//	@Bean
//	public FilterRegistrationBean ssoLoginFilter() {
//		FilterRegistrationBean filter = new FilterRegistrationBean(new SSOClientFilterInterceptUt());
//		filter.addUrlPatterns("/*");
//		filter.addInitParameter("exclude", "/api/*,/appLogout");
//		return filter;
//	}
//
//	@Bean
//	public FilterRegistrationBean ssoLogOutFilter() {
//		FilterRegistrationBean filter = new FilterRegistrationBean(new SSOClientLogoutFilter());
//		filter.addUrlPatterns("/appLogout");
//		return filter;
//	}

	/*------------------------------------ Druid ----------------------------------------*/

    @Bean
    public FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean filter = new FilterRegistrationBean(new WebStatFilter());
        filter.addUrlPatterns("/*");
        filter.addInitParameter("exclude", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
        return filter;
    }

    @Bean
    public ServletRegistrationBean druidStatViewServlet() {
        return new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
    }

}
