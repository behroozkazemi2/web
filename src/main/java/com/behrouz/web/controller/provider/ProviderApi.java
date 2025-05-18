package com.behrouz.web.controller.provider;

import com.behrouz.web.okhttp.OkHttpHelper;
import com.behrouz.web.okhttp.model.response.ProviderResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.behrouz.web.controller.shop.DataTable;
import com.behrouz.web.okhttp.api.ApiResponseBody;
import com.behrouz.web.okhttp.model.request.ProductProviderCategoryRequest;
import com.behrouz.web.rest.AjaxResponse;
    import java.util.List;

@RestController
@RequestMapping(value = "/api/provider")
public class ProviderApi {

    @RequestMapping(value = "/providerList")
    public AjaxResponse tableCard(
            @RequestParam(name = "rg[]") List<Long> rg,
            @RequestParam(name = "pId[]",required = false) List<Long> pId,
            @RequestParam(value = "draw") int draw,
            @RequestParam("start") int start,
            @RequestParam("length") int length) {
        ApiResponseBody<List<ProviderResponse>> providerResponseBody = OkHttpHelper.providerList(
                new ProductProviderCategoryRequest(
                        pId,
                        0,
                        1000,
                        rg.get(0)
                )
        );
            if (providerResponseBody.successful()) {
            long total = providerResponseBody.getTotal();
            DataTable<ProviderResponse> table = new DataTable();
            table.setData(providerResponseBody.getData());
            table.setDraw(draw);
            table.setStart(start);
            table.setRecordsTotal(total);
            return new AjaxResponse(true, table);
        } else {
            return new AjaxResponse(false, providerResponseBody.getDescription());
        }
    }
    @RequestMapping(value = "/bestSeller")
    public AjaxResponse bestSeller(
            @RequestParam(value = "draw") int draw,
            @RequestParam("start") int start,
            @RequestParam("length") int length) {
        ApiResponseBody<List<ProviderResponse>> popularProviders = OkHttpHelper.popularProviders();
        if (popularProviders.successful()) {

            DataTable<ProviderResponse> table = new DataTable();
            List<ProviderResponse> bestSellerDetail = popularProviders.getData();
            table.setData(bestSellerDetail);
            table.setDraw(draw);
            table.setStart(start);
            return new AjaxResponse(true, table);
        } else {
            return new AjaxResponse(false, popularProviders.getDescription());
        }
    }



@RequestMapping(value = "/providerSlider")
    public AjaxResponse providerSlider() {
        ApiResponseBody<List<ProviderResponse>> popularProviders = OkHttpHelper.popularProviders();
        if (popularProviders.successful()) {

            return new AjaxResponse(true, "");
        } else {
            return new AjaxResponse(false, popularProviders.getDescription());
        }
    }

}