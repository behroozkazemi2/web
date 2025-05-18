package com.behrouz.web.controller.login;

import com.behrouz.web.component.WebCartComponent;
import com.behrouz.web.configuration.CookieInterceptor;
import com.behrouz.web.exception.KoalaException;
import com.behrouz.web.okhttp.OkHttpHelper;
import com.behrouz.web.okhttp.api.ApiResponseBody;
import com.behrouz.web.okhttp.model.request.IdNameString;
import com.behrouz.web.okhttp.model.request.LoginRequest;
import com.behrouz.web.okhttp.model.request.RegisterRequest;
import com.behrouz.web.okhttp.model.request.VerifyRequest;
import com.behrouz.web.okhttp.model.response.ApiVerifyResponse;
import com.behrouz.web.okhttp.model.response.CartItemResponse;
import com.behrouz.web.okhttp.model.response.UserDetailResponse;
import com.behrouz.web.redis.RedisRegion;
import com.behrouz.web.rest.AjaxResponse;
import com.behrouz.web.rest.KoalaErrorResponse;
import com.behrouz.web.rest.LoginForm;
import com.behrouz.web.rest.VerifyForm;
import com.behrouz.web.rest.request.CartAddProductRequest;
import com.behrouz.web.rest.request.WebCartRedis;
import com.behrouz.web.security.captcha.CaptchaUtil;
import com.behrouz.web.security.session.model.SessionHolder;
import com.behrouz.web.util.CookieUtils;
import com.behrouz.web.util.StringUtil;
import com.behrouz.web.util.date.PersianDate;
import com.behrouz.web.util.date.PersianDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/auth")
public class LoginApi {

    Logger log = Logger.getLogger(LoginApi.class.getSimpleName());

    @Autowired
    private WebCartComponent webCartComponent;

    @Autowired
    private RedisRegion redisRegion;

    @RequestMapping(value = "/singUp")
    public AjaxResponse singUp(
            @RequestParam(value = "nationalCode") String nationalCode,
            @RequestParam(value = "first_name") String firstName,
            @RequestParam(value = "last_name") String lastName,
            @RequestParam(value = "mobile") String mobile,
            @RequestParam(value = "gender") boolean gender,
            @RequestParam(value = "birth") String birth,
            @CookieValue(name = CookieInterceptor.KEY_COOKIE) String cookie,
            @RequestParam(value = "captcha") String captcha,
            HttpServletRequest request
    ) {
        String answer = CaptchaUtil.getSavedCaptcha(request);

//        if (answer == null || !answer.equalsIgnoreCase(captcha)) {
//            return new AjaxResponse(false,"کد امنیتی نادرست است");
//        }
        RegisterRequest registerRequest = new RegisterRequest(lastName, firstName, gender, mobile, CookieUtils.getCookieToken(request), nationalCode);

        if(!StringUtil.isNullOrEmpty(birth)){
            String[] spli = birth.split("/");
            if(spli.length == 3 ){
                try{
                    Date date = PersianDateUtil.getDateFromPersianDate(new PersianDate(Integer.parseInt(spli[0]),Integer.parseInt(spli[1]),Integer.parseInt(spli[2])));
                    registerRequest.setBirthDate(date);
                }catch (Exception e){

                }
            }
        }

        ApiResponseBody response = OkHttpHelper.registerReq(registerRequest);
        if (!response.successful()) {
            return new AjaxResponse(false,response.getDescription());
        }

            ApiResponseBody<UserDetailResponse> user = OkHttpHelper.userReq((SessionHolder.getToken()));
            SessionHolder.login(user.getData(), (SessionHolder.getToken()));

            List<WebCartRedis> listCookie = webCartComponent.getCart(cookie);
            redisRegion.saveAddress(SessionHolder.getToken(), user.getData().getAddressId() == 0 ? -1 : user.getData().getAddressId());

            if (listCookie != null && !listCookie.isEmpty()) {
                OkHttpHelper.addCartToDatabase(listCookie.stream().map(e -> new CartAddProductRequest(e)).collect(Collectors.toList()));
            }

            ApiResponseBody<List<CartItemResponse>> listResponse =
                    OkHttpHelper.getCartFromDataBase();

            if (listResponse.successful()) {
                try {
                    for (CartItemResponse r : listResponse.getData()) {
                        webCartComponent.addCartToRedis(cookie, r);
                    }
                } catch (KoalaException e) {
                    log.warning("Bad Response For add Into Cart: " + e.getDescription());
                }
            }


            return new AjaxResponse(true,"");
    }


    @RequestMapping(value = "/singIn")
    public AjaxResponse singIn(
            @RequestParam(required = false, value = "mobile") String mobile,
            @RequestParam(value = "captcha") String captcha,
            HttpServletRequest request,
            @Valid LoginForm loginForm) {

        String answer = CaptchaUtil.getSavedCaptcha(request);

        if (answer == null || !answer.equalsIgnoreCase(captcha)) {
            return new AjaxResponse(false, "کد امنیتی نادرست است");

        }


        LoginRequest loginRequest = new LoginRequest(mobile, CookieUtils.getCookieToken(request));
        ApiResponseBody login = OkHttpHelper.loginReq(loginRequest);

        if (!login.successful()) {
            return new AjaxResponse(false, login.getDescription());
        } else {
            return new AjaxResponse(true, login);
        }
    }


