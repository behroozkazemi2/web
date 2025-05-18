package com.behrouz.web.okhttp.model.request;

import java.util.Date;

/**
 * Created By Hapi KZM
 **/
public class FactorRequest {


    private int addressId;

    private String offCode;
    private String userDescription;

    private Date selectedDate;

    private int timeId;
    private int paymentMethod;


    public FactorRequest() {
    }

    public FactorRequest(int addressId, String offCode, String userDescription, Date selectedDate, int timeId, int paymentMethod) {
        this.addressId = addressId;
        this.offCode = offCode;
        this.userDescription = userDescription;
        this.selectedDate = selectedDate;
        this.timeId = timeId;
        this.paymentMethod = paymentMethod;
    }

    public FactorRequest(int addressId, String discount) {
        this.addressId = addressId;
        this.offCode = discount;
    }


    public String getUserDescription() {
        return userDescription;
    }
    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public int getAddressId() {
        return addressId;
    }
    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }


    public String getOffCode() {
        return offCode;
    }
    public void setOffCode(String offCode) {
        this.offCode = offCode;
    }


    public Date getSelectedDate() {
        return selectedDate;
    }
    public void setSelectedDate(Date selectedDate) {
        this.selectedDate = selectedDate;
    }


    public int getTimeId() {
        return timeId;
    }
    public void setTimeId(int timeId) {
        this.timeId = timeId;
    }

    public int getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
