package com.behrouz.web.rest.response;

public class InCartProductCardRstResponse {

    private long  productId;
    private long imgId;
    private String title;
    private long delPrice;
    private long price;
    private long discountPercent;
    private long count;


    public InCartProductCardRstResponse() {
    }

    public InCartProductCardRstResponse(long productId, long imgId, String title, long delPrice, long price, long discountPercent, long count) {
        this.productId = productId;
        this.imgId = imgId;
        this.title = title;
        this.delPrice = delPrice;
        this.price = price;
        this.discountPercent = discountPercent;
        this.count = count;
    }

    public long getProductId() {
        return productId;
    }
    public void setProductId(long productId) {
        this.productId = productId;
    }


    public long getImgId() {
        return imgId;
    }
    public void setImgId(long imgId) {
        this.imgId = imgId;
    }


    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }


    public long getDelPrice() {
        return delPrice;
    }
    public void setDelPrice(long delPrice) {
        this.delPrice = delPrice;
    }


    public long getPrice() {
        return price;
    }
    public void setPrice(long price) {
        this.price = price;
    }


    public long getDiscountPercent() {
        return discountPercent;
    }
    public void setDiscountPercent(long discountPercent) {
        this.discountPercent = discountPercent;
    }

    public long getCount() {
        return count;
    }
    public void setCount(long count) {
        this.count = count;
    }
}
