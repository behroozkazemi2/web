package com.behrouz.web.okhttp.model.request;


import java.util.List;

/**
 * Created By Hapi KZM
 * Package ir.longabaran.xima.server.api.customer.request
 * Project server
 * 30 September 2018 10:43
 **/
public class ProductProviderCategoryRequest extends ListRequest {

    private List<Long> productIds;

    private List<Long> productCategoryId;

    private long providerId; // for next step, when clicked on some provider

    private String search;

    private List<Long> tag;

    private List<Long> brands;

    private long orderId;

    private float upPrice;

    private float downPrice;

    private long region;

    private long addressId;

    private boolean existence;


    public ProductProviderCategoryRequest( List<Long> productCategoryId, long providerId, String search, int page, int length) {
        this.productCategoryId = productCategoryId;
        this.providerId = providerId;
        this.search = search;
        this.page = page;
        this.length = length;
    }
    public ProductProviderCategoryRequest( String o, int page, int length) {
        this.page = page;
        this.length = length;

    }
    public ProductProviderCategoryRequest( String o, int page, int length,long region) {
        this.page = page;
        this.length = length;
        this.region = region;

    }

    public ProductProviderCategoryRequest(List<Long> productCategoryId, long providerId, Object o, int page, int length) {
//        this.providerCategoryId = productCategoryId;
        this.productCategoryId = productCategoryId;
        this.providerId = providerId;
//        this.search = search;
        this.page = page;
        this.length = length;
    }


    public ProductProviderCategoryRequest() {
    }

    public ProductProviderCategoryRequest( List<Long> productCategoryId, long providerId, String search, List<Long> tag, int orderId, float upPrice, float downPrice) {
        this.productCategoryId = productCategoryId;
        this.providerId = providerId;
        this.search = search;
        this.tag = tag;
        this.orderId = orderId;
        this.upPrice = upPrice;
        this.downPrice = downPrice;
    }

    public ProductProviderCategoryRequest( List<Long> productCategoryId, long providerId, String search, List<Long> tag, int orderId, float upPrice, float downPrice,long region, long addressId) {
        this.productCategoryId = productCategoryId;
        this.providerId = providerId;
        this.search = search;
        this.tag = tag;
        this.orderId = orderId;
        this.upPrice = upPrice;
        this.downPrice = downPrice;
        this.region=region;
        this.addressId=addressId;
    }
    public ProductProviderCategoryRequest( List<Long> productCategoryId, long providerId, String search, List<Long> tag, int orderId, float upPrice, float downPrice,long region, List<Long> brands , boolean existence, long addressId) {
        this.productCategoryId = productCategoryId;
        this.providerId = providerId;
        this.search = search;
        this.tag = tag;
        this.orderId = orderId;
        this.upPrice = upPrice;
        this.downPrice = downPrice;
        this.region = region;
        this.brands = brands;
        this.existence = existence;
        this.addressId = addressId;
    }

    public ProductProviderCategoryRequest(List<Long> pId, int page, int length, long region) {
        this.productCategoryId = productCategoryId;

        this.page = page;
        this.length = length;
        this.region=region;

    }


    public List<Long> getProductIds() {
        return productIds;
    }
    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }
    public List<Long> getProductCategoryId() {
        return productCategoryId;
    }
    public void setProductCategoryId( List<Long> productCategoryId ) {
        this.productCategoryId = productCategoryId;
    }


    public long getProviderId() {
        return providerId;
    }
    public void setProviderId( long providerId ) {
        this.providerId = providerId;
    }


    public String getSearch () {
        return search;
    }
    public void setSearch ( String search ) {
        this.search = search;
    }

    public List<Long> getTag() {
        return tag;
    }
    public void setTag(List<Long> tag) {
        this.tag = tag;
    }


    public long getOrderId() {
        return orderId;
    }
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }


    public float getUpPrice() {
        return upPrice;
    }
    public void setUpPrice(float upPrice) {
        this.upPrice = upPrice;
    }


    public float getDownPrice() {
        return downPrice;
    }
    public void setDownPrice(float downPrice) {
        this.downPrice = downPrice;
    }


    public long getRegion() {
        return region;
    }
    public void setRegion(long region) {
        this.region = region;
    }

    public List<Long> getBrands() {
        return brands;
    }
    public void setBrands(List<Long> brands) {
        this.brands = brands;
    }

    public boolean isExistence() {
        return existence;
    }
    public void setExistence(boolean existence) {
        this.existence = existence;
    }

    public long getAddressId() {
        return addressId;
    }
    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }
}
