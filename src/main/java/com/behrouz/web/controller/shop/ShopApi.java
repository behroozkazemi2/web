package com.behrouz.web.controller.shop;

import com.behrouz.web.component.WebCartComponent;
import com.behrouz.web.configuration.CookieInterceptor;
import com.behrouz.web.data.cardItem.CardItem;
import com.behrouz.web.okhttp.OkHttpHelper;
import com.behrouz.web.okhttp.api.ApiResponseBody;
import com.behrouz.web.okhttp.model.IdName;
import com.behrouz.web.okhttp.model.request.IdLong;
import com.behrouz.web.okhttp.model.response.ProductDetailResponse;
import com.behrouz.web.okhttp.model.response.ProviderResponse;
import com.behrouz.web.okhttp.model.response.RegionResponse;
import com.behrouz.web.okhttp.model.response.pDetailWithPInCartResponse;
import com.behrouz.web.rest.AjaxResponse;
import com.behrouz.web.rest.SpecialFilterResponse;
import com.behrouz.web.rest.request.WebCartRedis;
import com.behrouz.web.security.session.model.SessionHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/api/shop")
public class ShopApi {

    @Autowired
    private WebCartComponent webCartComponent;
    Logger log = Logger.getLogger(CommentApi.class.getSimpleName());

    @RequestMapping(value = "/special/filter")
    public SpecialFilterResponse getSpecialFilter() {

//        List<RegionResponse> regions = Arrays.asList(
//                new RegionResponse(1 , "C1",null),
//                new RegionResponse(2 , "C2",null),
//                new RegionResponse(3 , "C3",null)
//        );
//
//        List<GroupCategoryResponse> categories = Arrays.asList(
//                new GroupCategoryResponse(1 , "CAT1",Arrays.asList(1,2)),
//                new GroupCategoryResponse(2 , "CAT2",Arrays.asList(1)),
//                new GroupCategoryResponse(3 , "CAT3",Arrays.asList(1,2,3))
//        );
//
//        List<GroupProviderResponse> providers = Arrays.asList(
//                new GroupProviderResponse(1 , "PAT1",Arrays.asList(1)),
//                new GroupProviderResponse(2 , "PAT2",Arrays.asList(1,2)),
//                new GroupProviderResponse(3 , "PAT3",Arrays.asList(1,2,3))
//        );
        SpecialFilterResponse response = new SpecialFilterResponse();
        ApiResponseBody<List<RegionResponse>> regionsReq = OkHttpHelper.region();
        ApiResponseBody<HashMap<Integer, List<IdName>>> categoriesReq = OkHttpHelper.groupCategory();
        ApiResponseBody<HashMap<Integer, HashMap<Integer, List<IdName>>>> providersReq = OkHttpHelper.groupProvider();
        if(!regionsReq.successful() || !categoriesReq.successful() || !providersReq.successful()){

            return response;
        }

        response.setCategories(categoriesReq.getData());
        response.setRegions(regionsReq.getData());
        response.setProviders(providersReq.getData());

        return response;
    }

    @RequestMapping(value = "/tableCard")
    public DataTable<CartList> tableCard(
            @RequestParam(required = false, value = "filterNav") int filterIndex,
            @RequestParam(required = false, value = "creatorFilter") String creatorFilter,
            @RequestParam(required = false, value = "modelFilter") String modelFilter,
            @RequestParam(required = false, value = "maxPriceFliter") int maxPriceFliter,
            @RequestParam(required = false, value = "minPriceFliter") int minPriceFliter,
            @RequestParam(value = "draw") int draw,
            @RequestParam("start") int start,
            @RequestParam("length") int length) {

        DataTable<CartList> table = new DataTable();
        List<CartList> cardDetaile = CardItem.fill();
        table.setData(cardDetaile);
        table.setDraw(draw);
        table.setStart(start);
        return table;
    }

    @RequestMapping(value = "/pro/detail")
    public AjaxResponse getProDetail(
            @RequestParam(required = false, value = "proId") int proId){
        ProviderResponse providerDetail = new ProviderResponse();
        ApiResponseBody<ProviderResponse> providerRequest = OkHttpHelper.providerDetail(proId);
        if(providerRequest.successful()){
            providerDetail = providerRequest.getData();
            return new AjaxResponse(true, providerDetail);

        }

        return new AjaxResponse(false, providerRequest.getMessage());    }


    @RequestMapping(value = "/p/detail/{pId}")
    public AjaxResponse getProductDetail(
            @PathVariable(required = false, name = "pId") int pId,
            @CookieValue(name = CookieInterceptor.KEY_COOKIE) String cookie) {
        // TODO CHECK Address_Id
        ApiResponseBody<ProductDetailResponse> response = OkHttpHelper.productDetail(new IdLong(pId, -1));
        if (response.successful()) {
            if (response.getData() != null) {
                if (SessionHolder.isAuthenticated()) {
                    List<WebCartRedis> inCartList = webCartComponent.getCart(cookie);
                    List<WebCartRedis> inCartProduct = inCartList.stream().filter(e -> e.getProductProviderId() == response.getData().getId()).collect(Collectors.toList());
                    if (inCartProduct != null && !inCartProduct.isEmpty()) {
                        return new AjaxResponse(true,new pDetailWithPInCartResponse(inCartProduct,response.getData()));

//                       inCartProduct.get(0) != null ? true : false:"");
//                       inCartProduct.get(0) != null ? inCartProduct.get(0).getUserDescription() : "");
//                       inCartProduct.get(0) != null ? inCartProduct.get(0).getCount() : "");
//                       inCartProduct.get(0) != null ? inCartProduct.get(0).getSelectedSubFeature() : "");

                    }
                }

                return new AjaxResponse(true, response);

            }
        }
        return new AjaxResponse(false, response.getMessage());

    }
}



