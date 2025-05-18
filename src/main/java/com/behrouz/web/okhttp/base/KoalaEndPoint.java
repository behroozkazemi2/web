package com.behrouz.web.okhttp.base;
import com.behrouz.web.rest.response.InformationRestResponse;
import com.behrouz.web.rest.response.TicketDetailRestResponse;
import com.behrouz.web.rest.response.TicketMessageRestResponse;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.behrouz.web.okhttp.model.IdName;
import com.behrouz.web.okhttp.model.request.*;
import com.behrouz.web.okhttp.model.response.*;

import java.util.HashMap;
import java.util.List;

/**
 * Created By Hapi KZM
 */

public enum KoalaEndPoint {

    //<editor-fold desc="Authentication">

    REGISTER( "app.customer.auth.register" ) ,

    LOGIN( "app.customer.auth.login" ) ,

    VERIFY( "app.customer.auth.verify" , ApiVerifyResponse.class) ,

    USER_DETAIL( "app.customer.profile.get" , UserDetailResponse.class) ,

    RESEND( "app.customer.auth.resend" ) ,

    EDIT_USER( "app.customer.profile.edit" ) ,

    LOGOUT( "app.customer.auth.logout" ) ,

    GET_ADDRESS( "app.customer.address.list" , TypeFactory.defaultInstance().constructCollectionLikeType(List.class, AddressRequestResponse.class)) ,
    GET_ADDRESS_DETAIL( "app.customer.address.detail" ,  AddressRequestResponse.class) ,
    GET_SELECTED_ADDRESS_DETAIL( "app.customer.selected.address.detail" ,  AddressRequestResponse.class) ,
    CHECK_CHANGED_ADDRESS_AREA_IS_SAME( "app.customer.check.changed.address_area_is_same" ,  Boolean.class) ,

    ADD_ADDRESS( "app.customer.address.add", IdRequest.class ) ,

    DELETE_ADDRESS( "app.customer.address.delete" ) ,

    IMAGE_UPLOAD( "app.image.upload",  IdName.class) ,
    IMAGE_UPLOADS( "app.provider.image.uploads",  TypeFactory.defaultInstance().constructCollectionLikeType(List.class, IdName.class)),

    // </editor-fold >
    NEW_CUSTOM_ORDER( "app.customer.custom.new" , RequestDetailResponse.class) ,
    EDIT_CUSTOM_ORDER( "app.customer.custom.edit" , RequestDetailResponse.class) ,
    GET_CUSTOM_ORDER( "app.customer.custom.get" , CustomProductResponse.class) ,
    STATUS_CUSTOM_ORDER( "app.customer.customized.status.change") ,

    //<editor-fold desc="customOrder">

    // </editor-fold >

    //<editor-fold desc="Comment">
    COMMENT_LIST( "app.customer.comment.list" ,TypeFactory.defaultInstance().constructCollectionLikeType(List.class, CommentResponse.class)),
    COMMENT_ADD( "app.customer.comment.add" ) ,

    // </editor-fold >

    //<editor-fold desc="Product & Provider Detail">

    CATEGORIES_LIST( "app.customer.constant.category.all"  , TypeFactory.defaultInstance().constructCollectionLikeType(List.class , AllCategoriesResponse.class ) ) ,

    TAGS_LIST( "app.customer.constant.tag.all"  , TypeFactory.defaultInstance().constructCollectionLikeType(List.class , RequestDetailResponse.class ) ) ,
    TAGS_ALL_LIST( "app.customer.constant.tags.all"  , TypeFactory.defaultInstance().constructCollectionLikeType(List.class , RequestDetailResponse.class ) ) ,

    PROVIDER_LIST( "app.customer.provider.list" , TypeFactory.defaultInstance().constructCollectionLikeType(List.class , ProviderResponse.class)       ) ,

    PROVIDER_DETAIL( "web.provider.detail" , ProviderResponse.class ) ,

    POPULAR_PROVIDERS( "web.provider.popular"     , TypeFactory.defaultInstance().constructCollectionLikeType(List.class , ProviderResponse.class)       ) ,

    PRODUCT_LIST( "app.customer.product.list" , TypeFactory.defaultInstance().constructCollectionLikeType(List.class , ProductResponse.class)       ) ,
    PRODUCT_INFORMATION_CATEGORY( "app.customer.product.information.category" , TypeFactory.defaultInstance().constructCollectionLikeType(List.class , InformationRestResponse.class)) ,
    SPECIAL_PROMOTE_PRODUCT_LIST( "web.special.promote.product.list"     , PromotePromoteProductResponse.class ) ,
    LAST_PROMOTE_PRODUCT_LIST( "web.last.promote.product.list"     , PromotePromoteProductResponse.class ) ,

    PRODUCT_DETAIL( "web.product.detail"     , ProductDetailResponse.class ) ,


    CHECK_PRODUCT_LIST_COUNT_IN_AREA( "web.product.checkIsAnyProductInArea"     , Boolean.class ) ,

    PRODUCTS_DETAIL( "web.product.detail"     , TypeFactory.defaultInstance().constructCollectionLikeType(List.class , ProductResponse.class ) ) ,

    ADD_CART_TO_DATABASE( "web.cart.add.all" ) ,

