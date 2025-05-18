package com.behrouz.web.rest.request;

/**
 * Created by: HapiKZM
 **/
public class ProviderShortResponse {

    private int id;

    private int logoId;

    private float rate;

    private String name;

    private String description;

    private int commentNumber;

    public ProviderShortResponse() {
    }

    public ProviderShortResponse(int id, int logoId, float rate, String name, String description, int commentNumber) {
        this.id = id;
        this.logoId = logoId;
        this.rate = rate;
        this.name = name;
        this.description = description;
        this.commentNumber = commentNumber;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    public int getLogoId() {
        return logoId;
    }
    public void setLogoId(int logoId) {
        this.logoId = logoId;
    }


    public float getRate() {
        return rate;
    }
    public void setRate(float rate) {
        this.rate = rate;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


    public int getCommentNumber() {
        return commentNumber;
    }
    public void setCommentNumber(int commentNumber) {
        this.commentNumber = commentNumber;
    }
}

