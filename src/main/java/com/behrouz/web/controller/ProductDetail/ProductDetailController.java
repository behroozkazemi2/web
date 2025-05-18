package com.behrouz.web.controller.ProductDetail;


import com.behrouz.web.component.WebCartComponent;
import com.behrouz.web.configuration.CookieInterceptor;
import com.behrouz.web.okhttp.OkHttpHelper;
import com.behrouz.web.okhttp.api.ApiResponseBody;
import com.behrouz.web.okhttp.model.request.CommentListRequest;
import com.behrouz.web.okhttp.model.request.IdLong;
import com.behrouz.web.okhttp.model.response.ProductDetailResponse;
import com.behrouz.web.redis.RedisRegion;
import com.behrouz.web.rest.request.WebCartRedis;
import com.behrouz.web.rest.response.*;
import com.behrouz.web.security.session.model.SessionHolder;
import com.behrouz.web.util.GetCategoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/product")
public class    ProductDetailController {


    @Autowired
    private WebCartComponent webCartComponent;

    @Autowired
    private RedisRegion redisRegion;

    @RequestMapping(value = "/{productProviderId}/{productName}")
    public String productDetailPage
            (Model model,
             @PathVariable(name = "productProviderId") long productProviderId,
             @PathVariable(name = "productName") String productName,
             @CookieValue(name = CookieInterceptor.KEY_COOKIE) String cookie,
             HttpServletRequest request) {

        long addressId = -1;

        if ( SessionHolder.isAuthenticated() ) {
            addressId =  redisRegion.getAddress(cookie);
        }

        // TODO CHECK Address_Id
        ApiResponseBody<ProductDetailResponse> productDetail =
                OkHttpHelper.productDetail(new IdLong((int) productProviderId, -1 ));

        // TODO GET DEFAULT COUNT
        List<WebCartRedis> list = webCartComponent.getCart(cookie);
        List<WebCartRedis> pd = list.stream().filter(f -> f.getId() == productProviderId).collect(Collectors.toList());
        model.addAttribute("inCartCount",  pd.size() != 0  && pd.get(0) != null ?
                pd.get(0).getCount() :
                productDetail.getData().getMinAllow());


        productDetail.getData().setInCartCount(productDetail.getData().getInCartCount());
        model.addAttribute("productDetail", productDetail.getData());
        model.addAttribute("productId", productDetail.getData().getProductId());
        model.addAttribute("productProviderId", productProviderId);
        model.addAttribute("category", GetCategoryUtils.getAllCategory());
        model.addAttribute("isAuthenticated", SessionHolder.isAuthenticated());


        CommentListRequest resRequest = new CommentListRequest(
                0,
                10000,
                (int) productProviderId,
                0
        );

        ProductDetailCommentDetailRestResponse resultDetail =
                webCartComponent.getProductCommentDetail(
                        resRequest
                );
        model.addAttribute("pprate", resultDetail.getTotalRatePercent());
        model.addAttribute("view", "view/productDetail/single-product.html");
        return "index.html";

    }

    @RequestMapping(value = "/addToCart")
    public ResponseEntity addToCart(Model model,
                                    @ModelAttribute InCartProductCardRstResponse inCart,
                                    HttpServletRequest request
    ) {

        // just get id and count from object
        return ResponseEntity.ok("AddedToCart");
    }

    @RequestMapping(value = "/increase/{productProviderId}")
    public ResponseEntity increaseProductCount
            (@PathVariable(name = "productProviderId") long productProviderId,
             @CookieValue(name = CookieInterceptor.KEY_COOKIE) String cookie,
             HttpServletRequest request) {

        List<WebCartRedis> list = webCartComponent.getCart(cookie);
        List<WebCartRedis> pd = list.stream().filter(f -> f.getId() == productProviderId).collect(Collectors.toList());
        return ResponseEntity.ok(pd.size() == 0 ? 0 : pd.get(0).getCount());

    }
}