    @RequestMapping(value = "/restore")
    public ResponseEntity restore(@RequestParam(required = false, value = "userName") String email) {

        // TODO: 6/7/20

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = {"/captcha"})
    public void captcha(HttpServletResponse response, HttpServletRequest request) throws IOException {

        try {
            byte[] imgByte =
                    CaptchaUtil.createImage(request);

            writeResponseImage(response, imgByte);

        } catch (IOException e) {

            response.sendError(HttpServletResponse.SC_NOT_FOUND);

        }
    }

    private void writeResponseImage(final HttpServletResponse response, byte[] imgByte) throws IOException {

        long time = System.currentTimeMillis();
        response.setContentType("image/jpeg");
        response.setHeader("Cache-Control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Last-Modified", time);
        response.setDateHeader("Date", time);
        response.setDateHeader("Expires", time);
        ServletOutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(imgByte);
        responseOutputStream.flush();
        responseOutputStream.close();

    }




    @RequestMapping(value = {"/verify"})
    public ResponseEntity verify(
            HttpServletRequest request,
            @RequestParam(required = false, value = "mobile") String mobile,
            @RequestParam(required = false, value = "verify_code") String verifyCode,
            @CookieValue(name = CookieInterceptor.KEY_COOKIE) String cookie,
            @Valid VerifyForm verifyForm) {
        VerifyRequest verifyRequest = new VerifyRequest(verifyForm.getMobile(), verifyForm.getVerify_code(), CookieUtils.getCookieToken(request), null, false);
        ApiResponseBody<ApiVerifyResponse> verify = OkHttpHelper.verifyReq(verifyRequest);
        if (!verify.successful()) {
            return new ResponseEntity<>(
                    Collections.singleton(new KoalaErrorResponse("verifyCode", verify.getDescription())),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        if (!verify.getData().isRegistration()) {
            ApiResponseBody<UserDetailResponse> user = OkHttpHelper.userReq(verify.getData().getToken());
            SessionHolder.login(user.getData(), verify.getData().getToken());

            List<WebCartRedis> listCookie = webCartComponent.getCart(cookie);
            redisRegion.saveAddress(SessionHolder.getToken(), user.getData().getAddressId() == 0 ? -1 : user.getData().getAddressId());

            if (listCookie != null && !listCookie.isEmpty()) {
                OkHttpHelper.addCartToDatabase(listCookie.stream().map(e -> new CartAddProductRequest(e)).collect(Collectors.toList()));
            }

            ApiResponseBody<List<CartItemResponse>> listResponse =
                    OkHttpHelper.getCartFromDataBase();

            if (listResponse.successful()) {
                try {
                    for (CartItemResponse r : listResponse.getData()) {
                        webCartComponent.addCartToRedis(cookie, r);
                    }
                } catch (KoalaException e) {
                    log.warning("Bad Response For add Into Cart: " + e.getDescription());
                }
            }

            return new ResponseEntity<>(
                    request.getHeader("Referer"),
                    HttpStatus.OK
            );
        }else {

            SessionHolder.noLogin(verify.getData().getToken());
            return new ResponseEntity<>(
                    new IdNameString( 0, mobile,"/sign-up"),
                    HttpStatus.OK
            );
        }

    }


    @PostMapping(value = {"/resendCode"})
    public ResponseEntity resendCode(
            Model model,
            @RequestParam(value = "mobile") String mobile,
            HttpServletRequest request) {

       /* if(bindingResult.hasErrors()) {
            return new ResponseEntity<>(
                    checkValidation(bindingResult),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }*/

/*
        String answer = CaptchaUtil.getSavedCaptcha(request);

        if(answer == null || !answer.equalsIgnoreCase(loginForm.getCaptcha())) {

            return new ResponseEntity<>(
                    Collections.singleton(new KoalaErrorResponse("captcha" , "کد امنیتی نادرست است")),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );

        }
*/

        LoginRequest loginRequest = new LoginRequest(mobile, CookieUtils.getCookieToken(request));
        ApiResponseBody resend = OkHttpHelper.resendReq(loginRequest);

        if (!resend.successful()) {
            return new ResponseEntity<>(
                    Collections.singleton(new KoalaErrorResponse("captcha", resend.getDescription())),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

        return new ResponseEntity<>(

                HttpStatus.OK
        );

    }

    @RequestMapping(value = {"/logout"})
    public void logOut(
            @CookieValue(name = CookieInterceptor.KEY_COOKIE) String cookie,
            HttpServletRequest request,
            HttpServletResponse response) {
        ApiResponseBody logout = OkHttpHelper.logoutReq();

        if(logout.successful()) {
            webCartComponent.clearCart(cookie);
        }

        new SecurityContextLogoutHandler().logout(
                request,
                response,
                SecurityContextHolder.getContext().getAuthentication()
        );
    }

}