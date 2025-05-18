
package com.behrouz.web.controller.pay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.behrouz.web.component.WebCartComponent;
import com.behrouz.web.configuration.CookieInterceptor;

@Controller
@RequestMapping(value = {"/pay"})
class PayController {

    // eezafe kardam

    @Autowired
    private WebCartComponent webCartComponent;


    @RequestMapping(value = "/error")
    public String unsuccessful(Model model) {

        model.addAttribute("error", false);

        model.addAttribute("view", "view/pay/errorPay.html");
        return "index.html";
    }


    @RequestMapping(value = "/success/{code}/{billId}")
    public String successful(Model model,
                             @PathVariable (name = "code") String code,
                             @PathVariable (name = "billId") long billId
                             ) {

        model.addAttribute("error", true);
        model.addAttribute("code", code);
        model.addAttribute("billId", billId);

        model.addAttribute("view", "view/othersPage/payment-back.html");
        return "index.html";
    }


    @RequestMapping(value = "/result/{successful}/{token}/{billId}")
    public String successful(Model model,
                             @PathVariable(name = "successful") boolean successful,
                             @PathVariable(name = "token") String token,
                             @PathVariable(name = "billId") int billId,
                             @CookieValue(name = CookieInterceptor.KEY_COOKIE) String cookie) {

        if (successful) {
            model.addAttribute("billId", billId);
            if (billId != 0) {
                checkBankCallBank(token, billId, cookie);
                return "redirect:/user";
            }
            return "redirect:/user/information?id="+billId;

        }
        model.addAttribute("success", successful);
        model.addAttribute("view", "view/pay/result.html");
        return "index.html";
    }


    private void checkBankCallBank(
            String token,
            int billId,
            String cookie) {
        webCartComponent.clearCart(cookie);


        // TODO: 7/15/20  
    }


}