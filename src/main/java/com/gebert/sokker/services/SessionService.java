package com.gebert.sokker.services;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class SessionService {

  private static final String SESSION_URL = "https://sokker.org/start.php?session=xml";

  HttpHeaders getSessionHeader(String iLogin, String iPassword) {
    String sessonCookies = getSokkerSessionCookies(iLogin, iPassword);

    HttpHeaders headers = new HttpHeaders();
    headers.add("Cookie", sessonCookies);
    return headers;
  }

  private String getSokkerSessionCookies(String iLogin, String iPassword) {
    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    map.add("ilogin", iLogin);
    map.add("ipassword", iPassword);

    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

    ResponseEntity<String> stringResponseEntity = restTemplate
        .postForEntity(SESSION_URL, request, String.class);
    List<String> set_cookie = stringResponseEntity.getHeaders().get(HttpHeaders.SET_COOKIE);

    return set_cookie.get(5);
  }


}

