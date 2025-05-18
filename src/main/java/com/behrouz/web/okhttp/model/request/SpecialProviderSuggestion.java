package com.behrouz.web.okhttp.model.request;

import com.behrouz.web.okhttp.model.IdName;

import java.util.Date;

/**
 * Created by: HapiKZM
 **/

public class SpecialProviderSuggestion {

    private long id;

    private IdName provider;

    private int providerImage;

    private float rate;

    private IdName unit;

    private double countValue;

    private long unitAmount;

    private long extraAmount;

    private long finalAmount;

    private String description;

    private int creationHour;

    private Date date;


    public SpecialProviderSuggestion() {
    }

    public SpecialProviderSuggestion(long id, IdName provider, IdName unit, double countValue, long unitAmount, long extraAmount, long finalAmount, String description, int creationHour, Date date) {
        this.id = id;
        this.provider = provider;
        this.unit = unit;
        this.countValue = countValue;
        this.unitAmount = unitAmount;
        this.extraAmount = extraAmount;
        this.finalAmount = finalAmount;
        this.description = description;
        this.creationHour = creationHour;
        this.date = date;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }



    public IdName getProvider() {
        return provider;
    }
    public void setProvider(IdName provider) {
        this.provider = provider;
    }


    public float getRate() {
        return rate;
    }
    public void setRate(float rate) {
        this.rate = rate;
    }


    public int getProviderImage() {
        return providerImage;
    }
    public void setProviderImage(int providerImage) {
        this.providerImage = providerImage;
    }


    public IdName getUnit() {
        return unit;
    }
    public void setUnit(IdName unit) {
        this.unit = unit;
    }


    public double getCountValue() {
        return countValue;
    }
    public void setCountValue(double countValue) {
        this.countValue = countValue;
    }



    public long getUnitAmount() {
        return unitAmount;
    }
    public void setUnitAmount(long unitAmount) {
        this.unitAmount = unitAmount;
    }



    public long getExtraAmount() {
        return extraAmount;
    }
    public void setExtraAmount(long extraAmount) {
        this.extraAmount = extraAmount;
    }



    public long getFinalAmount() {
        return finalAmount;
    }
    public void setFinalAmount(long finalAmount) {
        this.finalAmount = finalAmount;
    }



    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }



    public int getCreationHour() {
        return creationHour;
    }
    public void setCreationHour(int creationHour) {
        this.creationHour = creationHour;
    }



    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
}