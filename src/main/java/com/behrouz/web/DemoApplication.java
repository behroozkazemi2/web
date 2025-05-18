package com.behrouz.web;

import com.behrouz.web.values.Links;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

@SpringBootApplication
public class DemoApplication {


    public static boolean DEBUG_MODE;

    public static int LOG_LENGTH_BYTE;

    public static boolean DELAY_RESPONSE;

    public static String APK_LINK;

    public static void main(String[] args) {

        TimeZone.setDefault( TimeZone.getTimeZone( "Asia/Tehran" ) );
        SpringApplication.run( DemoApplication.class, args );

        showStartServer();

    }

    private static void showStartServer() {

        System.out.println(
                "\n\n\t\t\t\t\t\t" + "Debug: " + DEBUG_MODE +
                        "\n\n\t\t\t\t\t\t" + "Log Length: " + LOG_LENGTH_BYTE +
                        "\n\n\t\t\t\t\t\t" + "Auth Link: " + Links.USER_AND_FINANCIAL +
                        "\n\n\n"
        );
        System.out.println();
    }
    public int[] singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap();
        int [] arr = new int[2];

        for(int i : nums){
            if(map.containsKey(i))
                map.put(i , 1);
            else
                map.put(i , 0);
        }

        int count =0 ;
        for(Map.Entry<Integer, Integer> mm : map.entrySet() ){
            if(mm.getValue() == 0){
                arr[count++] = mm.getKey();
            }
        }

        return arr;
    }


    @Value( "${api.values.debug-mode}" )
    public void setDebugMode(Boolean debugMode) {
        DEBUG_MODE = debugMode;
    }

    @Value( "${api.values.log-byte-length}" )
    public void setLogByteLength(int longByteLength) {
        LOG_LENGTH_BYTE = longByteLength;
    }

    @Value( "${api.values.delay-response}" )
    public void setDelayResponse(Boolean delayResponse) {
        DELAY_RESPONSE = delayResponse;
    }

    @Value( "${api.server.auth}" )
    public void setUserAccountLink(String value) {
        Links.USER_AND_FINANCIAL = value;
    }


    @Value( "${api.server.imageURl}" )
    public void setImageURl(String value) {
        Links.IMAGE_URL = value;
    }


    @Value( "${apk.link}" )
    public void setApkLink(String value) {
        Links.APK_LINK = value;
    }


    @Value( "${apk.cafebazaar-link}" )
    public void setApkCafebazaar(String value) {
        Links.APK_CAFFE_BAZZAR = value;
    }


    @Value( "${apk.myket-link}" )
    public void setApkMyket(String value) {
        Links.APK_MYKET = value;
    }


}
