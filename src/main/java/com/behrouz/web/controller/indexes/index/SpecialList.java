package com.behrouz.web.controller.indexes.index;

public class SpecialList {

    private String imgAdress;
    private String secImgAdress;
    private String name;
    private String category;
    private double price;

    public SpecialList() {
    }

    public SpecialList(String imgAdress, String secImgAdress, String name, String category, double price) {
        this.imgAdress = imgAdress;
        this.secImgAdress = secImgAdress;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getSecImgAdress() {
        return secImgAdress;
    }

    public void setSecImgAdress(String secImgAdress) {
        this.secImgAdress = secImgAdress;
    }

    public String getImgAdress() {
        return imgAdress;
    }
    public void setImgAdress(String imgAdress) {
        this.imgAdress = imgAdress;
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

