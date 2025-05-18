package com.behrouz.web.data.cardItem;


import com.behrouz.web.controller.shop.CartList;


import java.util.ArrayList;
import java.util.List;


public class  CardItem {
    public static List<CartList> fill(){
        List<CartList> cardPack = new ArrayList<>();
        CartList s = new CartList("assets/img/shopItem/images.jpeg","assets/img/shopItem/images (1).jpeg","name1","friute",1111);
        cardPack.add(s);
        CartList s1 = new CartList("assets/img/shopItem/images (1).jpeg","assets/img/shopItem/images.jpeg","name2","friute2",2222);
        cardPack.add(s1);
        CartList s2 = new CartList("assets/img/shopItem/images (2).jpeg","assets/img/shopItem/images (1).jpeg","name3","friute3",3333);
        cardPack.add(s2);
        CartList s3 = new CartList("assets/img/shopItem/images.jpeg","assets/img/shopItem/images (1).jpeg","name4","friute4",4444);
        cardPack.add(s3);
        CartList s4 = new CartList("assets/img/shopItem/images (1).jpeg","assets/img/shopItem/images.jpeg","name5","friute5",5555);
        cardPack.add(s4);
        CartList s5 = new CartList("assets/img/shopItem/images (2).jpeg","assets/img/shopItem/images.jpeg","name6","friute6",6666);
        cardPack.add(s5);
        CartList s6 = new CartList("assets/img/shopItem/images.jpeg","assets/img/shopItem/images (2).jpeg","name7","friute7",7777);
        cardPack.add(s6);
        CartList s7 = new CartList("assets/img/shopItem/images (1).jpeg","assets/img/shopItem/images.jpeg","name8","friute8",8888);
        cardPack.add(s7);
        CartList s8 = new CartList("assets/img/shopItem/images (2).jpeg","assets/img/shopItem/images (1).jpeg","name9","friute9",9999);
        cardPack.add(s8);
        CartList s9 = new CartList("assets/img/shopItem/images.jpeg","assets/img/shopItem/images (2).jpeg","name10","friute21",1010);
        cardPack.add(s9);
        CartList s10 = new CartList("assets/img/shopItem/images.jpeg","assets/img/shopItem/images (2).jpeg","name10","friute21",1010);
        cardPack.add(s10);
        CartList s11 = new CartList("assets/img/shopItem/images.jpeg","assets/img/shopItem/images (2).jpeg","name10","friute21",1010);
        cardPack.add(s11);
        CartList s12 = new CartList("assets/img/shopItem/images.jpeg","assets/img/shopItem/images (2).jpeg","name10","friute21",1010);
        cardPack.add(s12);
        return cardPack;
    }
}