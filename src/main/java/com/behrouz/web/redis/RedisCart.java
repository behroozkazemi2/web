package com.behrouz.web.redis;

import com.fasterxml.jackson.databind.type.TypeFactory;
import com.behrouz.web.rest.request.WebCartRedis;
import org.springframework.stereotype.Component;
import com.behrouz.web.redis.core.RedisBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By Hapi KZM
 * Package ir.mobintabaran.xima.server.redis
 * Project ximaserver
 * 17 July 2018 09:21
 **/
@Component
public class RedisCart extends RedisBase {

    private static final String REDIS_CART_TOKEN =  "cart-token";

    //Per Second
    private static final long REDIS_CART_EXPIRE_TIME = 5 * 24 * 60 * 60;

    private static final String CART_CODE = "cart";



    public void saveCart(String cookieToken, List<WebCartRedis> cart) {

        String key = getKey( cookieToken );
        String cartString = toJson(cart);
        getSync().hset( key , CART_CODE, cartString );
        getSync().expire( key ,  getExpireTime());

    }



    public List<WebCartRedis> getCart(String cookieToken){
        if(getSync().hexists( getKey( cookieToken ) , CART_CODE)){
            String cartString = getSync().hget(getKey(cookieToken), CART_CODE);
            return fromJson(cartString , TypeFactory.defaultInstance().constructCollectionType(List.class , WebCartRedis.class));
        }else{
            return new ArrayList<>();
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
