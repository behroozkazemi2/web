package com.behrouz.web.okhttp.exception;


import com.behrouz.web.okhttp.api.HttpCode;

/**
 * created by: Hapi
 **/


public class ApiActionNotFoundException extends ApiActionException {


    public ApiActionNotFoundException() {
        super(HttpCode.REQUEST_REJECT);
    }

    public ApiActionNotFoundException(String description) {
        super(HttpCode.REQUEST_REJECT , description);
    }

}
