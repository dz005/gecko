package org.gecko.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.gecko.config.properties.DruidProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Mybatis 配置
 *
 * @author: dengzhi
 * @date: 2018/6/1
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "org.gecko.modular.*.mapper")
public class MybatisConfig {

    @Autowired
    private DruidProperties druidProperties;

    @Bean
    public DruidDataSource datasource() {
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        return dataSource;
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
