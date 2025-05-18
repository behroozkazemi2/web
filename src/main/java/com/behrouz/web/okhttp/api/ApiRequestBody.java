package com.behrouz.web.okhttp.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.behrouz.web.okhttp.base.KoalaEndPoint;

import java.io.IOException;


/**
 * Created By Hapi KZM
 */

public class ApiRequestBody<T> {

    private String token;

    private String action;

    private int version;

    private T params;


    public ApiRequestBody(String token){
        this.token = token;
        this.version = 1;
    }


    public ApiRequestBody(String token, KoalaEndPoint endPoint){
        this(token,endPoint , null);
    }


    public ApiRequestBody(String token, KoalaEndPoint endPoint, T params) {
        this.token = token;
        this.action = endPoint.getApiAction();
        this.version = 1;
        this.params = params;
    }

    public ApiRequestBody(String token, T params) {
        this.token = token;
        this.version = 1;
        this.params = params;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }



    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }


    public int getVersion() {
        return version;
    }
    public void setVersion(int version) {
        this.version = version;
    }


    public T getParams() {
        return params;
    }
    public void setParams(T params) {
        this.params = params;
    }
    public <T> T getParams(Class<T> request) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(mapper.writeValueAsString(params), request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
