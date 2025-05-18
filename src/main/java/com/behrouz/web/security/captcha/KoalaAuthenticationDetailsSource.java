package com.behrouz.web.security.captcha;


import org.springframework.security.authentication.AuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;

/**
 * Created By Hapi KZM
 * Package ir.mobintabaran.xima.server.security.captcha
 * Project Koala Server
 * 09 September 2018 13:35
 **/
public class KoalaAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, KoalaAuthenticationDetails> {

    @Override
    public KoalaAuthenticationDetails buildDetails(HttpServletRequest context) {
        return new KoalaAuthenticationDetails(context);
    }
}
