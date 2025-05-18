package com.behrouz.web.configuration;

import com.behrouz.web.component.WebCartComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

/**
 * Created By Hapi KZM
 * Package ir.mobintabaran.xima.webapplication.controller
 * Project xima-webapplication
 * 20 January 2019 2:45 PM
 **/

@EnableWebMvc
@Configuration
@ComponentScan
public class WebApiConfigurer implements WebMvcConfigurer {


    @Autowired
    private WebCartComponent webCartComponent;

    @Autowired
    private CookieInterceptor cookieInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(cookieInterceptor).excludePathPatterns("/assets/**");

    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**", "/files/**")
                .addResourceLocations(
                        "classpath:/META-INF/resources/",
                        "classpath:/resources/",
                        "classpath:/static/",
                        "classpath:/public/",
                        "file:/opt/java/behta_shop/images/"
                ).setCacheControl(CacheControl.maxAge(1, TimeUnit.DAYS));;
    }


}
