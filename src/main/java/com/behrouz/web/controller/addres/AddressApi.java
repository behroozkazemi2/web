package com.behrouz.web.controller.addres;

import com.behrouz.web.component.WebCartComponent;
import com.behrouz.web.configuration.CookieInterceptor;
import com.behrouz.web.okhttp.OkHttpHelper;
import com.behrouz.web.okhttp.api.ApiResponseBody;
import com.behrouz.web.okhttp.model.request.AddressRequestResponse;
import com.behrouz.web.okhttp.model.request.IdLong;
import com.behrouz.web.okhttp.model.request.IdRequest;
import com.behrouz.web.redis.RedisRegion;
import com.behrouz.web.rest.AjaxResponse;
import com.behrouz.web.rest.KoalaErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

import static com.behrouz.web.security.session.model.SessionHolder.isAuthenticated;

/**
 * Created by: Hapi
 * Company: Mobintabaran
 * Package: Mobintabaran
 * Project Name: shop-template
 * 14 June 2020
 **/

@RestController
@RequestMapping(value = "/api/address")
public class AddressApi {




    @Autowired
    private WebCartComponent webCartComponent;

    @Autowired
    private RedisRegion redisRegion;

    @RequestMapping(value = "/list")
    public ResponseEntity list(
    ) {
        ApiResponseBody<AddressRequestResponse> response = OkHttpHelper.getAddressRequest();
        if (!response.successful()) {
            return  ResponseEntity.ok(
                    new AjaxResponse(false, response.getDescription())
            );
        }
        return ResponseEntity.ok( new AjaxResponse(true, response.getData()));

    }
    @RequestMapping(value = "/getList")
    public ModelAndView getList(
    ) {
        ApiResponseBody<AddressRequestResponse> response = OkHttpHelper.getAddressRequest();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("address",response.getData());

        modelAndView.setViewName("/fragment/userPanelFragment.html::addressList");
        return modelAndView;
    }

    @RequestMapping(value = "/getList/changeable")
    public ModelAndView changeableGetAddressList(
            @CookieValue(name = CookieInterceptor.KEY_COOKIE) String cookie
    ) {
        ApiResponseBody<AddressRequestResponse> response = OkHttpHelper.getAddressRequest();
        ModelAndView modelAndView = new ModelAndView();

        // TODO GET FROM SESSION

        if (redisRegion.getAddress(cookie) == 0){
            response.setData(new AddressRequestResponse(
                    0L,
                    "مشهد بلوار طبرسی شمالی",0,0,
                    "پیش فرض",
                    "",
                    "",
                    ""
                    ));
        }

        long redisAddressId  = redisRegion.getAddress(cookie);
        modelAndView.addObject("selectedAddressId", redisAddressId);
        modelAndView.addObject("address",response.getData());
        modelAndView.setViewName("/fragment/userPanelFragment.html::addressList_changable");
        return modelAndView;
    }




    @RequestMapping(value = "/checkAddressLocationArea/{newAddressId}")
    public AjaxResponse checkNewAddresslocation(
            @PathVariable (name = "newAddressId") long newAddressId,
            @CookieValue(name = CookieInterceptor.KEY_COOKIE) String cookie

    ) {
        if ( isAuthenticated() ) {
            long redisAddressId  = redisRegion.getAddress(cookie);

            if (newAddressId != redisAddressId){
                ApiResponseBody<Boolean> isInSameArea = OkHttpHelper.checkNewAddressIsInSameArea(
                        new IdLong(
                                redisAddressId,
                                newAddressId
                        )
                );
                return new AjaxResponse(true, isInSameArea.getData());
            }
            return new AjaxResponse(true, "4");
        }
        return new AjaxResponse(false, "خطا در تغییر آدرس .");

    }


    @RequestMapping(value = "/changeSelected/{newAddressId}")
    public AjaxResponse changeAddress(
            @PathVariable (name = "newAddressId") long newAddressId,
            @CookieValue(name = CookieInterceptor.KEY_COOKIE) String cookie
    ) {

        if ( isAuthenticated() ) {

            redisRegion.saveAddress(cookie, newAddressId);

        }
        return new AjaxResponse(true, "تغییر یافت");

    }




    @PostMapping(value = {"/order/cart/clear/{newAddressId}"})
    public ResponseEntity clearcart(
            Model model,
            @PathVariable (name = "newAddressId") long newAddressId,
            @CookieValue(name = CookieInterceptor.KEY_COOKIE) String cookie) {

        webCartComponent.clearCart(cookie);

        if (isAuthenticated()) {
            ApiResponseBody clear = OkHttpHelper.clearCart();
            if (!clear.successful()) {
                return new ResponseEntity<>(
                        new AjaxResponse(false, clear.getDescription()),
                        HttpStatus.INTERNAL_SERVER_ERROR
                );
            }

        }
        return new ResponseEntity<>(new AjaxResponse(true, newAddressId),HttpStatus.OK);

    }




