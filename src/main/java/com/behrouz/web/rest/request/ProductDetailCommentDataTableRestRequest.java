package com.behrouz.web.rest.request;

public class ProductDetailCommentDataTableRestRequest {
    private long currentPage;
    private long productId;

    public ProductDetailCommentDataTableRestRequest() {
    }

    public ProductDetailCommentDataTableRestRequest(long currentPage, long productId) {
        this.currentPage = currentPage;
        this.productId = productId;
    }

    public long getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    public long getProductId() {
        return productId;
    }
    public void setProductId(long productId) {
        this.productId = productId;
    }
}
