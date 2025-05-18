package com.behrouz.web.controller.product;

import com.behrouz.web.okhttp.OkHttpHelper;
import com.behrouz.web.okhttp.api.ApiResponseBody;
import com.behrouz.web.okhttp.model.request.IdLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import com.behrouz.web.component.WebCartComponent;
import com.behrouz.web.configuration.CookieInterceptor;
import com.behrouz.web.okhttp.model.response.ProductDetailResponse;
import com.behrouz.web.rest.request.WebCartRedis;
import com.behrouz.web.security.session.model.SessionHolder;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/p")
public class ProductController {

    @Autowired
    private WebCartComponent webCartComponent;

    @RequestMapping(value = "/{productId}")
    public String product(Model model,
                          @PathVariable(value = "productId", required = true) int productProviderId,
                          @RequestParam(value = "rg", required = false,defaultValue = "0") int region,
                          @CookieValue(name = CookieInterceptor.KEY_COOKIE) String cookie ) {
        // TODO CHECK Address_Id
            ApiResponseBody<ProductDetailResponse> response = OkHttpHelper.productDetail(new IdLong(productProviderId, -1));

        if(!response.successful()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND
            );
        }

        if(response.getData() != null) {

            if ( SessionHolder.isAuthenticated() ) {

                List <WebCartRedis> inCartList = webCartComponent.getCart( cookie );
                List<WebCartRedis> inCartProduct = inCartList.stream().filter(e -> e.getProductProviderId() == response.getData().getId() ).collect( Collectors.toList() );
                if(inCartProduct != null && !inCartProduct.isEmpty()) {

                    model.addAttribute("SELECTED", inCartProduct.get(0) != null ? true : false);
                    model.addAttribute("userDescription", inCartProduct.get(0) != null ? inCartProduct.get(0).getUserDescription() : "");
                    model.addAttribute("inCartCount", inCartProduct.get(0) != null ? inCartProduct.get(0).getCount() : "");
                }
            }
            model.addAttribute("productDetails", response.getData());
//            model.addAttribute("unitPriceChange", false ? "در حال بررسی" : response.getData().moneyFormat((long) response.getData().getPrimitiveAmount()));

            if(response.getData().getTags() != null) {
                model.addAttribute("tagSize", response.getData().getTags().size());
            }
            if(response.getData().getCategory() != null) {
                model.addAttribute("categoryName", response.getData().getCategory() .getName());
            }

        }

        model.addAttribute( "login", (SessionHolder.getUserDetail() != null));
        model.addAttribute("view", "view/product-details");
        return "index";
    }
}
