package com.behrouz.web.component;

import java.util.List;

public class RegionIdName {
    private int id;
    private String name;
    private List<RegionIdName> subRegion;

    public RegionIdName() {
    }
    public RegionIdName(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public RegionIdName(int id, String name, List<RegionIdName> subRegion) {
        this.id = id;
        this.name = name;
        this.subRegion = subRegion;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<RegionIdName> getSubRegion() {
        return subRegion;
    }
    public void setSubRegion(List<RegionIdName> subRegion) {
        this.subRegion = subRegion;
    }

}
