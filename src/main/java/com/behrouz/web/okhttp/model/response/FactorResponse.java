package com.behrouz.web.okhttp.model.response;


import com.behrouz.web.okhttp.model.IdNameLong;
import com.behrouz.web.option.PaymentMethodOption;
import com.behrouz.web.util.Thymeleaf.ThymeleafPersianDateModel;

import java.util.Date;
import java.util.List;

/**
 * Created By Hapi KZM
 * Company: reza
 * Package: ir.mobintabaran.xima.server.api.customer.response
 * Project Name: xima-server
 * 04 December 2018
 **/
public class FactorResponse implements ThymeleafPersianDateModel {

    private int billId;
    private PaymentMethodOption paymentMethod;

    private String trackingCode;

    private Date insertDate;
    private String insertDateString;

    private Date supposedToDeliverDate;

    private Date deliveredDate;

    private RequestDetailResponse supposedToDeliverTime;

    private RequestDetailResponse status;
    private List<IdNameLong> productsName;
    private String offCodeString;

    private float payableAmount;
    private float discountAmount;
    private float shipAmount;
    private long taxAmount;


    public int getBillId() {
        return billId;
    }
    public void setBillId(int billId) {
        this.billId = billId;
    }


    public String getTrackingCode () {
        return trackingCode;
    }
    public void setTrackingCode ( String trackingCode ) {
        this.trackingCode = trackingCode;
    }


    public Date getInsertDate () {
        return insertDate;
    }
    public void setInsertDate ( Date insertDate ) {
        this.insertDate = insertDate;
    }


    public RequestDetailResponse getStatus() {
        return status;
    }
    public void setStatus(RequestDetailResponse status) {
        this.status = status;
    }

    public long getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(long taxAmount) {
        this.taxAmount = taxAmount;
    }

    public float getPayableAmount() {
        return payableAmount;
    }
    public void setPayableAmount(float payableAmount) {
        this.payableAmount = payableAmount;
    }


    public Date getSupposedToDeliverDate() {
        return supposedToDeliverDate;
    }
    public void setSupposedToDeliverDate(Date supposedToDeliverDate) {
        this.supposedToDeliverDate = supposedToDeliverDate;
    }



    public RequestDetailResponse getSupposedToDeliverTime() {
        return supposedToDeliverTime;
    }
    public void setSupposedToDeliverTime(RequestDetailResponse supposedToDeliverTime) {
        this.supposedToDeliverTime = supposedToDeliverTime;
    }



    public Date getDeliveredDate() {
        return deliveredDate;
    }
    public void setDeliveredDate(Date deliveredDate) {
        this.deliveredDate = deliveredDate;
    }


    public float getDiscountAmount() {
        return discountAmount;
    }
    public void setDiscountAmount(float discountAmount) {
        this.discountAmount = discountAmount;
    }


    public List<IdNameLong> getProductsName() {
        return productsName;
    }
    public void setProductsName(List<IdNameLong> productsName) {
        this.productsName = productsName;
    }

    public float getShipAmount() {
        return shipAmount;
    }
    public void setShipAmount(float shipAmount) {
        this.shipAmount = shipAmount;
    }


    public String getInsertDateString() {
        return insertDateString;
    }
    public void setInsertDateString(String insertDateString) {
        this.insertDateString = insertDateString;
    }

    public String getOffCodeString() {
        return offCodeString;
    }
    public void setOffCodeString(String offCodeString) {
        this.offCodeString = offCodeString;
    }

    public PaymentMethodOption getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(PaymentMethodOption paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
