package com.behrouz.web.controller.login;

public class RequestListRequest {

    private String firstName;

    private String lastName;

    private boolean genderMen;

    private String mobile;

    public RequestListRequest(){

    }

    public RequestListRequest(String firstName, String lastName, boolean genderMen, String mobile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.genderMen = genderMen;
        this.mobile = mobile;
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


    public boolean isGenderMen() {
        return genderMen;
    }
    public void setGenderMen(boolean genderMen) {
        this.genderMen = genderMen;
    }


    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
