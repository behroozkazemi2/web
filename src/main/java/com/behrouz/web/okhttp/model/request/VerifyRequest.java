package com.behrouz.web.okhttp.model.request;

/**
 * Created By Hapi KZM
 * Package ir.mobintabaran.xima.server.api.customer.request
 * Project server
 * 18 September 2018 10:55
 **/
public class VerifyRequest {

    private String mobile;

    private String smsCode;

    private String imei;

    private String notificationId;

    private boolean android;


    public VerifyRequest(String mobile, String smsCode, String imei, String notificationId, boolean android) {
        this.mobile = mobile;
        this.smsCode = smsCode;
        this.imei = imei;
        this.notificationId = notificationId;
        this.android = android;
    }

    public VerifyRequest(String mobile, String smsCode) {
        this.mobile = mobile;
        this.smsCode = smsCode;
    }

    public String getMobile() {
        return mobile;
    }
    public void setMobile( String mobile ) {
        this.mobile = mobile;
    }


    public String getSmsCode() {
        return smsCode;
    }
    public void setSmsCode( String smsCode ) {
        this.smsCode = smsCode;
    }


    public String getImei() {
        return imei;
    }
    public void setImei( String imei ) {
        this.imei = imei;
    }


    public String getNotificationId() {
        return notificationId;
    }
    public void setNotificationId( String notificationId ) {
        this.notificationId = notificationId;
    }

    
    public boolean isAndroid() {
        return android;
    }
    public void setAndroid( boolean android ) {
        this.android = android;
    }
}
