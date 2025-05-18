package com.behrouz.web.okhttp.exception;


/**
 * Created By Hapi KZM
 **/


import com.behrouz.web.okhttp.api.HttpCode;

/**
 * null point param send to action
 * ApiActionParam when nullable = false and send null param to method
 * this exception throw
 */
public class ApiActionParamException extends ApiActionException {

    public ApiActionParamException(){
        super( HttpCode.REQUEST_REJECT , "bad parameter");
    }


}
