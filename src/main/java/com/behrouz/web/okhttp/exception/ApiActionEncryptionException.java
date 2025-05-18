package com.behrouz.web.okhttp.exception;


import com.behrouz.web.okhttp.api.HttpCode;

/**
 * created by: Hapi
 **/


public class ApiActionEncryptionException extends ApiActionException{

    public ApiActionEncryptionException() {
        super( HttpCode.REQUEST_REJECT , "‌bad encryption, check private and public key");
    }
}
