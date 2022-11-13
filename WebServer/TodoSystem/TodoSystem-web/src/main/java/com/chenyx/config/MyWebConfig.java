package com.chenyx.config;

import com.chenyx.filter.permission.MiniPermissionFilter;
import com.chenyx.filter.permission.PermissionFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class MyWebConfig implements WebMvcConfigurer {
    @Resource
    private PermissionFilter permissionFilter;
    @Resource
    private MiniPermissionFilter miniPermissionFilter;
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/zxc/foo").setViewName("foo");
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public FilterRegistrationBean filterRegist() {
        FilterRegistrationBean frBean = new FilterRegistrationBean();
        frBean.setFilter(permissionFilter);
        frBean.addUrlPatterns("/admin/*");
        return frBean;
    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public FilterRegistrationBean filterMiniRegist() {
        FilterRegistrationBean frBean = new FilterRegistrationBean();
        frBean.setFilter(miniPermissionFilter);
        frBean.addUrlPatterns("/mp/*");
        return frBean;
    }
}
