package com.behrouz.web.rest;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class VerifyForm {

    @NotNull( message = "موبایل الزامی است" )
    @Size( min=11 , max=11 , message = "شماره موبایل 11 رقمی است" )
    private String mobile;

    @NotNull( message = "کد تایید الزامی است" )
    @Size( min=5 , max=5 , message = "کد تایید 5 رقمی است" )
    private String verify_code;


    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getVerify_code() {
        return verify_code;
    }
    public void setVerify_code(String verify_code) {
        this.verify_code = verify_code;
    }
}
