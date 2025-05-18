package com.behrouz.web.controller.product;

import com.behrouz.web.okhttp.OkHttpHelper;
import com.behrouz.web.okhttp.model.request.*;

import com.behrouz.web.okhttp.model.response.*;
import com.behrouz.web.redis.RedisRegion;
import com.behrouz.web.rest.response.InformationCategoryDetailRestResponse;
import com.behrouz.web.rest.response.InformationRestResponse;
import com.behrouz.web.security.session.model.SessionHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.behrouz.web.component.WebCartComponent;
import com.behrouz.web.configuration.CookieInterceptor;
import com.behrouz.web.controller.shop.DataTable;
import com.behrouz.web.exception.KoalaException;
import com.behrouz.web.okhttp.api.ApiResponseBody;
import com.behrouz.web.option.ProductOrderOption;
import com.behrouz.web.rest.AjaxResponse;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/product")
public class ProductApi {

    @Autowired
    private WebCartComponent webCartComponent;
    @Autowired
    private RedisRegion redisRegion;


    @RequestMapping(value = "/search")
    public AjaxResponse tableSearch(
            @RequestParam(required = false, value = "pvdId[]", defaultValue = "0") List<Long> provider,
            @RequestParam(required = false, name = "rg[]") List<Long> rg,
            @RequestParam(required = false, value = "pdtCat[]", defaultValue = "0") List<Long> category,
            @RequestParam(required = false, value = "brands[]", defaultValue = "0") List<Long> brands,
            @RequestParam(required = false, value = "existence", defaultValue = "false") boolean existence,
            @RequestParam(required = false, value = "srch", defaultValue = "") String search,
            @RequestParam(required = false, value = "order", defaultValue = "0") int order,
            @RequestParam(required = false, value = "maxP", defaultValue = "0") int maxPrice,
            @RequestParam(required = false, value = "minP", defaultValue = "0") int minPrice,
            @RequestParam(required = false, value = "tags[]") List<Long> tags,
            @RequestParam(required = false, value = "draw", defaultValue = "12") int draw,
            @RequestParam(value = "start", defaultValue = "0") int start,
            @RequestParam(value = "length", defaultValue = "12") int length,
            @CookieValue(name = CookieInterceptor.KEY_COOKIE) String cookie) {
        long addressId = -1;

        if (SessionHolder.isAuthenticated()) {
            addressId = redisRegion.getAddress(cookie);
        }

        ProductProviderCategoryRequest req = new ProductProviderCategoryRequest(
                //0
                category,
                provider == null ? 0 : provider.get(0),
                search,
                tags,
                order,
                maxPrice,
                minPrice,
                rg == null ? 0 : rg.get(0),
                brands,
                existence,
                addressId == 0 ? -1 : addressId
        );
        req.setPage(start);
        req.setLength(length);

        ApiResponseBody<List<ProductResponse>> respopnse = OkHttpHelper.productList(req);
        if (respopnse.successful()) {
            DataTable<ProductResponse> table = new DataTable();
            table.setData(respopnse.getData());
            table.setDraw(draw);
            table.setStart(start);
            table.setRecordsTotal(respopnse.getTotal());
            return new AjaxResponse(true, table);
        } else {
            return new AjaxResponse(false, respopnse.getDescription());
        }
    }


    @RequestMapping(value = "/promoteProduct/special")
    public AjaxResponse specialPromoteProduct(
            @CookieValue(name = CookieInterceptor.KEY_COOKIE) String cookie
    ) {
        long addressId = -1;

        if (SessionHolder.isAuthenticated()) {
            addressId = redisRegion.getAddress(cookie);
        }

        ApiResponseBody<PromotePromoteProductResponse> respopnse = OkHttpHelper.specialPromoteProductList(new IdRequest(addressId));
        if (respopnse.successful()) {
            return new AjaxResponse(true, respopnse.getData());
        } else {
            return new AjaxResponse(false, respopnse.getDescription());
        }
    }
    @RequestMapping(value = "/promoteProduct/last")
    public AjaxResponse lastPromoteProduct(
            @CookieValue(name = CookieInterceptor.KEY_COOKIE) String cookie
    ) {
        long addressId = -1;

        if (SessionHolder.isAuthenticated()) {
            addressId = redisRegion.getAddress(cookie);
        }

        ApiResponseBody<PromotePromoteProductResponse> respopnse = OkHttpHelper.lastPromoteProductList(new IdRequest(addressId));
        if (respopnse.successful()) {
            return new AjaxResponse(true, respopnse.getData());
        } else {
            return new AjaxResponse(false, respopnse.getDescription());
        }
    }


    @RequestMapping(value = {"/banner"})
    public AjaxResponse banners
            (Model model,
             HttpServletRequest request) {

        ApiResponseBody<List<BannerRestRequest>> bannerImages =
                OkHttpHelper.bannerGetDetail();

        if (!bannerImages.successful()){
            return new AjaxResponse(false, "خطا در برقراری ارتباط");

        }
        return new AjaxResponse(true, bannerImages.getData());

    }

    @RequestMapping(value = "/addToCart")
    public AjaxResponse addCartToDataBase(
            @RequestParam(required = false, value = "pId") long productProviderId,
            @RequestParam(required = false, value = "v") float values,
            @RequestParam(required = false, value = "d") String description,
            @CookieValue(name = CookieInterceptor.KEY_COOKIE) String cookie) {
        try {
            long addressId = -1;

            if (SessionHolder.isAuthenticated()) {
                addressId = redisRegion.getAddress(cookie);
            }
            // TODO CHECK Address_Id
            ApiResponseBody<ProductDetailResponse> response = OkHttpHelper.productDetail(new IdLong((int) productProviderId, -1));

            if (!response.successful()) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND
                );
            }

