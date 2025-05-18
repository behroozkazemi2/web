package com.behrouz.web.okhttp.model.response;


import java.io.Serializable;
import java.util.List;

/**
 * Created By Hapi KZM
 * Package ir.mobintabaran.xima.server.api.customer.response
 * Project server
 * 30 September 2018 13:21
 **/
public class AllCategoriesResponse implements Serializable {

    private RequestDetailResponse current;
    private RequestDetailResponse parent;

    private List< AllCategoriesResponse > children;

    public AllCategoriesResponse() {
    }

    public AllCategoriesResponse(RequestDetailResponse current, RequestDetailResponse parent, List<AllCategoriesResponse> children) {
        this.current = current;
        this.parent = parent;
        this.children = children;
    }

    public RequestDetailResponse getParent() {
        return parent;
    }
    public void setParent( RequestDetailResponse parent ) {
        this.parent = parent;
    }


    public RequestDetailResponse getCurrent() {
        return current;
    }
    public void setCurrent(RequestDetailResponse current) {
        this.current = current;
    }


    public List<AllCategoriesResponse> getChildren() {
        return children;
    }
    public void setChildren(List<AllCategoriesResponse> children) {
        this.children = children;
    }
}
