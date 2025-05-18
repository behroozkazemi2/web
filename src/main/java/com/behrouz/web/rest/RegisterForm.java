package com.behrouz.web.rest;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created By Hapi KZM
 * Package ir.mobintabaran.xima.webapplication.rest
 * Project xima-webapplication
 * 06 February 2019 11:08 AM
 **/
public class RegisterForm {

    @NotNull( message = "نام الزامی است" )
    @Size( min=1 , max=30 , message = "نام بین 1 تا 30 حرف است" )
    private String name;

    @NotNull( message = "نام خانوادگی الزامی است" )
    @Size( min=1 , max=30 , message = "نام خانوادگی بین 1 تا 30 حرف است" )
    private String familyname;

    @NotNull( message = "جنسیت الزامی است" )
    private boolean gender;

    @NotNull( message = "موبایل الزامی است" )
    @Size( min=11 , max=11 , message = "شماره موبایل 11 رقمی است" )
    private String mobile;

    @NotNull( message = "کد امنیتی الزامی است" )
    @Size( min = 4 , max = 4 , message = "کد امنیتی 4 رقمی است" )
    private String captcha;



    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }



    public String getFamilyname() {
        return familyname;
    }
    public void setFamilyname(String familyname) {
        this.familyname = familyname;
    }



    public boolean getGender() {
        return gender;
    }
    public void setGender(boolean gender) {
        this.gender = gender;
    }


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
