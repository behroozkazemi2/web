package com.behrouz.web.rest;

import com.behrouz.web.util.Thymeleaf.ThymeleafPersianDateModel;

import java.util.Date;

/**
 * Created by: HapiKZM
 **/
public class DateRestResponse implements ThymeleafPersianDateModel {

    private Date date;

    public DateRestResponse() {
    }

    public DateRestResponse(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
}
