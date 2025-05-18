package com.behrouz.web.controller.homePage;

import com.behrouz.web.rest.response.DataTableResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping(value = "/homePage")
public class HomePageController {

    @RequestMapping(value = {"/", ""})
    public String homePage
            (Model model,
             HttpServletRequest request) {
        return "redirect:/";


    }


    @RequestMapping(value = {"/productList"})
    public DataTableResponse homePageNewProduct
            (Model model,
             HttpServletRequest request) {


        return null;

    }


}
