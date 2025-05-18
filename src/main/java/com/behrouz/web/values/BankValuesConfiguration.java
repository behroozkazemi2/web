package com.behrouz.web.values;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BankValuesConfiguration {

    public static String SADAD_SADAD_PAY_URL;


    @Value("${bank.sadad.sadad-pay-url}")
    public void setSadadSadadPayUrl(String value){
        SADAD_SADAD_PAY_URL = value;
    }

}
