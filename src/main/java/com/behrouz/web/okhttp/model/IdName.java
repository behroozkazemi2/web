package com.behrouz.web.okhttp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created By Hapi KZM
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdName {

    protected int id;

    protected String name;


    public IdName() {
    }

    public IdName(int id, String name) {
        this.id = id;
        this.name = name;
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
}
