package com.behrouz.web.okhttp.model.response;

public class ProductDetailResponse extends ProductResponse {


    protected String userDescription;

    protected long inCartCount;



    public ProductDetailResponse() {
    }

    public ProductDetailResponse(int productProviderId) {
        this.id = productProviderId;
    }


    public String getUserDescription() {
        return userDescription;
    }
    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }




    public long getInCartCount() {
        return inCartCount;
    }
    public void setInCartCount(long inCartCount) {
        this.inCartCount = inCartCount;
    }

}