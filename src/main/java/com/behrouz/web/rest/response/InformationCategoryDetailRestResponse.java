package com.behrouz.web.rest.response;


import java.util.List;

public class InformationCategoryDetailRestResponse {

    private List<InformationRestResponse> info;
    private String informationCategoryName;
    private long informationCategoryId;

    public InformationCategoryDetailRestResponse() {
    }

    public InformationCategoryDetailRestResponse(List<InformationRestResponse> info, String informationCategoryName, long informationCategoryId) {
        this.info = info;
        this.informationCategoryName = informationCategoryName;
        this.informationCategoryId = informationCategoryId;
    }

    public List<InformationRestResponse> getInfo() {
        return info;
    }
    public void setInfo(List<InformationRestResponse> info) {
        this.info = info;
    }

    public String getInformationCategoryName() {
        return informationCategoryName;
    }
    public void setInformationCategoryName(String informationCategoryName) {
        this.informationCategoryName = informationCategoryName;
    }

    public long getInformationCategoryId() {
        return informationCategoryId;
    }
    public void setInformationCategoryId(long informationCategoryId) {
        this.informationCategoryId = informationCategoryId;
    }
}
