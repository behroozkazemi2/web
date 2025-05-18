package com.behrouz.web.okhttp.model.response;

/**
 * Created By Hapi KZM
 * Company: reza
 * Package: ir.mobintabaran.xima.server.api.customer.request
 * Project Name: xima-server
 * 04 July 2020
 **/

public class PaymentAmount {

    private long amount;

    private String link;

    private int billId;


    public PaymentAmount ( long amount, String link, int billId ) {
        this.amount = amount;
        this.link = link;
        this.billId = billId;
    }

    public PaymentAmount toToman(){
        this.amount /= 10;
        return this;
    }

    public long getAmount () {
        return amount;
    }
    public void setAmount ( long amount ) {
        this.amount = amount;
    }


    public String getLink () {
        return link;
    }
    public void setLink ( String link ) {
        this.link = link;
    }


    public int getBillId () {
        return billId;
    }
    public void setBillId ( int billId ) {
        this.billId = billId;
    }


    public PaymentAmount () {
    }
}
