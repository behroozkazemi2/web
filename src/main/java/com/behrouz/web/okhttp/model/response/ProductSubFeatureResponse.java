package com.behrouz.web.okhttp.model.response;

import com.behrouz.web.util.Thymeleaf.ThymeleafFormatPrice;

public class ProductSubFeatureResponse implements ThymeleafFormatPrice {

    private int id;

    private String name;

    private long amount;

    private boolean unitDepended;


    public ProductSubFeatureResponse() {
    }

    public ProductSubFeatureResponse(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProductSubFeatureResponse(int id, String name, long amount, boolean unitDepended) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.unitDepended = unitDepended;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }



    public long getAmount() {
        return amount;
    }
    public void setAmount(long amount) {
        this.amount = amount;
    }



    public boolean isUnitDepended() {
        return unitDepended;
    }
    public void setUnitDepended(boolean unitDepended) {
        this.unitDepended = unitDepended;
    }
}