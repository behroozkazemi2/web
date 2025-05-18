package com.behrouz.web.rest.request;

import com.behrouz.web.okhttp.model.response.CommentResponse;
import com.behrouz.web.util.date.PersianDateUtil;

/**
 * Created by: HapiKZM
 **/
public class CommentResponseRequest {

    private int id;

    private String name;

    private float rate;

    private String date;

    private String text;

    public CommentResponseRequest() {
    }

    public CommentResponseRequest(String name, float rate, String date, String text) {
        this.name = name;
        this.rate = rate;
        this.date = date;
        this.text = text;
    }

    public CommentResponseRequest(CommentResponse c) {
        this.name = c.getCustomerName();
        this.rate = c.getRate();
        this.date = PersianDateUtil.getPersianDate(c.getDate().getTime());
        this.text = c.getText();
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


    public float getRate() {
        return rate;
    }
    public void setRate(float rate) {
        this.rate = rate;
    }


    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }


    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
