package com.behrouz.web.okhttp.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created By Hapi KZM
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdLong {

    protected long id;

    protected long value;


    public IdLong() {
    }

    public IdLong(long id, long value) {
        this.id = id;
        this.value = value;
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public long getValue() {
        return value;
    }
    public void setValue(long value) {
        this.value = value;
    }
}
