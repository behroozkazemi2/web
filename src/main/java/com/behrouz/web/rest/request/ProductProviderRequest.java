package com.behrouz.web.rest.request;

import com.behrouz.web.okhttp.model.request.ProductImageRequest;

import java.util.List;

public class ProductProviderRequest {
    private int providerId;
    private List<ProductImageRequest> images;
    private String fullDescription;

    public ProductProviderRequest() {
    }

    public ProductProviderRequest(int providerId, List<ProductImageRequest> images, String fullDescription) {
        this.providerId = providerId;
        this.images = images;
        this.fullDescription = fullDescription;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public List<ProductImageRequest> getImages() {
        return images;
    }

    public void setImages(List<ProductImageRequest> images) {
        this.images = images;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }
}
