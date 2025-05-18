package com.behrouz.web.rest;

import com.behrouz.web.okhttp.model.response.SpecialProductDigestResponse;
import com.behrouz.web.okhttp.model.IdName;

import java.util.Date;
import java.util.List;

/**
 * Created by: HapiKZM
 **/
public class SpecialProductRestResponse {

    private int id;

    private IdName category;

    private IdName provider;

    private Date insetDate;

    private Date submitDate;

    private IdName status;

    private String description;

    private List<Integer> images;


    public SpecialProductRestResponse() {
    }

    public SpecialProductRestResponse(int id, IdName copategory, IdName provider, Date insetDate, Date submitDate, IdName status, String description, List<Integer> images) {
        this.id = id;
        this.category = copategory;
        this.provider = provider;
        this.insetDate = insetDate;
        this.submitDate = submitDate;
        this.status = status;
        this.description = description;
        this.images = images;
    }

    public SpecialProductRestResponse(SpecialProductDigestResponse e) {
        this.id = e.getId();
        this.category = e.getCategory();
        this.provider = e.getProvider();
        this.insetDate = e.getDate();
        this.submitDate = submitDate;
        this.status = e.getStatus();
        this.description = e.getDescription();
        this.images = e.getImages();
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    public IdName getCategory() {
        return category;
    }
    public void setCategory(IdName category) {
        this.category = category;
    }


    public IdName getProvider() {
        return provider;
    }
    public void setProvider(IdName provider) {
        this.provider = provider;
    }


    public Date getInsetDate() {
        return insetDate;
    }
    public void setInsetDate(Date insetDate) {
        this.insetDate = insetDate;
    }


    public Date getSubmitDate() {
        return submitDate;
    }
    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }


    public IdName getStatus() {
        return status;
    }
    public void setStatus(IdName status) {
        this.status = status;
    }


    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


    public List<Integer> getImages() {
        return images;
    }
    public void setImages(List<Integer> images) {
        this.images = images;
    }
}

