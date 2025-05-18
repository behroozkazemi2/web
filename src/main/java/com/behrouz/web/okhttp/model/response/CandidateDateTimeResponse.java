package com.behrouz.web.okhttp.model.response;

import java.util.Date;
import java.util.List;

/**
 * Created by: hapi
 * 09 July 2020
 **/
public class CandidateDateTimeResponse {

    private List<Date> dates;

    private List<RequestDetailResponse> times;


    public CandidateDateTimeResponse() {
    }


    public List<Date> getDates() {
        return dates;
    }
    public void setDates(List<Date> dates) {
        this.dates = dates;
    }


    public List<RequestDetailResponse> getTimes() {
        return times;
    }
    public void setTimes(List<RequestDetailResponse> times) {
        this.times = times;
    }


}
