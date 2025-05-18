package com.behrouz.web.okhttp.model.request;

/**
 * Created By Hapi KZM
 * Package ir.mobintabaran.xima.webapplication.okhttp.model.request
 * Project xima-webapplication
 * 23 January 2019 10:24 AM
 **/
public class ProductSearchRequest extends ListRequest {

    private String search;

    public ProductSearchRequest() {

    }
    public ProductSearchRequest(String search) {
        this.search = search;
    }
    public ProductSearchRequest(int page, int length, String search) {
        super(page, length);
        this.search = search;
    }

    public String getSearch() {
        return search;
    }
    public void setSearch(String search) {
        this.search = search;
    }

}
