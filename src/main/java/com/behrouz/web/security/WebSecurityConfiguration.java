package com.behrouz.web.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import com.behrouz.web.security.captcha.KoalaAuthenticationDetailsSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created By Hapi KZM
 * Package ir.mobintabaran.xima.server.security
 * Project Koala Server
 * 09 September 2018 13:29
 **/
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter
        implements AuthenticationSuccessHandler {


    @Autowired
    private AuthenticationUser authUser;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authUser);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(
//                        "/**",
                        "/policy",
//                        "/cart",
                        "/banner",
                        "/contact",
                        "/getVerify/**",
                        "/getLogIn/**",
                        "/getSignUp/**",
                        "/thumbnail/**",
                        "/api/auth/**",
                        "/info/**",
                        "/api/**",
                        "/api/user/**",
                        "/productCategory/**",
                        "/about",
                        "/assets/**",
                        "/assets2/**",
                        "/login/**",
                        "/sign-up/**",
                        "/avatar/**",
                        "/customer/account/captcha",
                        "/customer/account/login",
                        "/customer/account/rememberPass",
                        "/customer/account/changePass",
                        "/",
                        "/captcha",
                        "/register",
                        "/allproducts",
                        "/product",
                        "/product/**",
                        "/allproviders/**",
                        "/provider/**",
                        "/category/**",
//                        "/special/*product*",
                        "/factor",
                        "/factor",
                        "/thumb/web/get/**",
//                        "/checkout/order/cart/**",
                        "/customerLogin",
                        "/app/**",
                        "/site/**",
                        "/login",
                        "/login/**",
                        "/prelogin",
                        "/verifylogin",
                        "/test/**",
                        "/files/**",
                        "/resendCode",
                        "/preregister",
                        "/p/**",
                        "/upload/**",
                        "/shop/**",
                        "/pay/**",
                        "/payhistory",
                        "/robots.txt",
                        "/sitemap.xml"
                ).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .authenticationDetailsSource(new KoalaAuthenticationDetailsSource())
                .loginPage("/login" )
                .permitAll()
                .and()
                .rememberMe().alwaysRemember(true).key("XimaUniqueAndSecret")

                .and()
                .csrf().disable();

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

    }



    @Override
    public void onAuthenticationSuccess( HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {


    }


}
