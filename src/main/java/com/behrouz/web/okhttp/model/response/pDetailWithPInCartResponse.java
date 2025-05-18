package com.behrouz.web.okhttp.model.response;

import com.behrouz.web.rest.request.WebCartRedis;

import java.util.List;

public class pDetailWithPInCartResponse {
    private List<WebCartRedis> productInCart;
    private ProductDetailResponse productDetail;

    public pDetailWithPInCartResponse() {
    }

    public pDetailWithPInCartResponse(List<WebCartRedis> productInCart, ProductDetailResponse productDetail) {
        this.productInCart = productInCart;
        this.productDetail = productDetail;
    }

    public List<WebCartRedis> getProductInCart() {
        return productInCart;
    }
    public void setProductInCart(List<WebCartRedis> productInCart) {
        this.productInCart = productInCart;
    }

    public ProductDetailResponse getProductDetail() {
        return productDetail;
    }
    public void setProductDetail(ProductDetailResponse productDetail) {
        this.productDetail = productDetail;
    }
}
