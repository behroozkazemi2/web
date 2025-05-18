package com.behrouz.web.controller.services;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/services")
public class Services {
    @RequestMapping(value = {"/",""})
    public String services(){
        return "index-2.html";
    }
}
