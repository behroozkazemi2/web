package com.behrouz.web.component;

import com.behrouz.web.exception.KoalaException;
import com.behrouz.web.okhttp.OkHttpHelper;
import com.behrouz.web.okhttp.api.ApiResponseBody;
import com.behrouz.web.okhttp.model.request.CommentListRequest;
import com.behrouz.web.okhttp.model.request.IdLong;
import com.behrouz.web.okhttp.model.response.CommentResponse;
import com.behrouz.web.redis.RedisRegion;
import com.behrouz.web.rest.request.WebCartRedis;
import com.behrouz.web.rest.response.ProductDetailCommentDetailRestResponse;
import com.behrouz.web.security.session.model.SessionHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.behrouz.web.okhttp.model.response.CartItemResponse;
import com.behrouz.web.okhttp.model.response.ProductDetailResponse;
import com.behrouz.web.redis.RedisCart;
import com.behrouz.web.rest.request.CartAddProductRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

/**
 * Created By Hapi KZM
 * Edited by Hapi
 * Package ir.mobintabaran.xima.server.component
 * Project newxima
 * 30 January 2019 12:23 PM
 **/


@Component
public class WebCartComponent {


    @Autowired
    private RedisCart redisCart;

    @Autowired
    private RedisRegion redisRegion;

    //this will update on server( add & Edit & Delete)
    public List<WebCartRedis> updateCart(String cookieKey, int productProviderId, float count, String userDesc) throws KoalaException {
        if(SessionHolder.isAuthenticated()) {

             // THIS NEVER BE USED
            WebCartRedis addCart = new WebCartRedis(productProviderId, count, userDesc);
            ApiResponseBody addResponse = OkHttpHelper.addSingleCartToDatabase(new CartAddProductRequest(addCart));

            if (addResponse == null || !addResponse.successful()) {
                throw new KoalaException("خطا در برقراری ارتباط با مرکز. لطفا مجددا تلاش کنید.");
            }
        }

        return syncCart(cookieKey, productProviderId, count, userDesc);

    }

    //use for time that user login and cart save
    public List<WebCartRedis> addCartToRedis(String cookieKey, CartItemResponse cartItemResponse) throws KoalaException {
        return syncCart(cookieKey, cartItemResponse);
    }

    //this will update on server( add & Edit & Delete)
    public List<WebCartRedis> updateCart(String cookieKey, ProductDetailResponse product, float count, String userDesc, long addressId) throws KoalaException {
        if(SessionHolder.isAuthenticated()) {
            WebCartRedis addCart =
                    new WebCartRedis(product, count, userDesc);

            // TODO HEHEHEHEHEH
            ApiResponseBody addResponse =
                    OkHttpHelper.addSingleCartToDatabase(new CartAddProductRequest(addCart, addressId));

            if (addResponse == null || !addResponse.successful()) {
                throw new KoalaException(Objects.requireNonNull(addResponse).getDescription());
            }
        }
        return syncCart(cookieKey, (int) product.getId(), count, userDesc);

    }

    public List<WebCartRedis> updateCart(String cookieKey, int productId, float count) throws KoalaException {
        return updateCart( cookieKey, productId, count );
    }

    public List<WebCartRedis> updateCartByData(String cookieKey, ProductDetailResponse productProvider, float count, String userDesc, long addressId) throws KoalaException {
        // TOODHEHEHEHE
        return updateCart( cookieKey, productProvider, count , userDesc, addressId);
    }

    //this will update on server( add & Edit & Delete)
    public List<WebCartRedis> syncCart(String cookieKey, int productProviderId, float count, String userDesc) throws KoalaException {

        if(SessionHolder.isAuthenticated()) {
            ApiResponseBody<List<CartItemResponse>> cardResponse = OkHttpHelper.getCartFromDataBase();
            if (cardResponse == null || !cardResponse.successful()) {
                throw new KoalaException("خطا در نمایش سبد خرید لطفا مجددا تلاش کنید.");
            }

            List<WebCartRedis> product = cardResponse.getData().stream().map(WebCartRedis::new).collect(Collectors.toList());
            redisCart.saveCart(cookieKey, product);
            return product;
        }else{
            List<WebCartRedis> cart = redisCart.getCart(cookieKey);
            List<WebCartRedis> nCart = updateLocalCart(cart, productProviderId, count, userDesc);
            redisCart.saveCart(cookieKey, nCart);
            return nCart;
        }

    }

    //use for time that user login and cart save
    public List<WebCartRedis> syncCart(String cookieKey, CartItemResponse cartItemResponse) throws KoalaException {


            List<WebCartRedis> cart = redisCart.getCart(cookieKey);
            List<WebCartRedis> nCart = updateLocalCart(cart, cartItemResponse);
            redisCart.saveCart(cookieKey, nCart);

            return nCart;
    }

    //use for time that user login and cart save
    private List<WebCartRedis> updateLocalCart(List<WebCartRedis> cart, CartItemResponse cartItemResponse){


        List<WebCartRedis> nCart =
                cart == null ? new ArrayList<>() :
                cart.stream().filter( f -> f.getProductProviderId() != cartItemResponse.getId()).collect(Collectors.toList());

        if(cartItemResponse.getInCartCount() > 0) {
            WebCartRedis nItem = new WebCartRedis( cartItemResponse);
            nCart.add(nItem);
        }

        return nCart;

    }

    //this will update on server( add & Edit & Delete)
    private List<WebCartRedis> updateLocalCart(List<WebCartRedis> cart, int productId, float count, String userDesc) throws KoalaException {

        List<WebCartRedis> nCart =
                cart == null ? new ArrayList<>() :
                cart.stream().filter( f -> f.getProductProviderId() != productId).collect(Collectors.toList());

        ApiResponseBody<ProductDetailResponse> response = OkHttpHelper.productDetail(new IdLong(productId, -1));
        if(!response.successful()){
            throw new KoalaException("خطا در دریافت اضافه کردن کالا به سبد خرید.");
        }
        if(count > 0) {
            WebCartRedis nItem = new WebCartRedis( response.getData() , count ,userDesc);
            nCart.add(nItem);
        }

        return nCart;

    }


    public List<WebCartRedis> getCart(String cookie) {
        return redisCart.getCart(cookie);
    }

    public void clearCart(String cookie) {
        redisCart.saveCart(cookie , new ArrayList<>());
    }


    public ProductDetailCommentDetailRestResponse getProductCommentDetail(CommentListRequest resRequest){
        ApiResponseBody<List<CommentResponse>> result =
                OkHttpHelper.getCommentList(
                        resRequest
                );

        OptionalDouble avg =
                result.getData().stream().mapToDouble(
                        CommentResponse::getRate
                ).average();

        return new ProductDetailCommentDetailRestResponse(
                "",
                result.getTotal(),
                avg.isPresent() ? avg.getAsDouble() : 0 ,
                new ArrayList<>()
        );
    }
}
