package com.behrouz.web.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created By Hapi KZM
 * Package ir.mobintabaran.xima.webapplication.security
 * Project xima-webapplication
 * 07 February 2019 1:26 PM
 **/
public class KoalaLogoutHandler implements LogoutHandler {
    @Override
    public void logout(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            Authentication authentication) {

        try {
            httpServletResponse.sendRedirect("/logout");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
