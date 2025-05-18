package com.behrouz.web.data.providerItem;

import com.behrouz.web.controller.provider.ProviderList;

import java.util.ArrayList;
import java.util.List;

public class ProviderItem {
    public static List<ProviderList> fill() {
        List<ProviderList> cardPack = new ArrayList<>();
        ProviderList s = new ProviderList("/assets/img/logo/logo.png", "ahmad", 10);
        cardPack.add(s);
        ProviderList s1 = new ProviderList("/assets/img/logo/logo.png", "hassan", 7);
        cardPack.add(s1);
        ProviderList s2 = new ProviderList("/assets/img/logo/logo.png", "hossein", 10);
        cardPack.add(s2);
        ProviderList s3 = new ProviderList("/assets/img/logo/logo.png", "Hapi", 8);
        cardPack.add(s3);
        ProviderList s4 = new ProviderList("/assets/img/logo/logo.png", "Hapi", 9);
        cardPack.add(s4);
        ProviderList s5 = new ProviderList("/assets/img/logo/logo.png", "matin", 6);
        cardPack.add(s5);
        ProviderList s6 = new ProviderList("/assets/img/logo/logo.png", "reza", 8);
        cardPack.add(s6);
        ProviderList s7 = new ProviderList("/assets/img/logo/logo.png", "ali", 1);
        cardPack.add(s7);
        ProviderList s8 = new ProviderList("/assets/img/logo/logo.png", "qoli", 5);
        cardPack.add(s8);
        ProviderList s9 = new ProviderList("/assets/img/logo/logo.png", "naqi", 2);
        cardPack.add(s9);
        return cardPack;
    }
}
