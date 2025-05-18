package com.behrouz.web.okhttp.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.behrouz.web.values.Links;
import com.behrouz.web.okhttp.api.ApiRequestBody;
import com.behrouz.web.okhttp.api.ApiResponseBody;
import com.behrouz.web.okhttp.api.HttpCode;
import com.behrouz.web.okhttp.listener.OkHttpCallback;
import okhttp3.*;
import org.springframework.lang.NonNull;
import com.behrouz.web.util.StringUtil;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;


/**
 * Created By Hapi KZM
 */

public class OkHttpRequest {


    private static final MediaType MEDIA_TYPE           = MediaType.parse("text/html; charset=utf-8");


    private static final int CONNECTION_TIME_OUT_SECOND = 15;


    private static final int READ_TIME_OUT_SECOND       = 10;

    private static final boolean DEBUG_MODE = true;

    private static final String TAG = OkHttpRequest.class.getSimpleName();


    private static OkHttpClient client                  = null;


    private static OkHttpClient getOkHttpClient() {

        if(client == null){

            client = new OkHttpClient.Builder()
                    .connectTimeout(CONNECTION_TIME_OUT_SECOND, TimeUnit.SECONDS)
                    .readTimeout(READ_TIME_OUT_SECOND , TimeUnit.SECONDS)
                    .build();
        }

        return client;
    }





    public static ApiResponseBody postRequest(
            final ApiRequestBody requestBody) {

        try {

            showRequestLog(requestBody);

            Request request =
                    new Request.Builder()
                            .url(Links.USER_AND_FINANCIAL)
                            .post(createRequestBody(requestBody))
                            .build();

            Response response = getOkHttpClient().newCall(request).execute();
            return createResponseBody(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ApiResponseBody().failure();
    }


    private static ApiResponseBody createResponseBody(Response response) throws Exception {
        if(response == null || response.body() == null ){
            throw new IOException("received response from server has null body(call to Hapi)");
        }

        if(!response.isSuccessful()){
            throw new IOException("server response code is not ok, response code: " + response.code() + "\n (call to Hapi)\n");
        }

        String body = response.body().string();
        if(StringUtil.isNullOrEmpty(body)){
            throw new IOException("received body is empty (call to Hapi)");
        }

        String decryptedBody = new Encryption().decrypt(body);

        return new ObjectMapper().readValue(decryptedBody , ApiResponseBody.class);
    }


    private static RequestBody createRequestBody(ApiRequestBody requestBody) throws JsonProcessingException, NoSuchAlgorithmException {
        return RequestBody.create(
                        MEDIA_TYPE,
                        new Encryption().encrypt(new ObjectMapper().writeValueAsString(requestBody))
                );

    }

    private static void showRequestLog(ApiRequestBody requestBody) throws JsonProcessingException {
        if(DEBUG_MODE) {
            System.out.println(TAG +  "\trun: " + "send request: " + (new ObjectMapper()).writeValueAsString(requestBody));
        }
    }

    private static void showResponseLog(ApiResponseBody responseBody) throws JsonProcessingException {
        if(DEBUG_MODE) {
           System.out.println(TAG +  "\trun: " + "received response: " + (new ObjectMapper()).writeValueAsString(responseBody));
        }
    }



    static class DefaultOkHttpCallBack implements Callback {


        final OkHttpCallback callback;

        final KoalaEndPoint endPoint;

        public DefaultOkHttpCallBack(OkHttpCallback callback , KoalaEndPoint endPoint) {
            this.callback = callback;
            this.endPoint = endPoint;
        }

        @Override
        public void onFailure( @NonNull Call call, @NonNull IOException e) {

            call.cancel();
            System.out.println(TAG +  "\tonFailure: " + e);

            callback.onFailure(e.getMessage());
        }

        @Override
        public void onResponse( @NonNull Call call, @NonNull Response response) throws IOException {

            try {
                ApiResponseBody responseBody = createResponseBody(response);
                showResponseLog(responseBody);
                if (responseBody.successful()) {
                    callback.onResponse(reconvertToResponse(responseBody, endPoint));
                } else {
                    if (responseBody.getCode() == HttpCode.NOT_ALLOW) {
                        notAllowedUser();
                    } else {
                        callback.onRequestReject(responseBody.getStatus(), responseBody.getDescription());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new IOException("bad decryption call to Hapi!!!!!");
            }


        }




        private static void notAllowedUser() {
            // Todo logout user
            /*new TokenPreferences().clear();
            Intent i = AppController.getContext().getPackageManager()
                    .getLaunchIntentForPackage( AppController.getContext().getPackageName() );
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            AppController.getContext().startActivity(i);
            */
        }

        private static Object reconvertToResponse(ApiResponseBody responseBody, KoalaEndPoint endPoint) throws IOException {
            ObjectMapper mapper = new ObjectMapper();
            String result = mapper.writeValueAsString(responseBody.getData());
            return mapper.readValue(result , endPoint.getResponseType());
        }
    }

}