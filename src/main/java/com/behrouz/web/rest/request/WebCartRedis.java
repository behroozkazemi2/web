package com.behrouz.web.rest.request;

import com.behrouz.web.okhttp.model.IdName;
import com.behrouz.web.okhttp.model.response.CartItemResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.behrouz.web.okhttp.model.response.ProductDetailResponse;


@JsonIgnoreProperties(ignoreUnknown = true)
public class WebCartRedis {


    private long id;

    private String name;

    private String shortDescription;

    private long primitiveAmount;

    private float count;

    private long image;

    private IdName unit;

    private double unitStep;

    private double minAllow;

    private double maxAllow;

    private float offPercent;

    private String userDescription;

    private long totalAmount;


    public WebCartRedis(long productProviderId) {
        this.id = productProviderId;
    }

    public WebCartRedis(long productProviderId, float count) {
        this.id = productProviderId;
        this.count = count;
    }

    public WebCartRedis(long productProviderId, float count, String userDescription) {
        this.id = productProviderId;
        this.count = count;
        this.userDescription = userDescription;
    }

    public WebCartRedis() {
    }

    public WebCartRedis(CartItemResponse e) {
        this.id = e.getId();
        this.name = e.getName();
        this.shortDescription = e.getShortDescription();
        this.primitiveAmount = e.getPrimitiveAmount();
        this.count = e.getInCartCount();
        this.image = (e.getImages() != null && !e.getImages().isEmpty()) ? e.getImages().get(0) : 0;
        this.unit = e.getUnit();
        this.offPercent = e.getOffPercent();
        this.userDescription = e.getUserDescription();
        this.totalAmount = e.getTotalAmount();
        this.unitStep = e.getUnitStep();
        this.maxAllow = e.getMaxAllow();
        this.minAllow = e.getMinAllow();
    }

    public WebCartRedis(ProductDetailResponse d, float inCart, String userDescription) {
        this.id = d.getId();
        this.name = d.getName();
        this.shortDescription = d.getShortDescription();
        this.primitiveAmount = d.getPrimitiveAmount();
        this.count = inCart;
        this.image = (d.getImages() != null && !d.getImages().isEmpty()) ? d.getImages().get(0) : 0;
        this.unit = d.getUnit();
        this.offPercent = d.getOffPercent();
        this.userDescription = userDescription;
        this.totalAmount = (long) (inCart * d.getFinalAmount());
        this.unitStep = d.getUnitStep();
        this.maxAllow = d.getMaxAllow();
        this.minAllow = d.getMinAllow();
    }



    public long getProductProviderId() {
        return id;
    }

    public void setProductProviderId(long id) {
        this.id = id;
    }


    public float getCount() {
        return count;
    }

    public void setCount(float count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public float getUnitPrice() {
        return primitiveAmount;
    }

    public void setUnitPrice(long primitiveAmount) {
        this.primitiveAmount = primitiveAmount;
    }

    public long getImage() {
        return image;
    }

    public void setImage(long image) {
        this.image = image;
    }

    public IdName  getUnitName() {
        return unit;
    }

    public void setUnitName(IdName unit) {
        this.unit = unit;
    }

    public float getOffPercent() {
        return offPercent;
    }

    public void setOffPercent(float offPercent) {
        this.offPercent = offPercent;
    }

    public String getUserDescription () {
        return userDescription;
    }

    public void setUserDescription ( String userDescription ) {
        this.userDescription = userDescription;
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }


    public long getPrimitiveAmount() {
        return primitiveAmount;
    }
    public void setPrimitiveAmount(long primitiveAmount) {
        this.primitiveAmount = primitiveAmount;
    }


    public IdName getUnit() {
        return unit;
    }
    public void setUnit(IdName unit) {
        this.unit = unit;
    }


    public long getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getUnitStep() {
        return unitStep;
    }

    public void setUnitStep(double unitStep) {
        this.unitStep = unitStep;
    }

    public double getMinAllow() {
        return minAllow;
    }
    public void setMinAllow(double minAllow) {
        this.minAllow = minAllow;
    }

    public double getMaxAllow() {
        return maxAllow;
    }
    public void setMaxAllow(double maxAllow) {
        this.maxAllow = maxAllow;
    }

    @Override
    public String toString() {
        return "WebCartRedis{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", primitiveAmount=" + primitiveAmount +
                ", count=" + count +
                ", image=" + image +
                ", unit=" + unit +
                ", unitStep=" + unitStep +
                ", minAllow=" + minAllow +
                ", maxAllow=" + maxAllow +
                ", offPercent=" + offPercent +
                ", userDescription='" + userDescription + '\'' +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
