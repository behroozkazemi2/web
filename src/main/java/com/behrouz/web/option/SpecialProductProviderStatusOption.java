package com.behrouz.web.option;

public enum SpecialProductProviderStatusOption {

    SUBMIT(1),
    VIEW(2),
    CONFIRM(3),
    DELETE(4);

    private final int id;

    SpecialProductProviderStatusOption(int id ) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static SpecialProductProviderStatusOption getById(int id)  {
        for(SpecialProductProviderStatusOption option : SpecialProductProviderStatusOption.values()){
            if(option.getId() == id){
                return option;
            }
        }

        return SUBMIT;
    }

}