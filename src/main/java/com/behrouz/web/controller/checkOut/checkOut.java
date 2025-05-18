package com.behrouz.web.controller.checkOut;

import com.behrouz.web.okhttp.OkHttpHelper;
import com.behrouz.web.okhttp.api.ApiResponseBody;
import com.behrouz.web.okhttp.model.response.MoneyRequestResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/checkOut")
public class checkOut {
    @RequestMapping(value = {"/", ""})
    public String CheckOut(
            Model model) {

        ApiResponseBody<MoneyRequestResponse> userBalance = OkHttpHelper.getUserBalance();
        if (userBalance.successful()) {
            model.addAttribute("walletCharge", userBalance.getData().getAmount());
        } else{
            model.addAttribute("walletCharge", 0);
        }
        model.addAttribute("view", "view/checkOut/checkout.html");
        return "index.html";
    }

}
