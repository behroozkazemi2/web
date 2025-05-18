package com.behrouz.web.rest.response;

import com.behrouz.web.okhttp.model.IdName;

import java.util.List;

public class ProductDetailCommentDetailRestResponse {

    private double totalRate;
    private String rateString;
    private double totalRatePercent;
    private List<IdName> rate;

    public ProductDetailCommentDetailRestResponse() {
    }

    public ProductDetailCommentDetailRestResponse(String rateString,double totalRate, double totalRatePercent, List<IdName> rate) {
        this.rateString = rateString;
        this.totalRate = totalRate;
        this.totalRatePercent = totalRatePercent;
        this.rate = rate;
    }

    public String getRateString() {
        return rateString;
    }
    public void setRateString(String rateString) {
        this.rateString = rateString;
    }

    public double getTotalRate() {
        return totalRate;
    }
    public void setTotalRate(double totalRate) {
        this.totalRate = totalRate;
    }


    public double getTotalRatePercent() {
        return totalRatePercent;
    }
    public void setTotalRatePercent(double totalRatePercent) {
        this.totalRatePercent = totalRatePercent;
    }


    public List<IdName> getRate() {
        return rate;
    }
    public void setRate(List<IdName> rate) {
        this.rate = rate;
    }
}
