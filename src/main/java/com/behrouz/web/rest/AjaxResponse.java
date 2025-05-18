package com.behrouz.web.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created By Hapi KZM
 * Package ir.mobintabaran.mehan.server.rest.constant
 * Project MehanServer
 * 16 July 2018 11:06
 **/
public class AjaxResponse {

    private boolean result;
    private String payload;


    public AjaxResponse() {
    }

    public AjaxResponse(boolean result, String payload) {
        this.result = result;
        this.payload = payload;
    }

    public AjaxResponse(boolean result, Object payload) {
        this.result = result;
        try {
            this.payload = new ObjectMapper().writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

}
