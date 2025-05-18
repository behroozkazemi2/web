package com.behrouz.web.okhttp.model.response;

import java.util.List;

/**
 * Created by: HapiKZM
 **/
public class DataTableResponse<T>  {

    private int draw;
    private int start;
    private long recordsTotal;
    private long recordsFiltered;
    private List<T> data;


    public DataTableResponse () {
    }


    public int getDraw() {
        return draw;
    }
    public void setDraw(int draw) {
        this.draw = draw;
    }



    public long getRecordsTotal() {
        return recordsTotal;
    }
    public void setRecordsTotal(long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }


    public long getRecordsFiltered() {
        return recordsFiltered;
    }
    public void setRecordsFiltered(long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }



    public List<T> getData() {
        return data;
    }
    public DataTableResponse setData(List<T> data) {
        this.data = data;
        return this;
    }


    public int getStart() {
        return start;
    }
    public void setStart(int start) {
        this.start = start;
    }

}
