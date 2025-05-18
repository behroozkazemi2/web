package com.behrouz.web.util;


import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.behrouz.web.configuration.CookieInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.stream.Stream;

/**
 * Created By Hapi KZM
 * Package ir.mobintabaran.xima.webapplication.util
 * Project xima-webapplication
 * 04 February 2019 11:24 AM
 **/
public class CookieUtils {

    public static String getCookieToken(HttpServletRequest request) {

            if(request.getCookies() == null){
            return null;
        }
        return Stream.of(request.getCookies()).filter(
                e -> e.getName().equals(CookieInterceptor.KEY_COOKIE)
        ).findAny().map(Cookie::getValue).orElse(null);

    }

    public static String getCookieToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return getCookieToken(request);
    }

}
