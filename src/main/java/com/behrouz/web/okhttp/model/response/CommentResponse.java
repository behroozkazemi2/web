package com.behrouz.web.okhttp.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * Created by: HapiKZM
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentResponse {

    private int id;
    private int status;

    private String customerName;

    private String text;
    private String editedText;
    private String email;

    private boolean logedIn;

    private float rate;

    private Date date;

    public CommentResponse() {
    }

    public CommentResponse(int id, String customerName, String text, float rate, Date date) {
        this.id = id;
        this.customerName = customerName;
        this.text = text;
        this.rate = rate;
        this.date = date;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }


    public float getRate() {
        return rate;
    }
    public void setRate(float rate) {
        this.rate = rate;
    }


    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }


    public boolean isLogedIn() {
        return logedIn;
    }
    public void setLogedIn(boolean logedIn) {
        this.logedIn = logedIn;
    }

    public String getEditedText() {
        return editedText;
    }
    public void setEditedText(String editedText) {
        this.editedText = editedText;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
}