    @RequestMapping(value = "/getModalAddress/{addressId}")
    public ModelAndView modalAddress(
            @PathVariable (name = "addressId") long addressId
    ){
        ModelAndView modelAndView = new ModelAndView();
        AddressRequestResponse addressDetail = new AddressRequestResponse();

        ApiResponseBody<AddressRequestResponse> item = OkHttpHelper.getAddressDetail(new IdRequest((int) addressId));
        addressDetail = item != null ? item.getData()
                : new AddressRequestResponse() ;

        String  title = addressId == 0 ? "افزودن آدرس " : "ویرایش آدرس";
        String  info = addressId == 0 ? "آدرس شما بصورت پیش فرض با توجه به موقعیت مکانی شما در نظر گرفته شده است. " : "لطفا آدرس را در نقشه هم ویرایش نمایید.  ";
        modelAndView.addObject("title",title );
        modelAndView.addObject("info",info );
        modelAndView.addObject("data",addressDetail == null ? new AddressRequestResponse() : addressDetail );
        modelAndView.setViewName("/fragment/userPanelFragment.html::address_modal");
        return modelAndView;
    }


    @RequestMapping(value = {"/addOrEdit"})
    public AjaxResponse postedit(Model model,
                                 @RequestParam(value = "id") long id,
                                 @RequestParam(value = "address") String address,
                                 @RequestParam(value = "province", defaultValue = "خراسان رضوی") String province,
                                 @RequestParam(value = "city", defaultValue = "مشهد") String city,
                                 @RequestParam(value = "title") String title,
                                 @RequestParam(value = "postalCode") String postalCode,
                                 @RequestParam(value = "lat") double lat,
                                 @RequestParam(value = "lng") double lng,
                                 @CookieValue(name = CookieInterceptor.KEY_COOKIE) String cookie) {
        AddressRequestResponse addressreq = new AddressRequestResponse(
                id ,
                address,
                lat,
                lng,
                title,
                province,
                city,
                postalCode
        );

        ApiResponseBody<IdRequest> add = OkHttpHelper.addAddressRequest(addressreq);
        if ( isAuthenticated() ) {
            long redisAddressId = redisRegion.getAddress(cookie);

            if (redisAddressId == 0 || redisAddressId == -1) {

                redisRegion.saveAddress(cookie, add.getData().getId());
            }
            if (!add.successful()) {
                return new AjaxResponse(false, add.getDescription());
            } else {
                return new AjaxResponse(true, add.getData());
            }
        }
        return new AjaxResponse(false, add.getDescription());

    }

    @RequestMapping(value = {"/edit"})
    public ResponseEntity postEditAddress(Model model,
                                          @RequestParam(value = "id") int id,
                                          @RequestParam(value = "address") String address,
                                          @RequestParam(value = "title") String title,
                                          @RequestParam(value = "postalCode") String postalCode,
                                          @RequestParam(value = "city", defaultValue = "مشهد") String city,
                                          @RequestParam(value = "province", defaultValue = "خراسان‌رضوی") String province,
                                          @RequestParam(value = "lat") double lat,
                                          @RequestParam(value = "lng") double lng) {

        if (address != null && address.isEmpty()) {
            return new ResponseEntity<>(
                    Collections.singleton(new KoalaErrorResponse("address" + id, "فیلد آدرس الزامی است")),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

        AddressRequestResponse editAddress = new AddressRequestResponse(id, address, lat, lng, title, province, city,postalCode);

        ApiResponseBody edit = OkHttpHelper.addAddressRequest(editAddress);

        if (!edit.successful()) {
            return new ResponseEntity<>(
                    Collections.singleton(new KoalaErrorResponse("address" + id, edit.getDescription())),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

        return new ResponseEntity<>(
                "ok",
                HttpStatus.OK
        );

    }

    @RequestMapping(value = {"/getSessionAddress"})
    public ResponseEntity getSessionAddress(Model model,
                                            @CookieValue(name = CookieInterceptor.KEY_COOKIE) String cookie) {
            if (isAuthenticated()){
                long redisAddressId = redisRegion.getAddress(cookie);

                ApiResponseBody<AddressRequestResponse> item = OkHttpHelper.getSelectedAddressDetail(new IdRequest((int) redisAddressId));
                if (item.getData() != null && item.getData().getId() != 0){
                    redisRegion.saveAddress(cookie, item.getData().getId());
                }else {
                    redisRegion.saveAddress(cookie, -1);

                }
                return new ResponseEntity<>(
                        item.getData() != null && item.getData().getId() != 0 ? item.getData()
                                : new AddressRequestResponse()
                        ,
                        HttpStatus.OK
                );
            }
        return new ResponseEntity<>(
                false,
                HttpStatus.OK
        );

    }

    @RequestMapping(value = {"/delete"})
    public ResponseEntity deleteAddress(Model model,
                                        @RequestParam(value = "addressId") int id,
                                        @CookieValue(name = CookieInterceptor.KEY_COOKIE) String cookie) {

        ApiResponseBody delete = OkHttpHelper.deleteAddressRequest(new IdRequest(id));

        if (!delete.successful()) {
            return new ResponseEntity<>(
                    Collections.singleton(new KoalaErrorResponse("address", delete.getDescription())),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        if (isAuthenticated()) {
            long redisAddressId = redisRegion.getAddress(cookie);
            if (redisAddressId == id){
                redisRegion.saveAddress(cookie, -1);
            }
        }
        return new ResponseEntity<>(
                "ok",
                HttpStatus.OK
        );

    }

}
