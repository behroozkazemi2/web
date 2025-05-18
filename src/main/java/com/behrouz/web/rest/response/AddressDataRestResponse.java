package com.behrouz.web.rest.response;

public class AddressDataRestResponse {
    private long id;
    private String address;
    private String city;
    private String userName;
    private String mobile;

    public AddressDataRestResponse() {
    }

    public AddressDataRestResponse(long id, String address, String city, String userName, String mobile) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.userName = userName;
        this.mobile = mobile;
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }


    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }


    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }


    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
