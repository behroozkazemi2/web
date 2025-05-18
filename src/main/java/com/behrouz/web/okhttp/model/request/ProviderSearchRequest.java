package com.behrouz.web.okhttp.model.request;

/**
 * Created By Hapi KZM
 * Package ir.mobintabaran.xima.webapplication.okhttp.model.request
 * Project xima-webapplication
 * 21 January 2019 3:55 PM
 **/
public class ProviderSearchRequest extends ListRequest {

    private String search;


    public ProviderSearchRequest() {
    }
    public ProviderSearchRequest(int page, int length) {
        super(page, length);
    }

    public ProviderSearchRequest(int page, int length, String search) {
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
