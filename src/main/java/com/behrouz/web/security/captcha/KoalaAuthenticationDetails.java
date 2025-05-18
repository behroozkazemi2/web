package com.behrouz.web.security.captcha;

import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created By Hapi KZM
 * Package ir.mobintabaran.xima.server.security.captcha
 * Project Koala Server
 * 09 September 2018 13:33
 **/
public class KoalaAuthenticationDetails {

    public static final String CAPTCHA_PARAMETER_NAME = "login xima web app";

    private String mobile = "mobile";
    private String verifyCode = "verifyCode";
    private String captcha = "captcha";

    KoalaAuthenticationDetails(HttpServletRequest request) {
        this.mobile = request.getParameter("mobile");
        this.verifyCode = request.getParameter("verifyCode");
        this.captcha = (String) WebUtils.getSessionAttribute(request, CAPTCHA_PARAMETER_NAME);
    }

//    KoalaAuthenticationDetails(boolean isAutoLogin) {
//        if(isAutoLogin)
//            answer = captcha = "auto_login";
//    }

//    public boolean isCorrect() {
//        return answer != null && captcha != null && answer.toLowerCase().equals(captcha.toLowerCase());
//    }


    public String getMobile() {
        return mobile;
    }

    public String getverifyCode() {
        return verifyCode;
    }

}
