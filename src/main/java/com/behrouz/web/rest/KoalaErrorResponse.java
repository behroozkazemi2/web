package com.behrouz.web.rest;

/**
 * Created By Hapi KZM
 * Package ir.mobintabaran.xima.webapplication.rest
 * Project xima-webapplication
 * 05 February 2019 10:06 AM
 **/
public class KoalaErrorResponse {

    private String field;

    private String message;

    public KoalaErrorResponse(String field, String message) {
        this.field = field;
        this.message = message;
    }


    public KoalaErrorResponse() {
    }

    public String getField() {
        return field;
    }
    public void setField(String field) {
        this.field = field;
    }



    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
