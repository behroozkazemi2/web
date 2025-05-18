package com.behrouz.web.controller.homePage;

import com.behrouz.web.util.GetCategoryUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @RequestMapping(value = {"/", ""})
    public String homePage
            (Model model,
             HttpServletRequest request
            ) {

        ServletContext servletContext = request.getServletContext();
        model.addAttribute("view", "view/homePage/homePage.html");
        model.addAttribute("category", GetCategoryUtils.getAllCategory());
        return "index.html";


    }


    @RequestMapping(value = {"/policy"})
    public String policy(Model model, HttpServletRequest request) {

        model.addAttribute("view", "view/policy");

        return "index";
    }

    @RequestMapping(value = {"/contact"})
    public String contact(Model model) {

        model.addAttribute("view", "view/contact");
        return "index";
    }

    @RequestMapping(value = "/download")
    public String download(Model model) {
        model.addAttribute("view", "view/download");
        return "index-2";
    }

    @RequestMapping(value = "/about")
    public String about(Model model) {
        model.addAttribute("view", "view/about.html");
        return "index.html";
    }


}
