package com.behrouz.web.rest;

import com.behrouz.web.okhttp.model.IdName;
import com.behrouz.web.okhttp.model.request.SpecialProviderSuggestion;

/**
 * Created by: HapiKZM
 **/
public class SpecialProductPriceRestResponse {

    private long id;

    private int providerImage;

    private IdName provider;

    private float providerRate;

    private IdName providerRegion;

    private String description;

    private long price;

    public SpecialProductPriceRestResponse() {
    }


    public SpecialProductPriceRestResponse(int id, int providerImage, IdName provider, float providerRate, IdName providerRegion, String description, long price) {
        this.id = id;
        this.providerImage = providerImage;
        this.provider = provider;
        this.providerRate = providerRate;
        this.providerRegion = providerRegion;
        this.description = description;
        this.price = price;
    }

    public SpecialProductPriceRestResponse(SpecialProviderSuggestion s) {
        this.id = s.getId();
        this.providerImage = s.getProviderImage();
        this.provider = s.getProvider();
        this.providerRate = s.getRate();
//        this.providerRegion = s.get;
        this.description = s.getDescription();
        this.price = s.getFinalAmount();
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }


    public int getProviderImage() {
        return providerImage;
    }
    public void setProviderImage(int providerImage) {
        this.providerImage = providerImage;
    }


    public IdName getProvider() {
        return provider;
    }
    public void setProvider(IdName provider) {
        this.provider = provider;
    }


    public float getProviderRate() {
        return providerRate;
    }
    public void setProviderRate(float providerRate) {
        this.providerRate = providerRate;
    }


    public IdName getProviderRegion() {
        return providerRegion;
    }
    public void setProviderRegion(IdName providerRegion) {
        this.providerRegion = providerRegion;
    }


    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


    public long getPrice() {
        return price;
    }
    public void setPrice(long price) {
        this.price = price;
    }
}
