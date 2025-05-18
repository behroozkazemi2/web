package com.behrouz.web.controller.account;

import com.behrouz.web.okhttp.OkHttpHelper;
import com.behrouz.web.okhttp.model.response.ProviderResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.behrouz.web.okhttp.api.ApiResponseBody;
import com.behrouz.web.okhttp.model.response.FactorDetailResponse;
import com.behrouz.web.okhttp.model.response.RequestDetailResponse;
import com.behrouz.web.security.session.model.SessionHolder;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/user")
public class AccountController {

    @RequestMapping(value = {"", "/"})
    public String accountPanel(Model model,
                @RequestParam(value = "sp", required = false,defaultValue = "1") int sp,
                @RequestParam(value = "acc", required = false,defaultValue = "1") int acc
    ) {
        model.addAttribute("sp",sp);
        model.addAttribute("acc",acc);
        model.addAttribute("firstName",SessionHolder.getFirstName());
        model.addAttribute("secondName",SessionHolder.getLastName());
        model.addAttribute("mobile",SessionHolder.getMobile());
        model.addAttribute("view", "view/my-account");
        return "index";
    }


    @RequestMapping(value = {"/information"})
    public String orderInformation(Model model,
                                   @RequestParam(value = "id")int id
    ) {

        ApiResponseBody<FactorDetailResponse> factorDetail = OkHttpHelper.getFactorDetail(id);
        ApiResponseBody<List<ProviderResponse>> providers = OkHttpHelper.getFactorProviderList(id);
        if(providers.successful()){
            model.addAttribute("providers", providers.getData());
        }

        List<RequestDetailResponse> steps = factorDetail.getData().getSteps().stream().sorted(Comparator.comparingLong(RequestDetailResponse::getId)).collect(Collectors.toList());
        factorDetail.getData().setSteps(steps);

        model.addAttribute("id", id);
        model.addAttribute( "factor", factorDetail.getData() );
        model.addAttribute("view", "view/information.html");
        return "index";
    }

}
