package com.behrouz.web.okhttp.model.response;


/**
 * Created By Hapi KZM
 * Package ir.mobintabaran.xima.server.api.customer.response
 * Project server
 * 08 October 2018 13:49
 **/
public class CartItemResponse  extends ProductDetailResponse{

    private int orderId;

    private long totalAmount;


    public CartItemResponse() {
    }

    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public long getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }

}