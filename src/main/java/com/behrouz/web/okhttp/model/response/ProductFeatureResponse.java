package com.behrouz.web.okhttp.model.response;

import java.util.List;

public class ProductFeatureResponse {

    protected int id;

    protected int category;

    protected String name;

    protected List<ProductSubFeatureResponse> subFeature;

    public ProductFeatureResponse() {
    }



    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }



    public int getCategory() {
        return category;
    }
    public void setCategory(int category) {
        this.category = category;
    }



    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }



    public List<ProductSubFeatureResponse> getSubFeature() {
        return subFeature;
    }
    public void setSubFeature(List<ProductSubFeatureResponse> subFeature) {
        this.subFeature = subFeature;
    }


}