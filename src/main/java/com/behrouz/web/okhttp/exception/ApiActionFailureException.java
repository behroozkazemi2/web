package com.behrouz.web.okhttp.exception;


import com.behrouz.web.okhttp.api.HttpCode;

/**
 * created by: Hapi
 **/


public class ApiActionFailureException extends ApiActionException{

    public ApiActionFailureException() {
        super( HttpCode.INTERNAL_SERVER_ERROR , "internal server error");
    }

    public ApiActionFailureException(String description) {
        super( HttpCode.INTERNAL_SERVER_ERROR , description);
    }
}
