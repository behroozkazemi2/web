package com.behrouz.web.bank;


import com.behrouz.web.values.BankValuesConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/payment/melli")
public class BankMelliController {


    @RequestMapping("/{token}")
    public String pay(Model model, @PathVariable("token") String token){

        System.out.println("received token: " + token);
        model.addAttribute("url", BankValuesConfiguration.SADAD_SADAD_PAY_URL);
        model.addAttribute("token", token);

        return "bank/payment";

    }


}
