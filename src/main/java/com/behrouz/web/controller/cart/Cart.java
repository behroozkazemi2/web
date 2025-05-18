package com.behrouz.web.controller.cart;

import com.behrouz.web.rest.response.InCartProductCardRstResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/cart")
public class Cart {
    @RequestMapping(value = {"/", ""})
    public String card(Model model) {
        model.addAttribute("view", "view/cartPage/cart.html");
        return "index.html";
    }
    @RequestMapping(value = {"/get/inCartProduct"})
    public ResponseEntity inCardProduct(Model model) {

        ModelAndView modelAndView = new ModelAndView();
        // userId
        List<InCartProductCardRstResponse> list = new ArrayList<>();
        list.add(new InCartProductCardRstResponse(1 ,1, "کیک", 5000, 4500, 10,2));
        list.add(new InCartProductCardRstResponse(4 ,1, "کیک _1", 5000, 4500, 10,4));
        list.add(new InCartProductCardRstResponse(5 ,1, "کیک _2", 5000, 4500, 10,4));
        return ResponseEntity.ok(list);
    }

    @RequestMapping(value = {"/remove/inCartProduct/{productId}"})
    public ResponseEntity removeInCardProduct(Model model,
                                              @PathVariable (name = "productId") long productId) {

        // userId
        // TODO REMOVE FROM INCART PRODUCT

        return ResponseEntity.ok("Remover");
    }
}
