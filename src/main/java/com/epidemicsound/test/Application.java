package com.epidemicsound.test;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

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
            HttpHeaders headers = createHeaders();

            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("grant_type", "client_credentials");

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

            ResponseEntity<AccessToken> res = restTemplate.postForEntity(API_URL + "/token", request, AccessToken.class);
            System.out.println(res.getBody());
        };
    }

    HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        byte[] encodedAuth = Base64.encodeBase64((CLIENT_ID + ":" + CLIENT_SECRET).getBytes());
        headers.add("Authorization", "Basic " + new String(encodedAuth));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return headers;
    }
}
