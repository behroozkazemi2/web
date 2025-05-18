package com.behrouz.web.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import com.behrouz.web.rest.KoalaErrorResponse;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by: Hapi KZM
 * Company: Mobintabaran
 * Package: Mobintabaran
 * Project Name: shop-template
 * 14 June 2020
 **/
public class BaseApi {


    protected List<KoalaErrorResponse> checkValidation(BindingResult result) {
        return result.getAllErrors().stream().map(
                e -> {
                    try {
                        Field heh = e.getClass().getDeclaredField("field");
                        heh.setAccessible(true);
                        return new KoalaErrorResponse( (String) heh.get(e) , e.getDefaultMessage() );
                    } catch (IllegalAccessException | NoSuchFieldException e1) {
                        e1.printStackTrace();
                        return null;
                    }
                }
        ).collect(Collectors.toList());
    }

    protected boolean isAuthenticated() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken);

    }

}
