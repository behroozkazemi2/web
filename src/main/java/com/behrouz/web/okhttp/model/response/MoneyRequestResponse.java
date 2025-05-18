package com.behrouz.web.okhttp.model.response;

/**
 * Created By Hapi KZM
 * Package ir.mobintabaran.xima.webapplication.okhttp.model.response
 * Project xima-webapplication
 * 23 February 2019 5:41 PM
 **/
public class MoneyRequestResponse {

    private long amount;
    private long amountRial;


    public MoneyRequestResponse() {
    }

    public MoneyRequestResponse(long amount) {
        this.amount = amount;
    }

    public long getAmount() {
        return amount;
    }
    public void setAmount(long amount) {
        this.amount = amount;
    }
    public void setAmountRial(long amountRial) {
        this.amountRial = amountRial;
    }
    public long getAmountRial(){
        return amount*10;
    }

    public MoneyRequestResponse toToman () {
        this.amount /= 10;
        return this;
    }
}
