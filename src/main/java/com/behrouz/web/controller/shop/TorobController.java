package com.behrouz.web.controller.shop;


import com.behrouz.web.okhttp.OkHttpHelper;
import com.behrouz.web.okhttp.api.ApiResponseBody;
import com.behrouz.web.okhttp.model.request.ProductProviderCategoryRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.behrouz.web.okhttp.model.response.ProductResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TorobController {




    @RequestMapping(value = "/shop/torob/apifortorobbymobintabaran/{page}/{limit}")
    public Object tableSearch(@PathVariable("page") int page, @PathVariable("limit") int limit) {
        ProductProviderCategoryRequest req = new ProductProviderCategoryRequest(
                //0
                null,
                0,
                null,
                null,
                0,
                0,
                0,
                0,
                -1
        );
        req.setPage(page);
        req.setLength(limit);

        ApiResponseBody<List<ProductResponse>> respopnse = OkHttpHelper.productList(req);

        HashMap<String, Object> responseResult = new HashMap<>();
        if(!respopnse.successful()){
            responseResult.put("message", "خطایی در گرفتن اطلاعات از سرور رخ داده");
        }else {

            List<ProductResponse> data = respopnse.getData();
            List<HashMap<String, Object>> result = new ArrayList<>();
            for (ProductResponse d : data) {
                HashMap<String, Object> cur = new HashMap<>();
                cur.put("ximaProductUrl", "https://shop.behtatahvie.com/p/" + d.getId());
                cur.put("productId", d.getId());
                cur.put("productName", d.getName());
                cur.put("productExistence", d.isExistence());
                cur.put("shortDescription", d.getShortDescription());
                cur.put("fullDescription", d.getFullDescription());
//                cur.put("showOrderInList", d.getOrder());
                cur.put("provider", d.getProvider());
                cur.put("productUnit", d.getUnit());
                cur.put("productUnitStep", d.getUnitStep());
                cur.put("productCategory", d.getCategory());
                cur.put("minCountAllow", d.getMinAllow());
                cur.put("maxCountAllow", d.getMaxAllow());
                cur.put("prepareHour", d.getPrepareHour());
                cur.put("primitivePrice", d.getPrimitiveAmount());
                cur.put("offPercentPrice", d.getOffPercent());
                cur.put("offAmountPrice", d.getOffPrice());
                cur.put("finalAmountPrice", d.getFinalAmount());
                cur.put("minAmount", d.getFinalAmount() * d.getMinAllow());
                List<String> images = d.getImages() == null ? null : d.getImages().stream().map(e -> "https://shop.behtatahvie.com/thumbnail/files/original/" + e).collect(Collectors.toList());
                cur.put("productImages", images);
                cur.put("productTags", d.getTags());
//                cur.put("productSubFeature", d.getSubFeatureResponse());
                cur.put("providerRegion", d.getProviderRegion());

                result.add(cur);
            }

            responseResult.put("message", "عملیات موفق");
            responseResult.put("data", result);
            responseResult.put("count", result.size());
        }


        return responseResult;

    }
}
