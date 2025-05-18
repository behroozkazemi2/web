package com.behrouz.web.okhttp.model.request;

/**
 * Created By Hapi KZM
 * Company: reza
 * Package: ir.mobintabaran.xima.server.api.customer.request
 * Project Name: xima-server
 * 13 December 2018
 **/
public class AddressRequestResponse {

    private long id;
    private String address;
    private String title;
    private String city;
    private String province;
    private String postalCode;

    private double lat; // Y
    private double lng; // X
    private long order; // olaviat address


    public AddressRequestResponse() {
    }

    public AddressRequestResponse(String address, double lat, double lng) {
        this.address = address;
        this.lat = lat;
        this.lng = lng;
    }

    public AddressRequestResponse(long id, String address, double lat, double lng, String title, String province, String city, String postalCode ) {
        this.id = id;
        this.province = province;
        this.city = city;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
        this.title = title;
        this.postalCode = postalCode;
    }


    public long getId () {
        return id;
    }
    public void setId ( long id ) {
        this.id = id;
    }


    public String getAddress () {
        return address;
    }
    public void setAddress ( String address ) {
        this.address = address;
    }


    public double getLat () {
        return lat;
    }
    public void setLat ( double lat ) {
        this.lat = lat;
    }


    public double getLng () {
        return lng;
    }
    public void setLng ( double lng ) {
        this.lng = lng;
    }


    public long getOrder () {
        return order;
    }
    public void setOrder ( long order ) {
        this.order = order;
    }


    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
