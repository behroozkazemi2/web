package com.behrouz.web.okhttp.exception;


/**
 * Created By Hapi KZM
 **/


import com.behrouz.web.okhttp.api.HttpCode;

/**
 * received bad request
 * param not found or
 * param format is not in api action param format
 */
public class ApiActionBadRequestException extends ApiActionException {

    public ApiActionBadRequestException(){
        super( HttpCode.REQUEST_REJECT , "‌bad parameter");
    }


}
