package com.behrouz.web.option;



public enum PaymentMethodOption {

    INTERNETI(1, "اینترنتی"),
    QYER_NAQDI(2, "غیر نقدی");


    private final int id;
    private final String  name;

    PaymentMethodOption(int id, String  name ) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public static PaymentMethodOption getById(int id) throws Exception {
        for(PaymentMethodOption option : PaymentMethodOption.values()){
            if(option.getId() == id){
                return option;
            }
        }

        throw new Exception( "وضعیت نا معتبر." );
    }

}
