package com.behrouz.web.okhttp.model.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.behrouz.web.okhttp.model.IdName;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegionResponse extends IdName {


    private List<IdName> regions;

    public RegionResponse() {
    }

    public RegionResponse(int id, String name, List<IdName> regions) {
        this.id = id;
        this.name = name;
        this.regions = regions;
    }

    public List<IdName> getRegions() {
        return regions;
    }
    public void setRegions(List<IdName> regions) {
        this.regions = regions;
    }
}
