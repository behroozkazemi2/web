package com.behrouz.web.component;

import com.behrouz.web.okhttp.OkHttpHelper;
import com.behrouz.web.okhttp.api.ApiResponseBody;
import com.behrouz.web.okhttp.model.request.IdRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import com.behrouz.web.okhttp.model.response.AllCategoriesResponse;
import com.behrouz.web.okhttp.model.response.RegionResponse;
import com.behrouz.web.okhttp.model.response.RequestDetailResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SelectOptions {
    @Bean(name = "myRegion")
    protected selectOptionService getOption() {
        return this::getOptions;
    }
    public interface selectOptionService {
        List<RegionResponse> getMyOption();
    }
    private List<RegionResponse> getOptions() {

         ApiResponseBody<List<RegionResponse>> response = OkHttpHelper.region();
        return response.getData();


//        List<RegionIdName> setOptions = new ArrayList<>();
//        List<RegionIdName> mashhadSetOptions = new ArrayList<>();
//        RegionIdName mashhadRole1 = new RegionIdName(6,"وکیل آباد");
//        RegionIdName mashhadRole2 = new RegionIdName(7,"سناباد");
//        RegionIdName mashhadRole3 = new RegionIdName(8,"خیام");
//        mashhadSetOptions.add(mashhadRole1);
//        mashhadSetOptions.add(mashhadRole2);
//        mashhadSetOptions.add(mashhadRole3);
//        RegionIdName role0 = new RegionIdName(513,"مشهد",mashhadSetOptions);
//        List<RegionIdName> tehranSetOptions = new ArrayList<>();
//        RegionIdName tehranRole1 = new RegionIdName(9,"آزادی");
//        RegionIdName tehranRole2 = new RegionIdName(10,"نیاوران");
//        RegionIdName tehranRole3 = new RegionIdName(11,"اکباتان");
//        tehranSetOptions.add(tehranRole1);
//        tehranSetOptions.add(tehranRole2);
//        tehranSetOptions.add(tehranRole3);
//        RegionIdName role1 = new RegionIdName(1,"تهران");
//        RegionIdName role2 = new RegionIdName(2,"اصفهان");
//        RegionIdName role3 = new RegionIdName(3,"شیراز");
//        RegionIdName role4 = new RegionIdName(4,"کرمان");
//        RegionIdName role5 = new RegionIdName(5,"تبریز");
//        setOptions.add(role0);
//        setOptions.add(role1);
//        setOptions.add(role2);
//        setOptions.add(role3);
//        setOptions.add(role4);
//        setOptions.add(role5);
//        return setOptions;
    }

    @Bean(name = "providerCategory")
    protected selectOptionCategory getProviderOrderCategoryLists() {
        return this::getProviderCategoryList;
    }
    public interface selectOptionCategory {
        List<RequestDetailResponse> getProviderCategory();
    }
    private List<RequestDetailResponse> getProviderCategoryList() {
        List<RegionIdName> setOptions = new ArrayList<>();
        ApiResponseBody<List<AllCategoriesResponse>> allCategory = OkHttpHelper.categoriesList(new IdRequest(0));
        return allCategory.getData()
                .parallelStream()
                .filter(e -> e.getParent() != null)
                .map(m -> new RequestDetailResponse(m.getParent().getId(),
                        m.getParent().getName())).collect(Collectors.toList());
    }

//    @Bean(name = "providers")
//    protected providerOptions providersLists() {
//        return this::providersList;
//    }
//    public interface providerOptions {
//        List<RequestDetailResponse> providers();
//    }
//    private List<RequestDetailResponse> providersList() {
//        ApiResponseBody<List<ProviderResponse>> setOptions= OkHttpHelper.providerList(new ProductProviderCategoryRequest(
//                //13
//                new ArrayList<>(13),
//                null,
//                0,
//                1000));
//        return setOptions.getData()
//                .parallelStream()
//                .map(m -> new RequestDetailResponse(m.getId(),
//                        m.getName())).collect(Collectors.toList());
//    }



}
