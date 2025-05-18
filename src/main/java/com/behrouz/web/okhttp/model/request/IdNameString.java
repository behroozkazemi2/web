package com.behrouz.web.okhttp.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created By Hapi KZM
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdNameString {

    protected int id;

    protected String name;
    protected String value;


    public IdNameString() {
    }

    public IdNameString(int id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
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


    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}
