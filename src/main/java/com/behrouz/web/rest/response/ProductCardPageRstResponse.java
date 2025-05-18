package com.behrouz.web.rest.response;


import com.behrouz.web.okhttp.model.IdName;

import java.util.List;

public class ProductCardPageRstResponse {

    private List<ProductCardRstResponse> product;
    private  double minPrice;
    private  double maxPrice;
    private  List<IdName> brands;
    private  long  total;

    public ProductCardPageRstResponse() {
    }

    public ProductCardPageRstResponse(List<ProductCardRstResponse> product, double minPrice, double maxPrice, List<IdName> brands , long total) {
        this.product = product;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.brands = brands;
        this.total = total;
    }




    public List<ProductCardRstResponse> getProduct() {
        return product;
    }
    public void setProduct(List<ProductCardRstResponse> product) {
        this.product = product;
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


    public long getTotal() {
        return total;
    }
    public void setTotal(long total) {
        this.total = total;
    }

    public List<IdName> getBrands() {
        return brands;
    }
    public void setBrands(List<IdName> brands) {
        this.brands = brands;
    }
}

