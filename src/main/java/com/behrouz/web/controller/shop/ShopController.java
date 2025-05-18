package com.behrouz.web.controller.shop;

import com.behrouz.web.okhttp.OkHttpHelper;
import com.behrouz.web.okhttp.api.ApiResponseBody;
import com.behrouz.web.okhttp.model.response.ProviderResponse;
import com.behrouz.web.security.session.model.SessionHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/shop")
public class ShopController {


    @RequestMapping(value = {"", "/"})
    public String shop(Model model,
                       @RequestParam(value = "p", required = false,defaultValue = "0") int providerId,
                       @RequestParam(value = "pc", required = false,defaultValue = "0") String providerCategory,
                       @RequestParam(value = "srch", required = false,defaultValue = "") String search,
                       @RequestParam(value = "tag", required = false,defaultValue = "") Integer tag,
                       @RequestParam(value = "rg", required = false,defaultValue = "0") int region) {
        model.addAttribute("rg", region);
        model.addAttribute("setId", providerId);
        model.addAttribute("search", search);

        ProviderResponse providerDetail = new ProviderResponse();



        ApiResponseBody<ProviderResponse> providerRequest = OkHttpHelper.providerDetail(providerId);
        if(providerRequest.successful()){
            providerDetail = providerRequest.getData();
        }


        model.addAttribute( "login", (SessionHolder.getUserDetail() != null));
        model.addAttribute( "setId", providerId);
        model.addAttribute( "providerDetail", providerDetail);
        model.addAttribute("providerCategory", providerCategory);
        model.addAttribute("tg", tag);
        model.addAttribute("view", "view/shop");
        return "index";
    }



    @RequestMapping(value = {"/popUp"})
    public ModelAndView popUpModal(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/fragment/popUp_modal.html::pop_up_modal");
        return modelAndView;

    }
}
