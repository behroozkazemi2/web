package com.behrouz.web.controller.Status;

import com.behrouz.web.okhttp.OkHttpHelper;
import com.behrouz.web.okhttp.api.ApiResponseBody;
import com.behrouz.web.okhttp.model.response.FactorDetailResponse;
import com.behrouz.web.security.session.model.SessionHolder;
import com.behrouz.web.security.session.model.UserSessionDetail;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
@Controller
@RequestMapping(value = "/info")
public class StatusController {


    @RequestMapping(value = "/privacyPolicy" )
    public String privacyPolicy
            (Model model,
             HttpServletRequest request) {
        model.addAttribute("view", "view/othersPage/privacy-policy.html");
        return "index.html";

    }

    @RequestMapping(value = "/contactUs" )
    public String contactUs
            (Model model,
             HttpServletRequest request) {
        model.addAttribute("view", "view/othersPage/contact-us.html");
        return "index.html";

    }

    @RequestMapping(value = "/termsOfUse" )
    public String termsOfUse
            (Model model,
             HttpServletRequest request) {
        model.addAttribute("view", "view/othersPage/terms-of-use.html");
        return "index.html";

    }

    @RequestMapping(value = "/faq" )
    public String faq
            (Model model,
             HttpServletRequest request) {
        model.addAttribute("view", "view/othersPage/faq.html");
        return "index.html";

    }

    @RequestMapping(value = "/aboutUs" )
    public String aboutUs
            (Model model,
             HttpServletRequest request) {
        model.addAttribute("view", "view/othersPage/about-us.html");
        return "index.html";

    }

    @RequestMapping(value = "/paymentBack" )
    public String paymentBack
            (Model model,
             HttpServletRequest request) {
        model.addAttribute("view", "view/othersPage/payment-back.html");
        return "index.html";

    }

    @RequestMapping(value = "/orderFactor/{billId}" )
    public String orderFactor
            (Model model,
             @PathVariable(name = "billId") long billId,
             HttpServletRequest request) {
        ApiResponseBody<FactorDetailResponse> factorInfo = OkHttpHelper.getFactorDetail((int) billId);

        if (!factorInfo.successful()){
            model.addAttribute("view", "view/errorNotFound/404.html");
        }else {
            model.addAttribute("detail", factorInfo.getData());

            UserSessionDetail userDetail = SessionHolder.getUserDetail();
            model.addAttribute("userInfo", userDetail);
            model.addAttribute("view", "view/othersPage/order-factor.html");
        }
        return "index.html";

    }
}
