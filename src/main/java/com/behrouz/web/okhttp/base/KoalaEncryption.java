package com.behrouz.web.okhttp.base;

import java.util.logging.Logger;

/**
 * Created By Hapi KZM
 */
public class KoalaEncryption extends Encryption {

    protected static final Logger logger = Logger.getLogger( KoalaEncryption.class.getName());

    private String sharedKey    = "Hapi8J8888888";

    private String sharedVI     = "HapiKazemi888888";




    protected String getSharedKey() {
        return sharedKey;
    }

    protected String getSharedVI() {
        return sharedVI;
    }
}