    CHECK_PRODUCT_LIST_COUNT( "app.customer.cart.product.check.count", Void.class) ,

    ADD_SINGLE_CART_TO_DATABASE( "app.customer.cart.add" ) ,

    GET_CART_FROM_DATABASE( "app.customer.cart.list" , TypeFactory.defaultInstance().constructCollectionLikeType(List.class , CartItemResponse.class) ) ,

    CLEAR_DATABASE_CART( "app.customer.cart.delete.all" ),

    CARD_DELETE( "app.customer.cart.delete" ),

    DISCOUNT_CHECK( "app.customer.off.check" , Float.class ),

    DISCOUNT_LIST( "app.customer.off.list" , TypeFactory.defaultInstance().constructCollectionLikeType(List.class, OffCodeResponse.class)),

    NEW_FACTOR( "app.customer.factor.new", PaymentAmount.class ) ,

    CANDIDATE_TIME( "app.customer.factor.get.candidate", CandidateDateTimeResponse.class ) ,

    PAY_AMOUNT( "app.customer.factor.amount" , FactorPaymentResponse.class) ,

    VERIFY_FACTOR( "app.customer.balance.pay" , String.class) ,

    CHARGE( "app.customer.balance.charge" , String.class) ,

    FACTOR_HISTORY( "app.customer.factor.list" , TypeFactory.defaultInstance().constructCollectionLikeType(List.class, FactorResponse.class) ) ,

    FACTOR_DETAIL( "app.customer.factor.detail" , FactorDetailResponse.class ),

    FACTOR_PROVIDERS( "app.customer.factor.provider.list" ,  TypeFactory.defaultInstance().constructCollectionLikeType(List.class , ProviderResponse.class)     ),

    GET_USER_BALANCE( "app.customer.balance.check"  ,  MoneyRequestResponse.class  ),

    REGION  ( "app.universal.constant.region",  TypeFactory.defaultInstance().constructCollectionType(List.class, RegionResponse.class)) ,

    GROUP_CATEGORY  ( "app.universal.constant.gCategory",  TypeFactory.defaultInstance().constructMapLikeType(HashMap.class, TypeFactory.defaultInstance().constructType(Integer.class), TypeFactory.defaultInstance().constructCollectionLikeType(List.class, IdName.class))) ,

    IMAGE_GET  ( "app.provider.image.get",  ImageRequest.class),

    GROUP_PROVIDER  ( "app.universal.constant.gProvider",  TypeFactory.defaultInstance().constructMapLikeType(HashMap.class, TypeFactory.defaultInstance().constructType(Integer.class),TypeFactory.defaultInstance().constructMapLikeType(HashMap.class, TypeFactory.defaultInstance().constructType(Integer.class), TypeFactory.defaultInstance().constructCollectionLikeType(List.class, IdName.class)))) ,


    SPECIAL_PRODUCT_LIST( "app.customer.special.list"  , TypeFactory.defaultInstance().constructCollectionType(List.class, SpecialProductDigestResponse.class)),
    SPECIAL_PRODUCT_DELETE( "app.customer.special.delete"  ),
    SPECIAL_PRODUCT_SUGGEST_LIST( "app.customer.special.detail"  ,  SpecialProductDetailResponse.class  ),
    SPECIAL_PRODUCT_SUGGEST_ASSIGN( "app.customer.special.confirm"    ),
    SPECIAL_PRODUCT_ADD( "app.customer.special.add"    ),

    GET_USER_TICKET( "app.customer.ticket.list" ,  TypeFactory.defaultInstance().constructCollectionType(List.class, TicketDetailRestResponse.class)),
    GET_USER_TICKET_DETAIL( "app.customer.ticket.detail" ,  TicketDetailRestResponse.class),
    GET_USER_TICKET_MESSAGES( "app.customer.ticket.messages" ,  TypeFactory.defaultInstance().constructCollectionType(List.class, TicketMessageRestResponse.class) ),
    ADD_USER_TICKET( "app.customer.ticket.add" , String.class),
    ADD_USER_TICKET_MESSAGE( "app.customer.ticket.add.message" , Void.class),
    CLOSE_USER_TICKET( "app.customer.ticket.close" , Void.class),


    //</editor-fold>
    BANNER_DETAIL("customer.banner.detail" , TypeFactory.defaultInstance().constructCollectionType(List.class, BannerRestRequest.class)),

    CUSTOMER_PAYMENT_CONFIRM( "app.customer.payment.confirm" , String.class);



    private final String apiAction;


    private final JavaType responseType;


    KoalaEndPoint(String API_ACTION, JavaType RESPONSE_CLASS) {
        this.apiAction = API_ACTION;
        this.responseType = RESPONSE_CLASS;
    }

    KoalaEndPoint(String API_ACTION) {
        this(API_ACTION , TypeFactory.defaultInstance().constructType(Void.class));
    }


    KoalaEndPoint(String API_ACTION, Class RESPONSE_CLASS) {
        this(API_ACTION , TypeFactory.defaultInstance().constructType(RESPONSE_CLASS));
    }




    public String getApiAction() {
        return apiAction;
    }


    public JavaType getResponseType() {
        return responseType;
    }
}
