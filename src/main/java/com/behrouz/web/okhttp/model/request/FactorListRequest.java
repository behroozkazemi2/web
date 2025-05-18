package com.behrouz.web.okhttp.model.request;

/**
 * Created By Hapi KZM
 * Package ir.mobintabaran.xima.webapplication.okhttp.model.request
 * Project xima-webapplication
 * 21 January 2019 3:52 PM
 **/
public class FactorListRequest {

    private long factorType;
    private int page;
    private int length;

    public FactorListRequest() {
    }

    public FactorListRequest( int page, int length, long factorType) {
        this.factorType = factorType;
        this.page = page;
        this.length = length;
    }


    public long getFactorType() {
        return factorType;
    }
    public void setFactorType(long factorType) {
        this.factorType = factorType;
    }


    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }


    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
}
