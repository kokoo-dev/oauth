package com.example.oauth.domain.kakao;

import lombok.Getter;

@Getter
public enum KakaoLogoutCategory {
    AUTHORIZATION("Authorization"),
    CLIENT_ID("client_id"),
    LOGOUT_REDIRECT_URI("logout_redirect_uri"),
    STATE("state");

    private String key;

    private KakaoLogoutCategory(String key){
        this.key = key;
    }

}
