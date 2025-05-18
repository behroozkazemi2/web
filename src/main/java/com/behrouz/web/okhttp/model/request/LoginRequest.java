package com.behrouz.web.okhttp.model.request;

/**
 * Created By Hapi KZM
 * Package ir.mobintabaran.xima.webapplication.okhttp.model.request
 * Project xima-webapplication
 * 03 February 2019 4:23 PM
 **/
public class LoginRequest {

    private String mobile;

    private String imei;

    public LoginRequest(String mobile, String cookie) {
        this.mobile = mobile;
        this.imei = cookie;
    }

    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public String getImei() {
        return imei;
    }
    public void setImei(String imei) {
        this.imei = imei;
    }
}
