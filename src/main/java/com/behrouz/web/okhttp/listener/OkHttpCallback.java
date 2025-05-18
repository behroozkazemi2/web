package com.behrouz.web.okhttp.listener;


/**
 * Created By Hapi KZM
 */

public interface OkHttpCallback<T> {

    void onResponse ( T response );

    void onRequestReject ( int rejectCode, String message );

    void onFailure ( String errorMessage );


}