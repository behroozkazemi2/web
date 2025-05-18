package com.behrouz.web.controller.ProductCategory;

import com.behrouz.web.okhttp.OkHttpHelper;
import com.behrouz.web.okhttp.api.ApiResponseBody;
import com.behrouz.web.okhttp.model.IdNameString;
import com.behrouz.web.okhttp.model.request.IdRequest;
import com.behrouz.web.okhttp.model.response.AllCategoriesResponse;
import com.behrouz.web.util.GetCategoryUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping(value = "/productCategory")
public class ProductCategoryController {

    @RequestMapping(value = {"/{brandName}/{tagName}/{categoryName}"})
    public String categoryPage
            (Model model,
             @RequestParam(defaultValue = "0", name = "categoryId", required = false) long categoryId,
             @RequestParam(defaultValue = "0", name = "brandId", required = false) long brandId,
             @RequestParam(defaultValue = "0", name = "tagId", required = false) long tagId,
             @PathVariable(name = "brandName") String brandName,
             @PathVariable(name = "tagName") String tagName,
             @PathVariable(name = "categoryName") String categoryName,
             @RequestParam(name = "searchString", defaultValue = "") String searchString,
             HttpServletRequest request) {

        ApiResponseBody<List<AllCategoriesResponse>> category =
                OkHttpHelper.categoriesList(new IdRequest((int) categoryId));


        List<AllCategoriesResponse> allCategories = new ArrayList<>();

        if (category.getData().size() != 0) {
            for (AllCategoriesResponse cat : category.getData()) {
                allCategories.add(new AllCategoriesResponse(cat.getCurrent(), null, null));
                if (cat != null && cat.getChildren() != null) {
                    for (AllCategoriesResponse child : cat.getChildren()) {
                        allCategories.add(new AllCategoriesResponse(child.getCurrent(), null, null));
                    }
                }
            }
        }

        List<IdNameString> selectedCategoryImageAndDesc = allCategories.stream().filter(f -> f.getCurrent().getId() == categoryId).collect(Collectors.toList())
                .stream().map(m -> new IdNameString((int) m.getCurrent().getImageId(), m.getCurrent().getName(), m.getCurrent().getDescription())).collect(Collectors.toList());


        model.addAttribute("searchedTagName", tagName);
        model.addAttribute("categoryName", categoryName);
        model.addAttribute("searchedBrandName", brandName);


        String title = "فروشگاه اینترنتی بهتاتهویه";
        title += categoryId == 0 ? "" : "-" + categoryName;
        title += brandId == 0 ? "" : "-" + brandName;
        title += tagId == 0 ? "" : "-" + tagName;

        model.addAttribute("title", title);
        model.addAttribute("filteredCategory", category.getData());
        model.addAttribute("category", GetCategoryUtils.getAllCategory());
        model.addAttribute("searchedBrand", brandId);
        model.addAttribute("searchedTag", tagId);
        model.addAttribute("categoryDetail", (selectedCategoryImageAndDesc.size() == 0 ? new IdNameString(0, "", "") : new IdNameString(selectedCategoryImageAndDesc.get(0).getId(), selectedCategoryImageAndDesc.get(0).getName(), selectedCategoryImageAndDesc.get(0).getValue())));
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("searchString", searchString);
        model.addAttribute("view", "view/categoryPage/category.html");
        return "index.html";

    }

    private List<AllCategoriesResponse> getAllCategories(AllCategoriesResponse m) {
        List<AllCategoriesResponse> cat = new ArrayList<>();
        cat.add(m);
        if (m.getChildren() == null || m.getChildren().size() == 0) {
            return new ArrayList<>();
        }
        for (AllCategoriesResponse child : m.getChildren()) {
            cat.addAll(getAllCategories(child));
        }
        return cat;
    }


}
