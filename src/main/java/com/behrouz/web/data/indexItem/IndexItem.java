package com.behrouz.web.data.indexItem;


import com.behrouz.web.controller.indexes.index.BestProduct;
import com.behrouz.web.controller.indexes.index.BestSeller;
import com.behrouz.web.controller.indexes.index.SpecialList;

import java.util.ArrayList;
import java.util.List;

public class IndexItem {

    public static List<SpecialList> special() {
        List<SpecialList> SpecialPack = new ArrayList<>();
        SpecialList s = new SpecialList("/assets/img/shopItem/images.jpeg","/assets/img/shopItem/images (2).jpeg", "ghy", "friute", 7000);
        SpecialPack.add(s);
        SpecialList s1 = new SpecialList("/assets/img/shopItem/images (1).jpeg","/assets/img/shopItem/images (2).jpeg", "htj", "friute2", 5000);
        SpecialPack.add(s1);
        SpecialList s2 = new SpecialList("/assets/img/shopItem/images (2).jpeg","/assets/img/shopItem/images.jpeg", "tfe", "friute3", 3990);
        SpecialPack.add(s2);
        SpecialList s3 = new SpecialList("/assets/img/shopItem/images.jpeg","/assets/img/shopItem/images (1).jpeg", "dfe", "friute4", 2000);
        SpecialPack.add(s3);
        SpecialList s4 = new SpecialList("/assets/img/shopItem/images (1).jpeg","/assets/img/shopItem/images (2).jpeg", "wed", "friute5", 1000);
        SpecialPack.add(s4);
        SpecialList s5 = new SpecialList("/assets/img/shopItem/images (2).jpeg","/assets/img/shopItem/images.jpeg", "scd", "friute6", 3000);
        SpecialPack.add(s5);
        SpecialList s6 = new SpecialList("/assets/img/shopItem/images.jpeg","/assets/img/shopItem/images (1).jpeg", "dfh", "friute7", 5570);
        return SpecialPack;
    }

    public static List<BestSeller> seller() {
        List<BestSeller> bestSellerPack = new ArrayList<>();
        BestSeller s = new BestSeller("/assets/img/brand/brand1.jpg", "bgh", 3);
        bestSellerPack.add(s);
        BestSeller s1 = new BestSeller("/assets/img/brand/brand2.jpg", "vvcc", 5);
        bestSellerPack.add(s1);
        BestSeller s2 = new BestSeller("/assets/img/brand/brand3.jpg", "cvc", 2);
        bestSellerPack.add(s2);
        BestSeller s3 = new BestSeller("/assets/img/brand/brand4.jpg", "vcv", 2);
        bestSellerPack.add(s3);
        return bestSellerPack;
    }

    public static List<BestProduct> top() {
        List<BestProduct> bestProductPack = new ArrayList<>();
        BestProduct s = new BestProduct("/assets/img/shopItem/images.jpeg","/assets/img/shopItem/images (1).jpeg", "qwe", "friute", 2234);
        bestProductPack.add(s);
        BestProduct s1 = new BestProduct("/assets/img/shopItem/images (1).jpeg","/assets/img/shopItem/images (2).jpeg", "scx", "friute2", 5500);
        bestProductPack.add(s1);
        BestProduct s2 = new BestProduct("/assets/img/shopItem/images (2).jpeg","/assets/img/shopItem/images.jpeg", "zas", "friute3", 8800);
        bestProductPack.add(s2);
        BestProduct s3 = new BestProduct("/assets/img/shopItem/images.jpeg","/assets/img/shopItem/images (1).jpeg", "gvb", "friute4", 9900);
        bestProductPack.add(s3);
        BestProduct s4 = new BestProduct("/assets/img/shopItem/images (1).jpeg","/assets/img/shopItem/images (2).jpeg", "htf", "friute5", 0000);
        bestProductPack.add(s4);
        BestProduct s5 = new BestProduct("/assets/img/shopItem/images (2).jpeg","/assets/img/shopItem/images (1).jpeg", "jkj", "friute6", 5300);
        bestProductPack.add(s5);
        return bestProductPack;
    }
}
