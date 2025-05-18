package com.behrouz.web.rest.request;

import java.util.List;

public class CardTableFiltersRestRequest {

    private List<Long> brands;
    private double minPrice;
    private double maxPrice;
    private boolean availableProduct;
    private long currentPage;
    private long orderShow;

    public CardTableFiltersRestRequest(List<Long> brands, double minPrice, double maxPrice, boolean availabeProduct , long currentPage, long orderShow) {
        this.brands = brands;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.availableProduct = availabeProduct;
        this.currentPage = currentPage;
        this.orderShow = orderShow;

    }

    public CardTableFiltersRestRequest() {

    }



    public List<Long> getBrands() {
        return brands;
    }
    public void setBrands(List<Long> brands) {
        this.brands = brands;
    }


    public double getMinPrice() {
        return minPrice;
    }
    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }


    public double getMaxPrice() {
        return maxPrice;
    }
    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }


    public boolean isAvailableProduct() {
        return availableProduct;
    }
    public void setAvailableProduct(boolean availableProduct) {
        this.availableProduct = availableProduct;
    }


    public long getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }


    public long getOrderShow() {
        return orderShow;
    }
    public void setOrderShow(long orderShow) {
        this.orderShow = orderShow;
    }
}
