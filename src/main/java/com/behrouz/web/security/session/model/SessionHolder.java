package com.behrouz.web.security.session.model;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.behrouz.web.okhttp.model.response.UserDetailResponse;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created By Hapi KZM
 **/


public class SessionHolder {

    public static String getToken() {

        UserSessionDetail detail = getUserDetail();

        return detail == null ? null : detail.getToken();

    }


    public static String getFirstName() {

        UserSessionDetail detail = getUserDetail();

        return detail == null ? null : detail.getFirstName();

    }

    public static String getLastName() {

        UserSessionDetail detail = getUserDetail();

        return detail == null ? null : detail.getLastName();

    }
    public static String getMobile() {

        UserSessionDetail detail = getUserDetail();

        return detail == null ? null : detail.getMobile();

    }

    public static byte[] getAvatar() {

        UserSessionDetail detail = getUserDetail();

        return detail == null ? null : detail.getAvatar();

    }

    public static UserSessionDetail getUserDetail() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            return null;
        }

        if(auth.getDetails() instanceof UserSessionDetail) {
            return (UserSessionDetail) auth.getDetails();
        }

        return null;

    }


    public static  void setfirstName( String fName){
        UserSessionDetail detail = getUserDetail();
        detail.setFirstName(fName);

        update(detail);
    }

    private static void update(UserSessionDetail detail) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(SessionHolder.getMobile(), "", new ArrayList<>());
        token.setDetails( detail );
        setDetail(token);
    }

    public static  void setlastName(String lName){
        UserSessionDetail detail = getUserDetail();
        detail.setLastName(lName);
        update(detail);
    }


    public static void login( UserDetailResponse data, String apiToken) {


        UserSessionDetail sessionDetail =
                new UserSessionDetail(
                        data.getFirstName(),
                        data.getLastName(),
                        data.getMobile(),
                        data.getAvatar(),
                        data.getInCartItems(),
                        apiToken,
                        new Date(),
                        data.getNationalCode()
                );

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(apiToken, "", new ArrayList<>());
        token.setDetails( sessionDetail );
        setDetail(token);
    }
    public static void noLogin( String apiToken) {


        UserSessionDetail sessionDetail =
                new UserSessionDetail(
                        false,
                        apiToken
                );

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(apiToken, "", new ArrayList<>());
        token.setDetails( sessionDetail );
        setDetail(token);
    }

    private static void setDetail(UsernamePasswordAuthenticationToken token) {
        SecurityContextHolder.getContext().setAuthentication(token);
    }


    public static boolean isAuthenticated(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null && auth.isAuthenticated() && auth.getDetails() instanceof UserSessionDetail && ((UserSessionDetail) auth.getDetails()).isRegistered();
    }
}


