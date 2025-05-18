package com.behrouz.web.okhttp.model.request;


import com.behrouz.web.okhttp.model.IdName;

import java.util.List;

public class BannerRestRequest {

    private List<IdName> images;
    private long type;

    public BannerRestRequest() {
    }

    public BannerRestRequest(List<IdName> images) {
        this.images = images;
    }

    public List<IdName> getImages() {
        return images;
    }
    public void setImages(List<IdName> images) {
        this.images = images;
    }


    public long getType() {
        return type;
    }
    public void setType(long type) {
        this.type = type;
    }
}
