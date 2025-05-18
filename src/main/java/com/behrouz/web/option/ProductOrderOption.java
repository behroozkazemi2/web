package com.behrouz.web.option;

public enum ProductOrderOption {


    PRICE_UP(1),//گرانترین
    PRICE_DOWN (2),//ارزانترین
    VIEW (3),//پربازدیدترین
    OFF (4),//"بیشترین تخفیف"
    NEW(5),//جدیدترین
    SAIL(6),//"پرفروش ترین"
    PROMOTE(7);//" برتر"

    private final int id;

    ProductOrderOption(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }



    public static ProductOrderOption getById(int id){
        for(ProductOrderOption option : ProductOrderOption.values()){
            if(option.getId() == id){
                return option;
            }
        }

        return null;
    }
}
