package com.modo.modoapiserver.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.Map;

@Service
public class KakaoOAuth2Service {

    private final String KAKAO_TOKEN_URL = "https://kauth.kakao.com/oauth/token";
    private final String KAKAO_USERINFO_URL = "https://kapi.kakao.com/v2/user/me";
    private final String KAKAO_CLIENT_ID = "b2227bde459023910498f777b84a09cb";
    private final String KAKAO_CLIENT_SECRET = "8q3XHQmlkd1VpBdb0Q8RQBW2LYlFkUT2";
    private final String KAKAO_REDIRECT_URI = "https://modo-team.com/oauth";

    public String getKakaoAccessToken(String code, String redirectUri, String codeVerifier){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", KAKAO_CLIENT_ID);
        params.add("client_secret", KAKAO_CLIENT_SECRET);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);
        params.add("code_verifier", codeVerifier);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

        ResponseEntity<Map> response = restTemplate.exchange(KAKAO_TOKEN_URL, HttpMethod.POST, kakaoTokenRequest, Map.class);

        return response.getBody().get("access_token").toString();
    }

    public Map<String, Object> getKakaoUserInfo(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers);

        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                KAKAO_USERINFO_URL, HttpMethod.POST, kakaoProfileRequest,
                new ParameterizedTypeReference<>() {}
        );

        return response.getBody();
    }
}