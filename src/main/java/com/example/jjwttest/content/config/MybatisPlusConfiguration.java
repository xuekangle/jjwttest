package com.example.jjwttest.content.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.baomidou.mybatisplus.annotation.DbType.MYSQL;

/**
 * mybatis-plus 配置类
 * @author xuekangle
 * @date 2019-05-22 23:08
 */
@Configuration
@MapperScan("com.example.jjwttest.content.mapper*")
public class MybatisPlusConfiguration {
    /**
     * mybatis-plus分页插件
     * 文档 ：http://mp-baomidou.com
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setDialectType(MYSQL.getDb());
//        paginationInterceptor.setOptimizeType(Optimize.JSQLPARSER.getOptimize());
        return paginationInterceptor;
    }
}
