package com.behrouz.web.rest;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created By Hapi KZM
 * Package ir.mobintabaran.xima.webapplication.rest
 * Project xima-webapplication
 * 04 February 2019 1:26 PM
 **/
public class LoginForm {

    @NotNull( message = "موبایل الزامی است" )
    @Size( min=11 , max=11 , message = "شماره موبایل 11 رقمی است" )
    private String mobile;

    @NotNull( message = "کد امنیتی الزامی است" )
    @Size( min = 4 , max = 4 , message = "کد امنیتی 6 رقمی است" )
    private String captcha;


    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public String getCaptcha() {
        return captcha;
    }
    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

}
