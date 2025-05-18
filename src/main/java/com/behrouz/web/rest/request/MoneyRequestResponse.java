package com.behrouz.web.rest.request;

/**
 * Created By Hapi KZM
 * Company: reza
 * Package: ir.mobintabaran.xima.server.api.customer.request
 * Project Name: xima-server
 * 06 December 2018
 **/
public class MoneyRequestResponse {

    private long amount; // rial


    public MoneyRequestResponse(long amount) {
        this.amount = amount;
    }

    public MoneyRequestResponse() {
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public MoneyRequestResponse toToman () {
        this.amount /= 10;
        return this;
    }
}
