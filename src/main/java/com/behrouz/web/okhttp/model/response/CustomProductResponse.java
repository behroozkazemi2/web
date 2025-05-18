package com.behrouz.web.okhttp.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import java.util.List;

/**
 * Created By Hapi KZM
 * Company: reza
 * Package: ir.mobintabaran.xima.server.api.customer.request
 * Project Name: xima-server
 * 27 May 2020
 **/

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomProductResponse {

    private int id;

    private int providerId;

    private RequestDetailResponse status;

    private String description;

    private double price;

    private List<ImageResponse> images;




    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    public int getProviderId () {
        return providerId;
    }
    public void setProviderId ( int providerId ) {
        this.providerId = providerId;
    }


    public RequestDetailResponse getStatus() {
        return status;
    }

    public void setStatus(RequestDetailResponse status) {
        this.status = status;
    }

    public String getDescription () {
        return description;
    }
    public void setDescription ( String description ) {
        this.description = description;
    }


    public double getPrice () {
        return price;
    }
    public void setPrice ( double price ) {
        this.price = price;
    }


    public List < ImageResponse > getImages () {
        return images;
    }
    public void setImages ( List < ImageResponse > images ) {
        this.images = images;
    }


    public CustomProductResponse() {
    }
}