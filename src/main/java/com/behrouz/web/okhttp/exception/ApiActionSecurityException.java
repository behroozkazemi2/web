package com.behrouz.web.okhttp.exception;


import com.behrouz.web.okhttp.api.HttpCode;

/**
 * created by: Hapi
 **/


public class ApiActionSecurityException extends ApiActionException{


    public ApiActionSecurityException(){
        super( HttpCode.NOT_ALLOW , "not allowed" );
    }

}
