package com.tave_app_1.senapool.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

//    @Value("${post.path}")
//    private String postUploadFolder;
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry){
//        WebMvcConfigurer.super.addResourceHandlers(registry);;
//
//        registry.addResourceHandler("/upload/**")
//                .addResourceLocations("file:///" + postUploadFolder)
//                .resourceChain(true)
//                .addResolver(new PathResourceResolver());
//    }

}
