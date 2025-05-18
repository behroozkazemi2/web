package com.behrouz.web.controller.pay;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/pay"})
public class Pay {
    @RequestMapping(value = {"/", ""})
    public String pay (Model model) {
        model.addAttribute("succes",true);
        model.addAttribute("view","view/pay.html");
        return "index.html";
    }

}
