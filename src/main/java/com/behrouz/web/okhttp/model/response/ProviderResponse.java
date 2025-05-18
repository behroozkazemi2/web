package com.behrouz.web.okhttp.model.response;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.behrouz.web.util.Thymeleaf.ThymeleafBase64Model;
import com.behrouz.web.util.Thymeleaf.ThymeleafFormatText;

import java.util.List;

/**
 * Created By Hapi KZM
 * Package ir.mobintabaran.xima.server.api.customer.response
 * Project server
 * 30 September 2018 14:13
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProviderResponse implements ThymeleafBase64Model, ThymeleafFormatText {

    private int id;

    private String name;

    private float rate;

    private long commentNumber;

    private int imageId;

    private float discount;


    private String shortDescription;

    private String fullDescription;

    private List< RequestDetailResponse > categories;

    private List<String> phone;

    private String address;

    private int status; // is provider active or not ?

    private List<RequestDetailResponse> tags; // not using now
    private int region;


    public int getId() {
        return id;
    }
    public void setId( int id ) {
        this.id = id;
    }


    public String getName() {
        return name;
    }
    public void setName( String name ) {
        this.name = name;
    }


    public float getRate() {
        return rate;
    }
    public void setRate( float rate ) {
        this.rate = rate;
    }


    public int getImageId() {
        return imageId;
    }
    public void setImageId( int imageId ) {
        this.imageId = imageId;
    }


    public float getDiscount() {
        return discount;
    }
    public void setDiscount( float offPercent ) {
        this.discount = offPercent;
    }


    public String getShortDescription () {
        return shortDescription;
    }
    public void setShortDescription ( String shortDescription ) {
        this.shortDescription = shortDescription;
    }


    public String getFullDescription () {
        return fullDescription;
    }
    public void setFullDescription ( String fullDescription ) {
        this.fullDescription = fullDescription;
    }


    public List < RequestDetailResponse > getCategories () {
        return categories;
    }
    public void setCategories ( List < RequestDetailResponse > categories ) {
        this.categories = categories;
    }


    public List < String > getPhone () {
        return phone;
    }
    public void setPhone ( List < String > phone ) {
        this.phone = phone;
    }


    public String getAddress () {
        return address;
    }
    public void setAddress ( String address ) {
        this.address = address;
    }


    public int getStatus () {
        return status;
    }
    public void setStatus ( int status ) {
        this.status = status;
    }


    public List < RequestDetailResponse > getTags () {
        return tags;
    }
    public void setTags ( List < RequestDetailResponse > tags ) {
        this.tags = tags;
    }

    public long getCommentNumber() {
        return commentNumber;
    }
    public void setCommentNumber(long commentNumber) {
        this.commentNumber = commentNumber;
    }

    public int getRegion() {
        return region;
    }
    public void setRegion(int region) {
        this.region = region;
    }
}
