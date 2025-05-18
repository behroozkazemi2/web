package com.behrouz.web.configuration;

import com.behrouz.web.security.session.model.SessionHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created By Hapi KZM
 * Package ir.mobintabaran.xima.webapplication.configuration
 * Project xima-webapplication
 * 07 February 2019 11:31 AM
 **/

@Configuration
public class UserConfiguration {

    public interface StringInterface {
        String text ();
    }

    public interface ByteInterface {
        byte[] image ();
    }

    public interface BoolInterface {
        boolean bool ();
    }

    @Bean(name="FirstName")
    public StringInterface getFirstName() {
        return SessionHolder::getFirstName;
    }

    @Bean(name="LastName")
    public StringInterface getLastName() {
        return SessionHolder::getLastName;
    }


    @Bean(name="FullName")
    public StringInterface getFullName() {
        return () -> !SessionHolder.isAuthenticated() ? "" : SessionHolder.getFirstName() + " " + SessionHolder.getLastName();
    }

    @Bean(name="Avatar")
    public ByteInterface getAvatar() {
        return SessionHolder::getAvatar;
    }

    @Bean(name="IsLogin")
    public BoolInterface isLogin() {
        return () -> {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            return auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken);
        };
    }

}
