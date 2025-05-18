package com.behrouz.web.controller.faq;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/faq")
public class Faq {

    @RequestMapping(value = {"/", ""})
    public String faq(Model model) {

        model.addAttribute("view", "view/faq");

        return "index-2.html";
    }

}
