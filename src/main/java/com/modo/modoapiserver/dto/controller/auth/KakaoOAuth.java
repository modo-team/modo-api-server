package com.modo.modoapiserver.dto.controller.auth;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import lombok.Builder;

@Builder
public class KakaoOAuth {

	private static final String KAKAO_CLIENT_ID = "b2227bde459023910498f777b84a09cb";
	private static final String KAKAO_CLIENT_SECRET = "8q3XHQmlkd1VpBdb0Q8RQBW2LYlFkUT2";
	private static final String KAKAO_REDIRECT_URI = "https://modo-team.com/oauth";


	private final String code;
	private final String codeVerifier;
	private final String redirectUri;
	private final String grantType;

	public MultiValueMap<String, String> toParams() {

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("client_id", KAKAO_CLIENT_ID);
		params.add("client_secret", KAKAO_CLIENT_SECRET);
		params.add("code", code);
		params.add("code_verifier", codeVerifier);
		params.add("redirect_uri", redirectUri);
		params.add("grant_type", grantType);

		return params;

	}

}
