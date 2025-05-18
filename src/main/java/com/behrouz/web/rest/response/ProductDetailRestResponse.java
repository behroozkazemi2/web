package com.behrouz.web.rest.response;


import com.behrouz.web.okhttp.model.IdName;

import java.util.List;

public class ProductDetailRestResponse {

    private ProductCardRstResponse product;
    private String description;
    private boolean state;
    private long userDescription;
    private long userProductCount;
    private List<IdName> tags;
    private List<Long> gallery;

    public ProductDetailRestResponse() {
    }

    public ProductDetailRestResponse(ProductCardRstResponse product, String description, boolean state, long userDescription, long userProductCount, List<IdName> tags, List<Long> gallery) {
        this.product = product;
        this.description = description;
        this.state = state;
        this.userDescription = userDescription;
        this.userProductCount = userProductCount;
        this.tags = tags;
        this.gallery = gallery;
    }

    public ProductCardRstResponse getProduct() {
        return product;
    }
    public void setProduct(ProductCardRstResponse product) {
        this.product = product;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isState() {
        return state;
    }
    public void setState(boolean state) {
        this.state = state;
    }

    public long getUserDescription() {
        return userDescription;
    }
    public void setUserDescription(long userDescription) {
        this.userDescription = userDescription;
    }

    public long getUserProductCount() {
        return userProductCount;
    }
    public void setUserProductCount(long userProductCount) {
        this.userProductCount = userProductCount;
    }

    public List<IdName> getTags() {
        return tags;
    }
    public void setTags(List<IdName> tags) {
        this.tags = tags;
    }

    public List<Long> getGallery() {
        return gallery;
    }
    public void setGallery(List<Long> gallery) {
        this.gallery = gallery;
    }
}
