package com.behrouz.web.configuration;


import com.behrouz.web.values.Links;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApkConfiguration {


    @Bean("directLink")
    public String directLink(){
        return Links.APK_LINK;
    }
    @Bean("cafebazaarLink")
    public String cafebazaarLink(){
        return Links.APK_CAFFE_BAZZAR;
    }
    @Bean("myketLink")
    public String myketLink(){
        return Links.APK_MYKET;
    }

}
