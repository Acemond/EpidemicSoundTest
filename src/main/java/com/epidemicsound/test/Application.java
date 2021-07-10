package com.epidemicsound.test;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Application {
    public static final String API_URL = "https://accounts.spotify.com/api";
    public static final String CLIENT_ID = "1006b92265b6408683db8854959d4e42";
    public static final String CLIENT_SECRET = "582cadd8604547e1ad9ad7ccf955259c";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) {
        return args -> {
            Map<String, String> parameters = new HashMap<>();
            parameters.put("client_id", CLIENT_ID);
            parameters.put("client_secret", CLIENT_SECRET);

            String body = "grant_type=client_credentials";

            AccessToken token = restTemplate.postForObject(API_URL + "/token", body, AccessToken.class, parameters);
            System.out.println(token);
        };
    }
}
