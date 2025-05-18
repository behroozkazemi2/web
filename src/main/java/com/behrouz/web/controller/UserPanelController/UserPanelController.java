package com.behrouz.web.controller.UserPanelController;

import com.behrouz.web.okhttp.OkHttpHelper;
import com.behrouz.web.okhttp.api.ApiResponseBody;
import com.behrouz.web.okhttp.model.request.FactorListRequest;
import com.behrouz.web.okhttp.model.request.ListRequest;
import com.behrouz.web.okhttp.model.response.FactorResponse;
import com.behrouz.web.okhttp.model.response.MoneyRequestResponse;
import com.behrouz.web.security.session.model.SessionHolder;
import com.behrouz.web.security.session.model.UserSessionDetail;
import com.behrouz.web.util.GetCategoryUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/UserPanel")
public class UserPanelController {

    @RequestMapping(value = {"/" , ""})
    public String userPanel
            (Model model,
             @RequestParam (name = "type" ,defaultValue = "0") long type,
             @RequestParam (name = "walletCharge" ,defaultValue = "false", required = false) boolean walletCharge,
             @RequestParam (name = "failed" ,defaultValue = "false", required = false) boolean failed,
             HttpServletRequest request) {
        ApiResponseBody<MoneyRequestResponse> userBalance = OkHttpHelper.getUserBalance();
        if (userBalance.successful()) {
            model.addAttribute("walletCharge", userBalance.getData().getAmount());
        } else{
            model.addAttribute("walletCharge", 0);
        }

        model.addAttribute("category", GetCategoryUtils.getAllCategory());
        model.addAttribute("type", type);
        model.addAttribute("walletChargeFlag", walletCharge);
        model.addAttribute("failed", failed);
        model.addAttribute("detail", SessionHolder.getUserDetail());
        model.addAttribute("view", "view/userPanel/userPanel.html");
        return "index.html";

    }


    @RequestMapping(value = {"/dashboard"})
    public ModelAndView dashboard1
            (Model model,
             HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("detail", SessionHolder.getUserDetail());
        ListRequest listRequest = new ListRequest(1, 6);
        ApiResponseBody<List<FactorResponse>> response = OkHttpHelper.getFactorHistory(new FactorListRequest(1, 6, 0));
        modelAndView.addObject("orders",response.getData());
        modelAndView.setViewName("/fragment/userPanelFragment.html::dashboard");
        return modelAndView;
    }

    @RequestMapping(value = {"/addresses"})
    public ModelAndView addresses
            (Model model,
             HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/fragment/userPanelFragment.html::addresses");
        return modelAndView;
    }

    @RequestMapping(value = {"/interests"})
    public ModelAndView interests
            (Model model,
             HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/fragment/userPanelFragment.html::interests");
        return modelAndView;
    }

    @RequestMapping(value = {"/offCode"})
    public ModelAndView off
            (Model model,
             HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/fragment/userPanelFragment.html::offCode");
        return modelAndView;
    }

    @RequestMapping(value = {"/orderDetails"})
    public ModelAndView orderDetails
            (Model model,
             HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/fragment/userPanelFragment.html::orderDetails");
        return modelAndView;
    }

    @RequestMapping(value = {"/orders"})
    public ModelAndView orders
            (Model model,
             HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/fragment/userPanelFragment.html::orders");
        return modelAndView;
    }

    @RequestMapping(value = {"/userInfo"})
    public ModelAndView userInfo
            (Model model,
             HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView();
        UserSessionDetail userDetail = SessionHolder.getUserDetail();
        modelAndView.addObject("detail", userDetail);
        modelAndView.setViewName("/fragment/userPanelFragment.html::userInfo");
        return modelAndView;
    }

    @RequestMapping(value = {"/walletCharge"})
    public ModelAndView walletCharge
            (Model model,
             HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/fragment/userPanelFragment.html::walletCharge");
        return modelAndView;
    }


    @RequestMapping(value = {"/ticket"})
    public ModelAndView ticket
            (Model model,
             HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/fragment/userPanelFragment.html::ticket");
        return modelAndView;
    }

}
