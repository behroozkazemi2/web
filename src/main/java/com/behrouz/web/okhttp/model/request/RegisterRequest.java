package com.behrouz.web.okhttp.model.request;

import java.util.Date;

/**
 * Created By Hapi KZM
 * Package ir.mobintabaran.xima.server.api.customer.request
 * Project server
 * 16 September 2018 15:35
 **/
public class RegisterRequest {

    private String mobile;

    private String firstName;

    private String lastName;

    private boolean genderMen;

    private String inviteCode;

    private String imei;

    private Date birthDate;

    private String nationalCode;

    public RegisterRequest() {
    }

    public RegisterRequest(String mobile, String firstName, String lastName, boolean genderMen, String inviteCode, String cookie, String nationalCode) {
        this.mobile = mobile;
        this.firstName = firstName;
        this.lastName = lastName;
        this.genderMen = genderMen;
        this.inviteCode = inviteCode;
        this.imei = cookie;
        this.nationalCode = nationalCode;
    }


    public RegisterRequest( String lastName,  String firstName,boolean genderMen,String mobile,String cookie, String nationalCode){

        this.firstName = firstName;
        this.lastName = lastName;
        this.genderMen = genderMen;
        this.mobile = mobile;
        this.nationalCode = nationalCode;
        this.imei = cookie;

    }

    public String getMobile() {
        return mobile;
    }
    public void setMobile( String mobile ) {
        this.mobile = mobile;
    }


    public String getFirstName() {
        return firstName;
    }
    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }
    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }


    public boolean isGenderMen() {
        return genderMen;
    }
    public void setGenderMen( boolean genderMen ) {
        this.genderMen = genderMen;
    }


    public String getInviteCode() {
        return inviteCode;
    }
    public void setInviteCode( String inviteCode ) {
        this.inviteCode = inviteCode;
    }


    public String getImei() {
        return imei;
    }
    public void setImei( String imei ) {
        this.imei = imei;
    }


    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getNationalCode() {
        return nationalCode;
    }
    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }
}

