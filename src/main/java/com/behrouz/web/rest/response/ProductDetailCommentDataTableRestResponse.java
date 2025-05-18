package com.behrouz.web.rest.response;

import com.behrouz.web.okhttp.model.IdName;

import java.util.Date;

public class ProductDetailCommentDataTableRestResponse {
    private IdName userName;
    private Date insertDate;
    private long rate;
    private String  rateS;
    private String  description;


    public ProductDetailCommentDataTableRestResponse() {
    }

    public ProductDetailCommentDataTableRestResponse(IdName userName, Date insertDate, long rate, String rateS, String description) {
        this.userName = userName;
        this.insertDate = insertDate;
        this.rate = rate;
        this.rateS = rateS;
        this.description = description;
    }


    public IdName getUserName() {
        return userName;
    }
    public void setUserName(IdName userName) {
        this.userName = userName;
    }


    public Date getInsertDate() {
        return insertDate;
    }
    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }


    public long getRate() {
        return rate;
    }
    public void setRate(long rate) {
        this.rate = rate;
    }


    public String getRateS() {
        return rateS;
    }
    public void setRateS(String rateS) {
        this.rateS = rateS;
    }


    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
