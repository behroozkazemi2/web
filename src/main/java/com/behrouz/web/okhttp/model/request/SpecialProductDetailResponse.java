package com.behrouz.web.okhttp.model.request;

import com.behrouz.web.okhttp.model.response.SpecialProductDigestResponse;
import com.behrouz.web.okhttp.model.IdName;

import java.util.Date;
import java.util.List;

/**
 * Created by: HapiKZM
 **/
/*
 * Request:            End Point
 *
 * IdRequest -> app.customer.special.detail
 */
public class SpecialProductDetailResponse extends SpecialProductDigestResponse {

    protected List<SpecialProviderSuggestion> suggestion;


    public SpecialProductDetailResponse() {
    }

    public SpecialProductDetailResponse(int id, List<Integer> images, IdName region, IdName category, IdName provider, IdName status, IdName customer, Date date, String description, List<SpecialProviderSuggestion> suggestion) {
        super(id, images, region, category, provider, status, customer, date, description);
        this.suggestion = suggestion;
    }



    public List<SpecialProviderSuggestion> getSuggestion() {
        return suggestion;
    }
    public void setSuggestion(List<SpecialProviderSuggestion> suggestion) {
        this.suggestion = suggestion;
    }
}