package com.behrouz.web.controller.account;

import com.behrouz.web.okhttp.OkHttpHelper;
import com.behrouz.web.okhttp.api.ApiResponseBody;
import com.behrouz.web.okhttp.model.request.AddressRequestResponse;
import com.behrouz.web.okhttp.model.request.FactorListRequest;
import com.behrouz.web.okhttp.model.request.IdRequest;
import com.behrouz.web.okhttp.model.request.ListRequest;
import com.behrouz.web.okhttp.model.response.*;
import com.behrouz.web.rest.AjaxResponse;
import com.behrouz.web.rest.KoalaErrorResponse;
import com.behrouz.web.security.session.model.SessionHolder;
import com.behrouz.web.security.session.model.UserSessionDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
public class AccountApi {

    @RequestMapping(value = "/order")
    public ModelAndView order(
            @RequestParam(value = "length") int length,
            @RequestParam(value = "start") int start,
            @RequestParam(value = "draw") int draw,
            @RequestParam(value = "factorType", defaultValue = "0", required = false) long factorType
    ) {

        int page = start / length;
        ListRequest listRequest = new ListRequest(page, length);
        ApiResponseBody<List<FactorResponse>> response = OkHttpHelper.getFactorHistory(new FactorListRequest(page, length, factorType));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("orders",response.getData());
        modelAndView.setViewName("/fragment/userPanelFragment.html::orderList");
        return modelAndView;

    }


    @RequestMapping(value = "/orderInfo")
    public ModelAndView orderInformation(
            @RequestParam(value = "id") int id) {
        ApiResponseBody<FactorDetailResponse> factorInfo = OkHttpHelper.getFactorDetail(id);
        ModelAndView modelAndView = new ModelAndView();

        UserSessionDetail userDetail = SessionHolder.getUserDetail();
        modelAndView.addObject("userInfo", userDetail);

        modelAndView.addObject("order",factorInfo.getData());
        modelAndView.setViewName("/fragment/userPanelFragment.html::orderDetails");
        return modelAndView;
    }


    @RequestMapping(value = "/discount")
    public ModelAndView discountorder(
            @RequestParam(value = "length") int length,
            @RequestParam(value = "start") int start,
            @RequestParam(value = "draw") int draw) {
        int page = start / length;
        ListRequest listRequest = new ListRequest(page, length);
        ApiResponseBody<List<OffCodeResponse>> response = OkHttpHelper.discountList(listRequest);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("offCodes",response.getData());
        modelAndView.setViewName("/fragment/userPanelFragment.html::offCodeList");
        return modelAndView;
    }


    @RequestMapping(value = "/walletShow")
    public AjaxResponse walletShow() {

        ApiResponseBody<MoneyRequestResponse> userBalance = OkHttpHelper.getUserBalance();
        if (userBalance.successful()) {
            return new AjaxResponse(true, userBalance);

        } else {
            return new AjaxResponse(false, userBalance.getDescription());
        }
    }

    @RequestMapping(value = "/walletCharge")
    public String walletChange(
            @RequestParam(value = "amount") String amount) {

        long amountInt = 0;
        try{
            amountInt=Long.parseLong(amount);

        }catch (Exception e){
            e.printStackTrace();
            // khata dar dade string (add vard she )
            return null;
        }
        ApiResponseBody<String> req = OkHttpHelper.charcgeBalance(new MoneyRequestResponse(amountInt));
        if (!req.successful()){
            //showERROR
            return null;
        }

        /*ApiResponseBody<MoneyRequestResponse> userBalance = OkHttpHelper.getUserBalance();
         */
        return req.getData();
    }


        @RequestMapping(value = {"/address/edit"})
    public ResponseEntity editAddress(Model model,
                                      @RequestParam(value = "addId") int addId,
                                      @RequestParam(value = "address") String address,
                                      @RequestParam(value = "title") String title,
                                      @RequestParam(value = "city") String city,
                                      @RequestParam(value = "province") String province,
                                      @RequestParam(value = "lat") double lat,
                                      @RequestParam(value = "lng") double lng) {

        AddressRequestResponse editAddress = new AddressRequestResponse(addId, address, lat, lng, title, province, city,"");

        ApiResponseBody edit = OkHttpHelper.addAddressRequest(editAddress);

        if (!edit.successful()) {
            return new ResponseEntity<>(
                    Collections.singleton(new KoalaErrorResponse("address" + addId, edit.getDescription())),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

        return new ResponseEntity<>(
                "ok",
                HttpStatus.OK
        );
    }


    @PostMapping(value = {"address/delete"})
    public ResponseEntity deleteAddress(Model model,
                                        @RequestParam(value = "id") int id) {

        ApiResponseBody delete = OkHttpHelper.deleteAddressRequest(new IdRequest(id));

        if (!delete.successful()) {
            return new ResponseEntity<>(
                    Collections.singleton(new KoalaErrorResponse("address", delete.getDescription())),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }


        return new ResponseEntity<>(
                "ok",
                HttpStatus.OK
        );

    }


    @RequestMapping(value = {"/edit"})
    public ResponseEntity editUser(Model model,
                                   @RequestParam(value = "firstname") String firstName,
                                   @RequestParam(value = "lastname") String lastname
    ) {
/*

        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>(
                    checkValidation(bindingResult),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
*/

        UserDetailResponse user = new UserDetailResponse(
                firstName,
                lastname,
                null,
                0
        );

        ApiResponseBody edit = OkHttpHelper.editUserRequest(user);

        if (!edit.successful()) {
            return new ResponseEntity<>(
                    Collections.singleton(new KoalaErrorResponse("name", edit.getDescription())),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
//        SessionHolder.getUserDetail().setFirstName(firstName);
//        SessionHolder.getUserDetail().setLastName(lastname);
        SessionHolder.setfirstName(firstName);
        SessionHolder.setlastName(lastname);

        return new ResponseEntity<>(
                new AjaxResponse( true, firstName + " " + lastname  ),
                HttpStatus.OK
        );

    }

}