            ApiResponseBody resCheckData =
                    OkHttpHelper.checkProductCount(new IdLong(productProviderId, (long) values));
            if (!resCheckData.successful())
                return new AjaxResponse(false, resCheckData.getDescription());


            webCartComponent.updateCartByData(cookie, response.getData(), values, description, addressId == 0 ? -1 : addressId);
            return new AjaxResponse(true, "Ok");
        } catch (KoalaException e) {
            return new AjaxResponse(false, e.getDescription());
        }

    }

    @RequestMapping(value = "/checkIsAnyProductInArea")
    public AjaxResponse checkIsAnyProductInArea(
            @CookieValue(name = CookieInterceptor.KEY_COOKIE) String cookie) {
        long addressId = -1;

        if (SessionHolder.isAuthenticated()) {
            addressId = redisRegion.getAddress(cookie);
        }
        ApiResponseBody<Boolean> response = OkHttpHelper.checkIsAnyProductInArea(new IdLong( 1 , addressId));

        if (!response.successful()) {
            return new AjaxResponse(false, "Not_ok");
        }

        return new AjaxResponse(true, response.getData() );

    }

    @RequestMapping(value = "/model/type/{catId}")
    public AjaxResponse modelTypes(
            @PathVariable(name = "catId") long catId
    ) {
        ApiResponseBody<List<AllCategoriesResponse>> allCategory = OkHttpHelper.categoriesList(new IdRequest((int) catId));
        if (allCategory.successful()) {
            return new AjaxResponse(true, allCategory.getData());
        } else {
            return new AjaxResponse(false, allCategory.getDescription());
        }
    }

    @RequestMapping(value = "/tagList")
    public AjaxResponse getTagList(
            @RequestParam(value = "pCat[]", required = false) List<Long> providerId
    ) {
        ApiResponseBody<List<RequestDetailResponse>> req = OkHttpHelper.tagsList(providerId);
        if (req.successful()) {
            return new AjaxResponse(true, req.getData());
        } else {
            return new AjaxResponse(false, req.getDescription());
        }
    }


    @RequestMapping(value = "/sp")
    public AjaxResponse simillar(
            @RequestParam(value = "tags[]") List<Long> tags,
            @RequestParam(value = "cId") List<Long> categoryId,
            @RequestParam(value = "proId") int providerId
    ) {
        int proId = providerId;
        ProductProviderCategoryRequest req = new ProductProviderCategoryRequest();
        if (tags == null) {
            req.setProductCategoryId(categoryId);
        } else {
            req.setTag(tags);
        }
        req.setPage(0);
        req.setLength(20);
        ApiResponseBody<List<ProductResponse>> res = OkHttpHelper.productList(req);
        if (res.successful()) {
            return new AjaxResponse(true, res.getData());
        } else {
            return new AjaxResponse(false, res.getDescription());
        }
    }


    @RequestMapping(value = "/popularProduct")
    public AjaxResponse popularProduct(
            @RequestParam(value = "rg=[0]", required = false) List<Long> rg,
            @RequestParam(value = "draw") int draw,
            @RequestParam("start") int start,
            @RequestParam("length") int length,
            @CookieValue(name = CookieInterceptor.KEY_COOKIE) String cookie
    ) {

        long addressId = -1;

        if (SessionHolder.isAuthenticated()) {
            addressId = redisRegion.getAddress(cookie);
        }

        ProductProviderCategoryRequest req = new ProductProviderCategoryRequest(
                //0,
                new ArrayList<>(),
                0,
                null,
                null,
                ProductOrderOption.SAIL.getId(),
                0,
                0,
                rg == null ? 0 : rg.get(0),
                addressId == 0 ? -1 : addressId

        );
        req.setPage(start);
        req.setLength(length);

        ApiResponseBody<List<ProductResponse>> popularProducts = OkHttpHelper.productList(req);

        if (popularProducts.successful()) {
            DataTable<ProductResponse> table = new DataTable();
            table.setData(popularProducts.getData());
            table.setDraw(draw);
            table.setStart(start);
            return new AjaxResponse(true, table);
        } else {
            return new AjaxResponse(false, "");
        }
    }

    @RequestMapping(value = "/information/{productId}")
    public ModelAndView getInfoList(
            @PathVariable(name = "productId") long productId
    ) {
        ApiResponseBody<List<InformationRestResponse>> response =
                OkHttpHelper.productInformationCategory(new IdRequest(productId));

        ModelAndView modelAndView = new ModelAndView();

        List<InformationCategoryDetailRestResponse> groupByList =
                convertProductInfoToInfoCategory(response.getData());


        modelAndView.addObject("response",groupByList);

        modelAndView.setViewName("/fragment/informationCategory.html::info");
        return modelAndView;
    }

    private List<InformationCategoryDetailRestResponse> convertProductInfoToInfoCategory(List<InformationRestResponse> data) {

        Map<Long, List<InformationRestResponse>> mapList =
                data.stream().collect(
                        Collectors.groupingBy(
                                InformationRestResponse::getInformationCategoryId
                        )
                );

        List<InformationCategoryDetailRestResponse> res = new ArrayList<>();

        for (Map.Entry<Long, List<InformationRestResponse>> map : mapList.entrySet()){
            InformationRestResponse sameDetail =
                    map.getValue().get(0);

            res.add(
                    new InformationCategoryDetailRestResponse(
                            map.getValue(),
                            sameDetail.getInformationCategoryName(),
                            sameDetail.getInformationCategoryId()

                    )
            );
        }
        return res;
    }

}