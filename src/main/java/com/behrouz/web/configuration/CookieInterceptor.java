package com.behrouz.web.configuration;


import com.behrouz.web.redis.RedisCart;
import com.behrouz.web.rest.request.WebCartRedis;
import com.behrouz.web.strategy.StringGenerator;
import com.behrouz.web.util.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


/**
 * Created By Hapi KZM
 * Package ir.mobintabaran.xima.webapplication.controller
 * Project xima-webapplication
 * 20 January 2019 2:45 PM
 **/

@Component
public class CookieInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisCart redisCart;

    public static final String KEY_COOKIE = "mt-xima-web-app";

    @Override
    public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler) {

        List<Cookie> cookieList =
                request == null || request.getCookies() == null ? new ArrayList<>() : Arrays.asList(request.getCookies());

        Optional<Cookie > optionalCookie =
                cookieList.stream().filter(e -> e.getName().equals(KEY_COOKIE)).findAny();


        if(!optionalCookie.isPresent()) {
            String cookie = StringGenerator.generateCookie();
            Cookie cookieValue = new Cookie(KEY_COOKIE, cookie);
            response.addCookie(cookieValue);
        }
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        if (response.getStatus() != HttpServletResponse.SC_OK || modelAndView == null){
            return;
        }

        String cookie = CookieUtils.getCookieToken( request );

        List<WebCartRedis> cart = redisCart.getCart(cookie);
        modelAndView.addObject("liveCart" , cart);
    }
}
