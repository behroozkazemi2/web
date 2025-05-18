package com.behrouz.web.rest.request;

import java.util.List;

public class CartAddProductRequest {

    private long id;

    private float count;
    private long addressId;

    private String userDescription;


    public CartAddProductRequest(WebCartRedis e ) {
        this.id = e.getId();
        this.count = e.getCount();
        this.userDescription = e.getUserDescription();

    }
    public CartAddProductRequest(WebCartRedis e , long addressId) {
        this.id = e.getId();
        this.count = e.getCount();
        this.userDescription = e.getUserDescription();
        this.addressId = addressId;

    }

    public CartAddProductRequest(long id, float count, String userDescription, List<Integer> subFeature) {
        this.id = id;
        this.count = count;
        this.userDescription = userDescription;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public float getCount() {
        return count;
    }
    public void setCount(float count) {
        this.count = count;
    }

    public long getAddressId() {
        return addressId;
    }
    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }

    public String getUserDescription() {
        return userDescription;
    }
    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }
}