package com.modo.modoapiserver.dto.controller.auth;

import static org.springframework.util.StringUtils.hasText;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

public class KakaoSignInRequest {

	@JsonProperty("code")
	private String code;

	@JsonProperty("codeVerifier")
	private String codeVerifier;

	@JsonProperty("redirectUri")
	private String redirectUri;

	@JsonProperty("grantType")
	private String grantType;

	public KakaoOAuth toOAuth() {
		return KakaoOAuth.builder()
			.code(code)
			.codeVerifier(codeVerifier)
			.redirectUri(redirectUri)
			.grantType(grantType)
			.build();
	}

	@JsonSetter
	private void setGrantType(String grantType) {
		this.grantType = hasText(grantType) ? "authorization_code" : grantType;
	}

}
