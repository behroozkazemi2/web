package com.behrouz.web.controller.indexes.index;

public class BestSeller {

    private String logo;
    private String name;
    private int score;

    public BestSeller() {
    }

    public BestSeller(String logo, String name, int score) {
        this.logo = logo;
        this.name = name;
        this.score = score;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


}
