package com.behrouz.web.okhttp;

import com.behrouz.web.okhttp.model.response.MoneyRequestResponse;
import com.behrouz.web.rest.request.*;
import com.behrouz.web.rest.response.InformationRestResponse;
import com.behrouz.web.rest.response.TicketDetailRestResponse;
import com.behrouz.web.rest.response.TicketMessageRestResponse;
import com.behrouz.web.rest.response.bank.BehtaSamanRedirectUrlRestResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.behrouz.web.okhttp.api.ApiRequestBody;
import com.behrouz.web.okhttp.api.ApiResponseBody;
import com.behrouz.web.okhttp.base.KoalaEndPoint;
import com.behrouz.web.okhttp.base.OkHttpRequest;
import com.behrouz.web.okhttp.model.IdName;
import com.behrouz.web.okhttp.model.request.*;
import com.behrouz.web.okhttp.model.response.*;
import com.behrouz.web.security.session.model.SessionHolder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created By Hapi KZM
 */

public class OkHttpHelper {


    // <editor-fold desc="Authentication">


    public static ApiResponseBody registerReq(RegisterRequest registerRequest) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), registerRequest);
        return postRequest(request, KoalaEndPoint.REGISTER);
    }

    public static ApiResponseBody loginReq(LoginRequest loginRequest) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), loginRequest);
        return postRequest(request, KoalaEndPoint.LOGIN);
    }

    public static ApiResponseBody<ApiVerifyResponse> verifyReq(VerifyRequest verifyRequest) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), verifyRequest);
        return postRequest(request, KoalaEndPoint.VERIFY);
    }

    public static ApiResponseBody<UserDetailResponse> userReq(String token) {
        ApiRequestBody request = new ApiRequestBody<>(token);
        return postRequest(request, KoalaEndPoint.USER_DETAIL);
    }

    public static ApiResponseBody resendReq(LoginRequest loginRequest) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), loginRequest);
        return postRequest(request, KoalaEndPoint.RESEND);
    }

    public static ApiResponseBody editUserRequest(UserDetailResponse user) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), user);
        return postRequest(request, KoalaEndPoint.EDIT_USER);
    }

    public static ApiResponseBody logoutReq() {
        ApiRequestBody request = new ApiRequestBody<>(getToken());
        return postRequest(request, KoalaEndPoint.LOGOUT);
    }

    public static ApiResponseBody<AddressRequestResponse> getAddressRequest() {
        ApiRequestBody request = new ApiRequestBody<>(getToken());
        return postRequest(request, KoalaEndPoint.GET_ADDRESS);
    }

    public static ApiResponseBody <IdRequest> addAddressRequest(AddressRequestResponse addressRequestResponse) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), addressRequestResponse);
        return postRequest(request, KoalaEndPoint.ADD_ADDRESS);
    }
    public static ApiResponseBody<AddressRequestResponse> getAddressDetail(IdRequest addressId) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), addressId);
        return postRequest(request, KoalaEndPoint.GET_ADDRESS_DETAIL);
    }
    public static ApiResponseBody<AddressRequestResponse> getSelectedAddressDetail(IdRequest addressId) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), addressId);
        return postRequest(request, KoalaEndPoint.GET_SELECTED_ADDRESS_DETAIL);
    }
    public static ApiResponseBody<Boolean> checkNewAddressIsInSameArea(IdLong addresses){
        ApiRequestBody request = new ApiRequestBody<>(getToken(), addresses);
        return postRequest(request, KoalaEndPoint.CHECK_CHANGED_ADDRESS_AREA_IS_SAME);
    }


    public static ApiResponseBody deleteAddressRequest(IdRequest idRequest) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), idRequest);
        return postRequest(request, KoalaEndPoint.DELETE_ADDRESS);
    }

