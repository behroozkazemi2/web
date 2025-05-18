package com.behrouz.web.controller.provider;

public class ProviderList {
    private String logo;
    private  String name;
    private  int score;

    public ProviderList() {
    }

    public ProviderList(String logo, String name, int score) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
