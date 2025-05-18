package com.behrouz.web.rest;


public class IdNameBit {

    private long id;
    private String name;
    private boolean bit;


    public IdNameBit() {
    }

    public IdNameBit(long id, String name, boolean bit) {
        this.id = id;
        this.name = name;
        this.bit = bit;
    }





    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBit() {
        return bit;
    }

    public void setBit(boolean bit) {
        this.bit = bit;
    }
}
