package com.behrouz.web.okhttp.model.request;

/**
 * Created By Hapi KZM
 * Package ir.mobintabaran.xima.server.rest.constant
 * Project Koala Server
 * 09 September 2018 15:55
 **/
public class IdRequest {

    private long id;

    public IdRequest() {
    }

    public IdRequest(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

}
