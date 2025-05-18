package com.behrouz.web.okhttp.exception;


import com.behrouz.web.okhttp.api.HttpCode;

/**
 * Created By Hapi KZM
 * Package ir.mobintabaran.xima.server.api.driverApplication
 * Project ximaserver
 * 12 July 2018 11:53
 **/


public class ApiActionWrongDataException extends ApiActionException {

    public ApiActionWrongDataException() {
        super(HttpCode.REQUEST_REJECT , "data wrong");
    }

    public ApiActionWrongDataException(String description) {
        super(HttpCode.REQUEST_REJECT , description);
    }
}
