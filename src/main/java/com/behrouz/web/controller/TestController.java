package com.behrouz.web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping(value = {"/", ""})
    public String card(Model model) {
        model.addAttribute("view", "view/cartPage/threed.html");
        return "index.html";
    }

}
