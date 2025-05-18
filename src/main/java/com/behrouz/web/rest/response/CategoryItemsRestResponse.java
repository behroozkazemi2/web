package com.behrouz.web.rest.response;

public class CategoryItemsRestResponse {

   private long categoryId;
   private String search;

    public CategoryItemsRestResponse() {
    }

    public CategoryItemsRestResponse(long categoryId, String search) {
        this.categoryId = categoryId;
        this.search = search;
    }


    public long getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }


    public String getSearch() {
        return search;
    }
    public void setSearch(String search) {
        this.search = search;
    }
}
