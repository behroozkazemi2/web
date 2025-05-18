package com.behrouz.web.controller.provider;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/category")
public class ProviderController {

    @RequestMapping( value = { "/{id}" } )
    public String shop (Model model,
                        @PathVariable(name = "id") int id) {
        model.addAttribute( "pid", id );
        model.addAttribute( "view", "view/provider" );
        return "index";
    }



}
