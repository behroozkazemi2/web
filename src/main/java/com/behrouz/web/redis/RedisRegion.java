package com.behrouz.web.redis;

import com.behrouz.web.redis.core.RedisBase;
import org.springframework.stereotype.Component;

/**
 * Created By Hapi KZM
 * Package ir.mobintabaran.xima.server.redis
 * Project ximaserver
 * 17 July 2018 09:21
 **/
@Component
public class RedisRegion extends RedisBase {

    private static final String REDIS_CART_TOKEN =  "reg-region";

    //Per Second
    private static final long REDIS_CART_EXPIRE_TIME = 5 * 24 * 60 * 60;

    private static final String ADDRESS_KEY = "key_address";



    public void saveAddress(String cookieToken, long addressId) {

        String key = getKey( cookieToken );
        getSync().hset( key , ADDRESS_KEY, addressId + "" );
        getSync().expire( key ,  getExpireTime());

    }



    public long getAddress(String cookieToken){
        if(getSync().hexists( getKey( cookieToken ) , ADDRESS_KEY)){
            String regString = getSync().hget(getKey(cookieToken), ADDRESS_KEY);
            return Long.parseLong(regString);
        }else{
            return 0;
        }
    }


    private String getKey(String cookieKey){
        return String.format( "xm-%s:%s", getPrefixKey() , cookieKey );
    }



    @Override
    protected String getPrefixKey() {
        return REDIS_CART_TOKEN;
    }

    @Override
    protected long getExpireTime() {
        return REDIS_CART_EXPIRE_TIME;
    }




}
