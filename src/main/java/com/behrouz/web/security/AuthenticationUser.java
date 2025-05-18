package com.behrouz.web.security;

import com.behrouz.web.okhttp.OkHttpHelper;
import com.behrouz.web.okhttp.api.ApiResponseBody;
import com.behrouz.web.okhttp.model.request.VerifyRequest;
import com.behrouz.web.security.captcha.KoalaAuthenticationDetails;
import com.behrouz.web.util.CookieUtils;
import com.behrouz.web.util.StringUtil;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created By Hapi KZM
 * Package ir.mobintabaran.xima.server.security
 * Project Koala Server
 * 09 September 2018 13:32
 **/
@Component
public class AuthenticationUser implements AuthenticationProvider {


//    @Autowired
//    private PasswordRepository passwordRepository;
//
//
//    @Autowired
//    private PasswordComponent passwordComponent;
//
//    @Autowired
//    private OperatorRepository operatorRepository;
//
//    @Autowired
//    private OperatorLoginHistoryRepository operatorLoginHistory;


    @Override
    public Authentication authenticate( Authentication authentication)
            throws AuthenticationException {


        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();


        KoalaAuthenticationDetails userDetails =
                (KoalaAuthenticationDetails) authentication.getDetails();

        String mobile =
                userDetails.getMobile();

        if( StringUtil.isNullOrEmpty(mobile) ) {
            throw new BadCredentialsException("موبایل وارد نشده است");
        }

        VerifyRequest verifyRequest = new VerifyRequest(userDetails.getMobile(), userDetails.getverifyCode(), CookieUtils.getCookieToken(request), null, false);
        ApiResponseBody verify = OkHttpHelper.verifyReq(verifyRequest);

        if(!verify.successful()) {
//            throw new KoalaErrorResponse("verifyCode" , verify.getDescription());
            throw new BadCredentialsException("کد وارد شده صحیح نیست");
        }

       // return loginAuthentication(mobile);

        return null;
    }

//    private Authentication loginAuthentication(String mobile) {
//
//        ApiResponseBody <IdRequest> authLoginResponse =
//                Auth.getInstanceAuthPanel().getAccountingNumber( mobile );
//
//        if(!authLoginResponse.successful()){
//            throw new BadCredentialsException("نام کاربری یا رمز عبور اشتباه است");
//        }
//
//
////        PasswordEntity passwordEntity =
////                passwordRepository.findFirstByOperator_AccountingIdOrderByIdDesc( authLoginResponse.getData().getId());
////
////
////        if(passwordEntity == null || !passwordComponent.passwordMatched( password , passwordEntity.getHash() ) ){
////            throw new BadCredentialsException("نام کاربری یا رمز عبور اشتباه می باشد");
////        }
//
//
//        UsernamePasswordAuthenticationToken token =
//                new UsernamePasswordAuthenticationToken(mobile, "", new ArrayList<>());
//
//
//        OperatorSessionDetail sessionDetail =
//                createSessionDetail(passwordEntity);
//
//        token.setDetails( sessionDetail );
//
//
//        //saveLoginLog(passwordEntity , sessionDetail);
//
//        return token;
//
//    }



//
//
//    private Authentication loginAuthentication(HttpServletRequest request , String mobile ) {
//
//        LoginRequest loginRequest = new LoginRequest( mobile , CookieUtils.getCookieToken(H));
//
//        ApiResponseBody login = OkHttpHelper.loginReq();
//
//        if( operator == null ){
//            throw new BadCredentialsException("نام کاربری صحیح نمی باشد");
//        }
//
//
//        PasswordEntity passwordEntity =
//                passwordRepository.findFirstByOperator_IdOrderByIdDesc( operator.getId() );
//
//
//        if(passwordEntity == null || !passwordComponent.passwordMatched( password , passwordEntity.getHashPassword() ) ){
//            throw new BadCredentialsException("رمز عبور صحیح نمی باشد");
//        }
//
//
//
//        UsernamePasswordAuthenticationToken token =
//                new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
//
//
//        OperatorSessionDetail sessionDetail =
//                createSessionDetail(passwordEntity);
//
//        token.setDetails( sessionDetail );
//
//
//        saveLoginLog(passwordEntity , sessionDetail);
//
//
//        return token;
//
//
//    }

//    private void saveLoginLog( PasswordEntity passwordEntity, OperatorSessionDetail sessionDetail ) {
//
//        operatorLoginHistory.save(
//                new OperatorLoginHistoryEntity(
//                        passwordEntity,
//                        sessionDetail
//                )
//        );
//
//    }
//
//
//    private OperatorSessionDetail createSessionDetail(PasswordEntity passwordEntity) {
//
//        if ( passwordEntity.getOperator() == null ){
//            throw new BadCredentialsException("اطلاعات اپراتور کامل نیست!!");
//        }
//
//
//        OperatorSessionDetail sessionDetail =
//                new OperatorSessionDetail();
//
//        sessionDetail.setUser( new UserSessionDetail(
//                passwordEntity.getOperator().getId(),
//                passwordEntity.getOperator().getFirstName(),
//                passwordEntity.getOperator().getLastName(),
//                passwordEntity.getOperator().getAvatar(),
//                StrategyGenerator.generateOperatorToken(),
//                new Date()
//        ) );
//
//        sessionDetail.setFirstLogin( false );
//
//
//        return sessionDetail;
//    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
                UsernamePasswordAuthenticationToken.class);
    }

}
