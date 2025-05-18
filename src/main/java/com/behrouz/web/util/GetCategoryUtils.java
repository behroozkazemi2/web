package com.behrouz.web.util;

import com.behrouz.web.okhttp.OkHttpHelper;
import com.behrouz.web.okhttp.api.ApiResponseBody;
import com.behrouz.web.okhttp.model.request.IdRequest;
import com.behrouz.web.okhttp.model.response.AllCategoriesResponse;

import java.util.List;


public class GetCategoryUtils {

    public static List<AllCategoriesResponse> getAllCategory() {
        ApiResponseBody<List<AllCategoriesResponse>> allCategory =
                OkHttpHelper.categoriesList(new IdRequest((int) 0));

        return allCategory.getData();
    }

}
