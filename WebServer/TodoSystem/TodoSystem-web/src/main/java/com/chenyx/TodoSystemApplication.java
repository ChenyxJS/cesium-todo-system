package com.chenyx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


/**
 *
 * @author Administrator
 */
@MapperScan({"com.chenyx.mapper" , "com.baomidou.mybatisplus.samples.quickstart.mapper"})
@SpringBootApplication
public class TodoSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(TodoSystemApplication.class, args);
    }
    @Bean
    public InternalResourceViewResolver setupViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;

    }
}