//    public static void logoutRequest(LoginRequest request , OkHttpCallback<Void> callback) {
//        ApiRequestBody apiRequestBody = new ApiRequestBody<>( getToken() , request );
//        postRequest(apiRequestBody, KoalaEndPoint.LOGIN, callback);
//    }
//
//    public static void registerRequest(RegisterRequest registerRequest, OkHttpCallback callback) {
//
//        ApiRequestBody request = new ApiRequestBody<>( getToken(), registerRequest );
//        postRequest(request, KoalaEndPoint.REGISTER , callback);
//
//    }

    // </editor-fold >


    // <editor-fold desc="User">

    public static ApiResponseBody<MoneyRequestResponse> getUserBalance() {
        ApiRequestBody request = new ApiRequestBody<>(getToken());
        return postRequest(request, KoalaEndPoint.GET_USER_BALANCE);
    }




    //<editor-fold desc=" TICKET">

    public static ApiResponseBody<List<TicketDetailRestResponse>> getUserTicket(
            String search
    ) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), search);
        return postRequest(request, KoalaEndPoint.GET_USER_TICKET);
    }

    public static ApiResponseBody<TicketDetailRestResponse> getUserTicketDetail(
            String trackingCode
    ) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), trackingCode);
        return postRequest(request, KoalaEndPoint.GET_USER_TICKET_DETAIL);
    }


    public static ApiResponseBody<String> saveOrEditTicket(SaveTicketRestRequest rest) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), rest);
        return postRequest(request, KoalaEndPoint.ADD_USER_TICKET);
    }


    public static ApiResponseBody<Void> closeTicket(IdRequest ticketId) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), ticketId);
        return postRequest(request, KoalaEndPoint.CLOSE_USER_TICKET);
    }


    public static ApiResponseBody<List<TicketMessageRestResponse>> getTicketMessages(TicketMessageRequest requestDetail) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), requestDetail);
        return postRequest(request, KoalaEndPoint.GET_USER_TICKET_MESSAGES);
    }


    public static ApiResponseBody<Void> addNewTicketMessage(SaveTicketMessageRestRequest restRequest) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), restRequest);
        return postRequest(request, KoalaEndPoint.ADD_USER_TICKET_MESSAGE);
    }

    //</editor-fold>


    // </editor-fold >


    // <editor-fold desc="Special Product">


    // </editor-fold >


    // <editor-fold desc="Basic">

    public static ApiResponseBody<List<AllCategoriesResponse>> categoriesList(IdRequest catId) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), catId);
        return postRequest(request, KoalaEndPoint.CATEGORIES_LIST);
    }

    public static ApiResponseBody<List<RequestDetailResponse>> tagsList(RequestDetailResponse requestResponse) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), requestResponse);
        return postRequest(request, KoalaEndPoint.TAGS_LIST);
    }

    public static ApiResponseBody<List<RequestDetailResponse>> tagsList(List<Long> categories) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), categories);
        return postRequest(request, KoalaEndPoint.TAGS_ALL_LIST);
    }

    public static ApiResponseBody<List<ProviderResponse>> providerList(ProductProviderCategoryRequest productProviderCategoryRequest) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), productProviderCategoryRequest);
        return postRequest(request, KoalaEndPoint.PROVIDER_LIST);
    }

    public static ApiResponseBody<ProviderResponse> providerDetail(Integer providerId) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), new IdRequest(providerId));
        return postRequest(request, KoalaEndPoint.PROVIDER_DETAIL);
    }

    public static ApiResponseBody<List<ProviderResponse>> popularProviders() {
        ApiRequestBody request = new ApiRequestBody<>(getToken());
        return postRequest(request, KoalaEndPoint.POPULAR_PROVIDERS);
    }

    public static ApiResponseBody<List<ProductResponse>> productList(ProductProviderCategoryRequest productSearchRequest) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), productSearchRequest);
        return postRequest(request, KoalaEndPoint.PRODUCT_LIST);
    }

    public static ApiResponseBody<List<InformationRestResponse>> productInformationCategory(IdRequest idRequest) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), idRequest);
        return postRequest(request, KoalaEndPoint.PRODUCT_INFORMATION_CATEGORY);
    }
    public static ApiResponseBody<PromotePromoteProductResponse> specialPromoteProductList(IdRequest addressId) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), addressId);
        return postRequest(request, KoalaEndPoint.SPECIAL_PROMOTE_PRODUCT_LIST);
    }

    public static ApiResponseBody<PromotePromoteProductResponse> lastPromoteProductList(IdRequest addressId) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), addressId);
        return postRequest(request, KoalaEndPoint.LAST_PROMOTE_PRODUCT_LIST);
    }

    public static ApiResponseBody<ProductDetailResponse> productDetail(IdLong idLong) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), idLong);
        return postRequest(request, KoalaEndPoint.PRODUCT_DETAIL);
    }

    public static ApiResponseBody<Boolean> checkIsAnyProductInArea(IdLong idLong) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), idLong);
        return postRequest(request, KoalaEndPoint.CHECK_PRODUCT_LIST_COUNT_IN_AREA);
    }


    public static ApiResponseBody<Void> productDelete(IdRequest idRequest) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), idRequest);
        return postRequest(request, KoalaEndPoint.CARD_DELETE);
    }


    public static ApiResponseBody<List<RegionResponse>> region() {
        ApiRequestBody request = new ApiRequestBody<>(getToken());
        return postRequest(request, KoalaEndPoint.REGION);
    }


    public static ApiResponseBody<HashMap<Integer, List<IdName>>> groupCategory() {
        ApiRequestBody request = new ApiRequestBody<>(getToken());
        return postRequest(request, KoalaEndPoint.GROUP_CATEGORY);
    }

    public static ApiResponseBody<HashMap<Integer, HashMap<Integer, List<IdName>>>> groupProvider() {
        ApiRequestBody request = new ApiRequestBody<>(getToken());
        return postRequest(request, KoalaEndPoint.GROUP_PROVIDER);
    }

    public static ApiResponseBody<List<ProductResponse>> productDetailsFromListOfIds(List<WebCartRedis> webCartRequests) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), webCartRequests);
        return postRequest(request, KoalaEndPoint.PRODUCTS_DETAIL);
    }

    public static ApiResponseBody addCartToDatabase(List<CartAddProductRequest> cartAddProductRequests) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), cartAddProductRequests);
        return postRequest(request, KoalaEndPoint.ADD_CART_TO_DATABASE);
    }
    public static ApiResponseBody checkProductCount(IdLong productIdCount) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), productIdCount);
        return postRequest(request, KoalaEndPoint.CHECK_PRODUCT_LIST_COUNT);
    }

    public static ApiResponseBody addSingleCartToDatabase(CartAddProductRequest cartAddProductRequests) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), cartAddProductRequests);
        return postRequest(request, KoalaEndPoint.ADD_SINGLE_CART_TO_DATABASE);
    }

    public static ApiResponseBody<List<CartItemResponse>> getCartFromDataBase() {
        ApiRequestBody request = new ApiRequestBody<>(getToken());
        return postRequest(request, KoalaEndPoint.GET_CART_FROM_DATABASE);
    }

    public static ApiResponseBody clearCart() {
        ApiRequestBody request = new ApiRequestBody<>(getToken());
        return postRequest(request, KoalaEndPoint.CLEAR_DATABASE_CART);
    }

    public static ApiResponseBody<Float> discountCheck(RequestDetailResponse discountCode) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), discountCode);
        return postRequest(request, KoalaEndPoint.DISCOUNT_CHECK);
    }

    public static ApiResponseBody<List<OffCodeResponse>> discountList(ListRequest listRequest) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), listRequest);
        return postRequest(request, KoalaEndPoint.DISCOUNT_LIST);
    }

    public static ApiResponseBody getCandiDateTime() {
        ApiRequestBody request = new ApiRequestBody<>(getToken());
        return postRequest(request, KoalaEndPoint.CANDIDATE_TIME);
    }

    public static ApiResponseBody createFactor(FactorRequest requestDetailResponse) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), requestDetailResponse);
        return postRequest(request, KoalaEndPoint.NEW_FACTOR);
    }

    public static ApiResponseBody<FactorPaymentResponse> payFactor(FactorRequest requestDetailResponse) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), requestDetailResponse);
        return postRequest(request, KoalaEndPoint.PAY_AMOUNT);
    }

    public static ApiResponseBody<String> verifyFactor() {
        ApiRequestBody request = new ApiRequestBody<>(getToken());
        return postRequest(request, KoalaEndPoint.VERIFY_FACTOR);
    }

    public static ApiResponseBody<String> charcgeBalance(MoneyRequestResponse req) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), req);
        return postRequest(request, KoalaEndPoint.CHARGE);
    }

    public static ApiResponseBody<List<FactorResponse>> getFactorHistory(FactorListRequest listRequest) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), listRequest);
        return postRequest(request, KoalaEndPoint.FACTOR_HISTORY);
    }

    public static ApiResponseBody<FactorDetailResponse> getFactorDetail(int billId) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), billId);
        return postRequest(request, KoalaEndPoint.FACTOR_DETAIL);
    }

    public static ApiResponseBody<List<ProviderResponse>> getFactorProviderList(int billId) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), billId);
        return postRequest(request, KoalaEndPoint.FACTOR_PROVIDERS);
    }

    public static ApiResponseBody addComment(CommentRequest commentRequest) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), commentRequest);
        return postRequest(request, KoalaEndPoint.COMMENT_ADD);
    }


    public static ApiResponseBody<List<CommentResponse>> getCommentList(CommentListRequest commentListRequest) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), commentListRequest);
        return postRequest(request, KoalaEndPoint.COMMENT_LIST);
    }

    public static ApiResponseBody<ImageRequest> getImage(ImageTypeRequest idRequest) {
        ApiRequestBody request = new ApiRequestBody<>(getToken(), idRequest);
        return postRequest(request, KoalaEndPoint.IMAGE_GET);
    }

    // </editor-fold >


    public static ApiResponseBody<List<BannerRestRequest>> bannerGetDetail() {
        ApiRequestBody request = new ApiRequestBody<>( getToken());
        return postRequest( request , KoalaEndPoint.BANNER_DETAIL);
    }

    public static ApiResponseBody<String> customerPaymentConfirm(BehtaSamanRedirectUrlRestResponse bankResponse ) {
        ApiRequestBody request = new ApiRequestBody<>( getToken(), bankResponse);
        return postRequest( request , KoalaEndPoint.CUSTOMER_PAYMENT_CONFIRM);
    }


    private static String getToken() {
        return SessionHolder.getToken();
    }

    private static ApiResponseBody postRequest(
            final ApiRequestBody requestBody,
            final KoalaEndPoint endPoint) {

        requestBody.setAction(endPoint.getApiAction());

        ApiResponseBody apiResponse = OkHttpRequest.postRequest(requestBody);

        if (apiResponse != null && apiResponse.successful() && apiResponse.getData() != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                apiResponse.setData(mapper.readValue(mapper.writeValueAsString(apiResponse.getData()), endPoint.getResponseType()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return apiResponse;
    }


}