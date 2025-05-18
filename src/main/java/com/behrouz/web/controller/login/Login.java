package com.behrouz.web.controller.login;

import com.behrouz.web.component.WebCartComponent;
import com.behrouz.web.rest.DateRestResponse;
import com.behrouz.web.security.session.model.SessionHolder;
import com.behrouz.web.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
public class Login {

    @Autowired
    private WebCartComponent webCartComponent;

    @RequestMapping(value = {"/login/","/login"})
    public String login(HttpServletRequest request, HttpServletResponse response, Model model){
        if(SessionHolder.isAuthenticated()){
            return "redirect:/";
        }
        model.addAttribute("view","view/loginPage/login");
        String referer = "/";
        if(
                request != null &&
                        request.getSession() != null &&
                        request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST") != null &&
                        request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST") instanceof DefaultSavedRequest){

            DefaultSavedRequest beforeUrl = (DefaultSavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
            if(!StringUtil.isNullOrEmpty(beforeUrl.getRequestURI())) {
                referer = beforeUrl.getRequestURI();
            }
        }
        model.addAttribute("login" , true);
        model.addAttribute("Referer" , referer);
        return "index.html";
    }

    @RequestMapping(value = {"/sign-up/","/sign-up"})
    public String signUp(HttpServletRequest request,
                         @RequestParam(name = "mobile",defaultValue = "") String mobile,
                         HttpServletResponse response,
                         Model model){
        if(SessionHolder.isAuthenticated()){

            return "redirect:/";
        }


        String referer = "/";
        if(
                request != null &&
                        request.getSession() != null &&
                        request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST") != null &&
                        request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST") instanceof DefaultSavedRequest){

            DefaultSavedRequest beforeUrl = (DefaultSavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
            if(!StringUtil.isNullOrEmpty(beforeUrl.getRequestURI())) {
                referer = beforeUrl.getRequestURI();
            }
        }
        model.addAttribute("login" , false);
        model.addAttribute("Referer" , referer);
        model.addAttribute("date" , new DateRestResponse(new Date()));
        model.addAttribute("mobile_content" , mobile);
        model.addAttribute("view","view/loginPage/login");
        System.out.println(referer);
        return "index";
    }


    @RequestMapping(value = "/getLogIn/Content")
    public ModelAndView logInFragment(
    ){
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("/fragment/logIn.html::logIn");
        return modelAndView;
    }

    @RequestMapping(value = "/getVerify/Content/{isFromLogin}")
    public ModelAndView verifyFragment(
            @PathVariable(name = "isFromLogin" ) boolean isFromLogin
    ){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("isFromLogin", isFromLogin);
        modelAndView.setViewName("/fragment/logIn.html::verify");
        return modelAndView;
    }

    @RequestMapping(value = "/getSignUp/Content")
    public ModelAndView signUpFragment(
            @RequestParam(name = "mobile", defaultValue = "") String mobile
    ){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("mobile", mobile);
        modelAndView.setViewName("/fragment/logIn.html::singUp");
        return modelAndView;
    }

}
