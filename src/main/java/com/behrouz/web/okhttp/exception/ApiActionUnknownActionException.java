package com.behrouz.web.okhttp.exception;


/**
 * created by: Hapi
 **/


import com.behrouz.web.okhttp.api.HttpCode;

/**
 * unknown action received
 */
public class ApiActionUnknownActionException extends ApiActionException {

    public ApiActionUnknownActionException(){
        super( HttpCode.REQUEST_REJECT , "action not found");
    }


}
