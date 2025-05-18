package com.behrouz.web.controller.cart;


import com.behrouz.web.controller.BaseApi;
import com.behrouz.web.okhttp.OkHttpHelper;
import com.behrouz.web.okhttp.api.ApiResponseBody;
import com.behrouz.web.okhttp.model.response.PaymentAmount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.behrouz.web.component.WebCartComponent;
import com.behrouz.web.configuration.CookieInterceptor;
import com.behrouz.web.controller.shop.DataTable;
import com.behrouz.web.okhttp.model.request.FactorRequest;
import com.behrouz.web.okhttp.model.response.CartItemResponse;
import com.behrouz.web.okhttp.model.response.FactorPaymentResponse;
import com.behrouz.web.okhttp.model.response.RequestDetailResponse;
import com.behrouz.web.rest.AjaxResponse;
import com.behrouz.web.rest.request.WebCartRedis;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/cart")
public class CartApi extends BaseApi {

    @Autowired
    private WebCartComponent webCartComponent;

    @RequestMapping(value = "/list/get")
    public List<WebCartRedis> cartList(@CookieValue(name = CookieInterceptor.KEY_COOKIE) String cookie) {
        List<WebCartRedis> list = webCartComponent.getCart(cookie);
        return list;
    }


    @RequestMapping(value = "/cartTable")
    public AjaxResponse cartTable() {
        ApiResponseBody<List<CartItemResponse>> cartResponseBody = OkHttpHelper.getCartFromDataBase();
        if (cartResponseBody.successful()) {
            DataTable<CartItemResponse> table = new DataTable();
            List<CartItemResponse> list = cartResponseBody.getData();
            table.setData(list);
            table.setDraw(10);
            table.setStart(100);
            return new AjaxResponse(true, table);

        } else {
            return new AjaxResponse(false, cartResponseBody.getDescription());
        }

    }

    @RequestMapping(value = "/discount")
    public ApiResponseBody<Float> discount(
            @RequestParam(value = "code") String code) {
        RequestDetailResponse setDiscount = new RequestDetailResponse((long) 0, code);
        ApiResponseBody<Float> send = OkHttpHelper.discountCheck(setDiscount);
        if (send.successful()) {
            return send;
        } else {
            return send;
        }
    }

    @RequestMapping(value = "/factor")
    public AjaxResponse factor(
            @RequestParam(value = "address", defaultValue = "0") long address,
            @RequestParam(value = "discount", defaultValue = "") String discount,
            @RequestParam(value = "date", defaultValue = "1") Long date,
            @RequestParam(value = "time", defaultValue = "1") int time
    ) {
        Date newDate = new Date(date);
        // TODO GET FROM SESSIO NOR NOT
//        FactorRequest setVal = new FactorRequest((int) SessionHolder.getUserDetail().getAddressId(), discount, newDate, time);
        FactorRequest setVal = new FactorRequest((int) address, discount, "", newDate, time, 1);
        ApiResponseBody<FactorPaymentResponse> response = OkHttpHelper.payFactor(setVal);
        if (response.successful()) {
            return new AjaxResponse(true, response.getData());
        } else {
            return new AjaxResponse(false, response.getDescription());
        }
    }

    @RequestMapping(value = "/finalPayment")
    public AjaxResponse finalPayment(
            @CookieValue(name = CookieInterceptor.KEY_COOKIE) String cookie,
            @RequestParam(value = "address", defaultValue = "0") long address,
            @RequestParam(value = "userDescription", defaultValue = "") String userDescription,
            @RequestParam(value = "paymentMethod", defaultValue = "0") int paymentMethod,
            @RequestParam(value = "discount", defaultValue = "") String discount,
            @RequestParam(value = "date", defaultValue = "1") Long date,
            @RequestParam(value = "time", defaultValue = "1") int time) {

        Date newDate = new Date(date);

        // TODO GET FROM SESSIO NOR NOT
        FactorRequest setVal = new FactorRequest((int) address, discount, userDescription, newDate, time, paymentMethod);
        ApiResponseBody<PaymentAmount> response =
                OkHttpHelper.createFactor(setVal);

        if (response.successful()) {
            if (response.getData().getLink() != null && !response.getData().getLink().isEmpty()) {
                removeAllOrderFromCooki(cookie);
                return new AjaxResponse(true, response.getData());
                // go to bank
            } else {
                return new AjaxResponse(false, response.getDescription());

            }

        } else {
            return new AjaxResponse(false, response.getDescription());
        }


    }

    private void removeAllOrderFromCooki(String cookie) {
        webCartComponent.clearCart(cookie);

    }

    @PostMapping(value = {"/order/cart/clear"})
    public ResponseEntity clearcart(
            Model model,
            @CookieValue(name = CookieInterceptor.KEY_COOKIE) String cookie) {

        webCartComponent.clearCart(cookie);

        if (isAuthenticated()) {

            ApiResponseBody clear = OkHttpHelper.clearCart();

            if (!clear.successful()) {
                return new ResponseEntity<>(
                        clear.getDescription(),
                        HttpStatus.INTERNAL_SERVER_ERROR
                );
            }

        }

        return new ResponseEntity<>(
                HttpStatus.OK
        );

    }


    @RequestMapping(value = {"/getTime"})
    public ApiResponseBody getTime() {
        ApiResponseBody time = OkHttpHelper.getCandiDateTime();
        return time;
    }


    @RequestMapping(value = {"/verify"})
    public String verifyCheckout(Model model,
                                 @CookieValue(name = CookieInterceptor.KEY_COOKIE) String cookie) {

//        ApiResponseBody<Integer> factor = OkHttpHelper.verifyFactor();

        webCartComponent.clearCart(cookie);

//        return "redirect:/user/order/" + factor.getData();
        return "";
    }


//    @GetMapping(value = { "/pay" })
//    public String pay (Model model,
//                       @RequestParam(value = "address") int address,
//                       @RequestParam(required = false, value = "discount") String discount,
//                       @CookieValue( name = CookieInterceptor.KEY_COOKIE ) String cookie) {
//
//        ApiResponseBody newFactor = OkHttpHelper.createFactor(new RequestDetailResponse(address, discount));
//
//        ApiResponseBody<FactorPaymentResponse> payFactor = OkHttpHelper.payFactor();
//
//        model.addAttribute( "factor", payFactor.getData() );
//        return "view/user/payPage";
//
//    }
//




/*
    @RequestMapping(value = "/sendLocation")
    public AddressRequestResponse sendLocations(){
        ApiResponseBody<AddressRequestResponse> response = OkHttpHelper.getAddressRequest();
        if(response.successful()){
            return response.getData();
        }
        return null ;
    return response.getMessage();
}
*/

}