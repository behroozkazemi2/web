package com.behrouz.web.security.session.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created By Hapi KZM
 * 11 July 2018
 **/


public class UserSessionDetail implements Serializable {


    private static final long serialVersionUID = -1948456964430422267L;

    private String firstName;

    private String lastName;

    private String mobile;

    private String nationalCode;

    private byte[] avatar;

    private int inCartItems;


    private String token;

    private Date loginDate;

    private boolean registered;

    public UserSessionDetail() {
    }


    public UserSessionDetail(String firstName, String lastName, String mobile, byte[] avatar, int inCartItems, String token, Date loginDate, String  nationalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.avatar = avatar;
        this.inCartItems = inCartItems;
        this.token = token;
        this.nationalCode = nationalCode;
        this.loginDate = loginDate;
        this.registered = true;
    }

    public UserSessionDetail(boolean registered, String token) {
        this.registered = registered;
        this.token = token;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getInCartItems() {
        return inCartItems;
    }
    public void setInCartItems(int inCartItems) {
        this.inCartItems = inCartItems;
    }

    public byte[] getAvatar() {
        return avatar;
    }
    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public Date getLoginDate() {
        return loginDate;
    }
    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public boolean isRegistered() {
        return registered;
    }
    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public String getNationalCode() {
        return nationalCode;
    }
    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }
}
