package com.epidemicsound.test.spotify.access;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class AccessTokenRestClient {
    private final Config config = ConfigFactory.load();
    private final String API_URL = config.getString("spotify.rest.accounts.url");
    private final String CLIENT_ID = config.getString("spotify.rest.accounts.id");
    private final String CLIENT_SECRET = config.getString("spotify.rest.accounts.secret");

    private final RestTemplate restTemplate;

    @Autowired
    public AccessTokenRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AccessToken authenticate() {
        HttpHeaders headers = createHeaders();

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");

        return restTemplate.postForObject(API_URL + "/token", new HttpEntity<>(body, headers), AccessToken.class);
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        byte[] encodedAuth = Base64.encodeBase64((CLIENT_ID + ":" + CLIENT_SECRET).getBytes());
        headers.add("Authorization", "Basic " + new String(encodedAuth));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return headers;
    }
}
