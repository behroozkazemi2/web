package com.behrouz.web.okhttp.model.response;

import java.util.Date;
import java.util.List;


public class PromotePromoteProductResponse {
    private String promoteName;
    private long promoteId;
    private Date startDate;
    private Date endDate;
    private List<ProductResponse> products;

    public PromotePromoteProductResponse() {
    }


    public String getPromoteName() {
        return promoteName;
    }
    public void setPromoteName(String promoteName) {
        this.promoteName = promoteName;
    }


    public long getPromoteId() {
        return promoteId;
    }
    public void setPromoteId(long promoteId) {
        this.promoteId = promoteId;
    }


    public List<ProductResponse> getProducts() {
        return products;
    }
    public void setProducts(List<ProductResponse> products) {
        this.products = products;
    }


    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }


    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}
