package com.behrouz.web.controller.shop;

import com.behrouz.web.component.WebCartComponent;
import com.behrouz.web.okhttp.OkHttpHelper;
import com.behrouz.web.okhttp.api.ApiResponseBody;
import com.behrouz.web.rest.response.ProductDetailCommentDetailRestResponse;
import com.behrouz.web.security.session.model.SessionHolder;
import com.behrouz.web.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.behrouz.web.okhttp.model.request.CommentListRequest;
import com.behrouz.web.okhttp.model.request.CommentRequest;
import com.behrouz.web.okhttp.model.response.CommentResponse;
import com.behrouz.web.rest.AjaxResponse;
import com.behrouz.web.rest.request.CommentResponseRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/api/comment")
public class CommentApi {
    Logger log = Logger.getLogger(CommentApi.class.getSimpleName());

    @Autowired
    private WebCartComponent webCartComponent;


    @RequestMapping(value = "/userComments/detail/{productDetail}")
    public ResponseEntity productDetailCommentsDetail(Model model,
                                                      @PathVariable (name = "productDetail") long id,
                                                      HttpServletRequest request
    ) {
        CommentListRequest resRequest = new CommentListRequest(
                0,
                10000,
                (int) id,
                0
        );

        ProductDetailCommentDetailRestResponse resultDetail =
                webCartComponent.getProductCommentDetail(
                        resRequest
                );
        return ResponseEntity.ok(resultDetail);
    }

    @RequestMapping(value = "/product/list/{productId}/{page}/{size}")
    public List<CommentResponseRequest> commentProductList(
            @PathVariable(name = "productId") int productId,
            @PathVariable(name = "page") int page,
            @PathVariable(name = "size") int size) {

        ApiResponseBody<List<CommentResponse>> listReq =
                OkHttpHelper.getCommentList(
                        new CommentListRequest(page, size, productId, 0)
                );
        if(!listReq.successful()){
            log.warning("commentProductList fail!");
            return new ArrayList<>();
        }

        return listReq.getData().stream().map(CommentResponseRequest::new).collect(Collectors.toList());
    }

    @RequestMapping(value = "/product/send/{productId}")
    public AjaxResponse productCommentAdd(
            @PathVariable(value = "productId") int productId,
            @RequestParam(value = "text", defaultValue = "") String text,
            @RequestParam(value = "email", defaultValue = "", required = false) String email,
            @RequestParam(value = "commenter", defaultValue = "", required = false) String commenter,
            @RequestParam(value = "rate", defaultValue = "0", required = false) int rate) {

        if (StringUtil.isNullOrEmpty(commenter) && !SessionHolder.isAuthenticated() )
            return new AjaxResponse(false, "لطفا نام را وارد نمایید");
        if (StringUtil.isNullOrEmpty(email) && !SessionHolder.isAuthenticated())
            return new AjaxResponse(false, "لطفا ایمیل را وارد نمایید");
        if (StringUtil.isNullOrEmpty(text))
            return new AjaxResponse(false, "لطفا متن را وارد نمایید.");
        if (rate == 0 || rate > 5)
            return new AjaxResponse(false, "لطفا امتیاز را به درستی وارد نمایید.");
    if (text.length() > 500)
            return new AjaxResponse(false, "حداکثر طول متن ۵۰۰ کلمه می‌باشد.");
    if (text.length() < 10)
            return new AjaxResponse(false, "حداقل طول متن ۱۰ کلمه می‌باشد.");
    if (!SessionHolder.isAuthenticated() && commenter.length() > 25)
            return new AjaxResponse(false, "حداکثر طول نام ۲۵ کلمه می‌باشد.");
    if (!SessionHolder.isAuthenticated() && email.length() > 40)
            return new AjaxResponse(false, "حداکثر طول ایمیل ۴۰ کلمه می‌باشد.");

        ApiResponseBody addReq = OkHttpHelper.addComment(
                new CommentRequest(
                        SessionHolder.isAuthenticated(),
                        email,
                        commenter,
                        productId,
                        0,
                        text,
                        rate
                )
        );
        if(!addReq.successful()){
            return new AjaxResponse(false, addReq.getMessage());
        }

        return new AjaxResponse(true, "Ok");

    }



}




