package com.behrouz.web.okhttp.model.response;

import java.util.Date;

/**
 * Created By Hapi KZM
 * Company: reza
 * Package: ir.mobintabaran.xima.server.api.customer.response
 * Project Name: xima-server
 * 20 January 2019
 **/
public class OffCodeResponse {

    private Date insertDate;
    private Date expireDate;
    private String stInsertDate;
    private String stExpireDate;
    private String offCode;
    private int maxAllowedUsage;
    private int remainUsage;
    private String explain;
    private float offPercent;
    private boolean expired;

    public OffCodeResponse() {
    }

    public OffCodeResponse(Date insertDate, Date expireDate, String stInsertDate, String stExpireDate, String offCode, int maxAllowedUsage, int remainUsage, String explain, float offPercent, boolean expired) {
        this.insertDate = insertDate;
        this.expireDate = expireDate;
        this.stInsertDate = stInsertDate;
        this.stExpireDate = stExpireDate;
        this.offCode = offCode;
        this.maxAllowedUsage = maxAllowedUsage;
        this.remainUsage = remainUsage;
        this.explain = explain;
        this.offPercent = offPercent;
        this.expired = expired;
    }

    public OffCodeResponse(Date insertDate, Date expireDate, String offCode, int maxAllowedUsage, int remainUsage, String explain, float offPercent, boolean expired) {
        this.insertDate = insertDate;
        this.expireDate = expireDate;
        this.offCode = offCode;
        this.maxAllowedUsage = maxAllowedUsage;
        this.remainUsage = remainUsage;
        this.explain = explain;
        this.offPercent = offPercent;
        this.expired = expired;
    }

    public Date getInsertDate () {
        return insertDate;
    }
    public void setInsertDate ( Date insertDate ) {
        this.insertDate = insertDate;
    }


    public Date getExpireDate () {
        return expireDate;
    }
    public void setExpireDate ( Date expireDate ) {
        this.expireDate = expireDate;
    }


    public String getOffCode () {
        return offCode;
    }
    public void setOffCode ( String offCode ) {
        this.offCode = offCode;
    }


    public int getMaxAllowedUsage () {
        return maxAllowedUsage;
    }
    public void setMaxAllowedUsage ( int maxAllowedUsage ) {
        this.maxAllowedUsage = maxAllowedUsage;
    }


    public int getRemainUsage () {
        return remainUsage;
    }
    public void setRemainUsage ( int remainUsage ) {
        this.remainUsage = remainUsage;
    }


    public String getExplain () {
        return explain;
    }
    public void setExplain ( String explain ) {
        this.explain = explain;
    }


    public float getOffPercent () {
        return offPercent;
    }
    public void setOffPercent ( float offPercent ) {
        this.offPercent = offPercent;
    }


    public boolean isExpired() {
        return expired;
    }
    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public String getStInsertDate() {
        return stInsertDate;
    }
    public void setStInsertDate(String stInsertDate) {
        this.stInsertDate = stInsertDate;
    }

    public String getStExpireDate() {
        return stExpireDate;
    }
    public void setStExpireDate(String stExpireDate) {
        this.stExpireDate = stExpireDate;
    }
}
