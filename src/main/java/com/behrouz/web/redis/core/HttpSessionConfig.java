package com.behrouz.web.redis.core;


import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


/**
 * Created By Hapi KZM
 **/

@Configuration
@EnableRedisHttpSession(redisNamespace = "behta-web")
public class HttpSessionConfig {

    @Bean
    public LettuceConnectionFactory connectionFactory() {
        return new LettuceConnectionFactory();
    }

    @Bean
    public StatefulRedisConnection<String, String> redisConnection() {
        return  RedisClient.create("redis://localhost").connect();
    }
//
//    @Bean
//    public Retrofit retrofit() {
//        return new Retrofit.Builder()
//                .baseUrl("http://login.niazpardaz.ir/api/v1/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//    }
//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//        return builder.build();
//    }
}
