package com.behrouz.web.controller.shop;

public class CartList {


    private String imgAdress;
    private String imgAdress1;
    private String name;
    private String category;
    private double price;

    public CartList() {
    }

    public CartList(String imgAdress,String imgAdress1, String name, String category, double price) {
        this.imgAdress = imgAdress;
        this.imgAdress1 = imgAdress1;
        this.name = name;
        this.category = category;
        this.price = price;
    }


    public String getImgAdress() {
        return imgAdress;
    }
    public void setImgAdress(String imgAdress) {
        this.imgAdress = imgAdress;
    }


    public String getImgAdress1() {
        return imgAdress1;
    }
    public void setImgAdress1(String imgAdress1) {
        this.imgAdress1 = imgAdress1;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }


    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
