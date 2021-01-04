package com.easycc.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    @Autowired
    private Environment env;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(
                "classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations(
                "classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations(
                "classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/static/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/static/files/**").addResourceLocations("file:" + env.getProperty("local.uploadPath"));
        super.addResourceHandlers(registry);
    }

    @Resource
    private TokenInterceptor tokenInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**");
    }
}
