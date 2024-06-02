package com.modo.modoapiserver.service;

import java.util.Map;
import java.util.Objects;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.modo.modoapiserver.dto.controller.auth.KakaoOAuth;

@Service
public class KakaoOAuth2Service {

	private final String KAKAO_TOKEN_URL = "https://kauth.kakao.com/oauth/token";
	private final String KAKAO_USERINFO_URL = "https://kapi.kakao.com/v2/user/me";

	public String getKakaoAccessToken(KakaoOAuth kakaoOAuth) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");


		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(kakaoOAuth.toParams(), headers);

		ResponseEntity<Map<String, Object>> response = restTemplate.exchange(KAKAO_TOKEN_URL, HttpMethod.POST,
			kakaoTokenRequest, new ParameterizedTypeReference<>() {});

		return Objects.requireNonNull(response.getBody()).get("access_token").toString();
	}

	public Map<String, Object> getKakaoUserInfo(String accessToken) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + accessToken);
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers);

		ResponseEntity<Map> response = restTemplate.exchange(KAKAO_USERINFO_URL, HttpMethod.POST, kakaoProfileRequest,
			Map.class);

		return response.getBody();
	}
}