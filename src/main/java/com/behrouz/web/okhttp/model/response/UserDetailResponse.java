package com.behrouz.web.okhttp.model.response;

import java.util.Date;

/**
 * Created By Hapi KZM
 * Package ir.mobintabaran.xima.server.api.customer.response
 * Project server
 * 27 September 2018 10:50
 **/
public class UserDetailResponse {

    private String firstName;


    private String lastName;

    private String mobile;
    private String nationalCode;

    private byte[] avatar;

    private int inCartItems;

    private Date birthDate;

    private long addressId;

    public UserDetailResponse() {
    }


    public UserDetailResponse(String firstName, String lastName, byte[] avatar, int inCartItems) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
        this.inCartItems = inCartItems;
    }

    public UserDetailResponse(String firstName, String lastName, String mobile, byte[] avatar, int inCartItems , long addressId ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.avatar = avatar;
        this.inCartItems = inCartItems;
        this.addressId = addressId;
    }
    public UserDetailResponse(String firstName, String lastName, String mobile, byte[] avatar, int inCartItems  ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.avatar = avatar;
        this.inCartItems = inCartItems;
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


    public String getMobile() {
        return mobile;
    }
    public void setMobile( String mobile ) {
        this.mobile = mobile;
    }


    public byte[] getAvatar() {
        return avatar;
    }
    public void setAvatar( byte[] avatar ) {
        this.avatar = avatar;
    }


    public int getInCartItems () {
        return inCartItems;
    }
    public void setInCartItems ( int inCartItems ) {
        this.inCartItems = inCartItems;
    }



    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }


    public long getAddressId() {
        return addressId;
    }
    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }

    public String getNationalCode() {
        return nationalCode;
    }
    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }
}
