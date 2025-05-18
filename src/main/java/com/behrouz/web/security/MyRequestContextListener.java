package com.behrouz.web.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;

import javax.servlet.annotation.WebListener;

/**
 * Created By Hapi KZM
 * Package ir.mobintabaran.xima.webapplication.security
 * Project xima-webapplication
 * 06 February 2019 2:55 PM
 **/
@Configuration
@WebListener
public class MyRequestContextListener extends RequestContextListener {

}