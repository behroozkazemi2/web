package com.behrouz.web.okhttp.exception;


/**
 * Created By Hapi KZM
 **/


import com.behrouz.web.okhttp.api.HttpCode;

/**
 * extra param seen from api action method
 * when method has more than 2 params and param invalid
 */
public class ApiActionParamsException extends ApiActionException {

    public ApiActionParamsException(){
        super( HttpCode.REQUEST_REJECT , "invalid parameter" );
    }


}
