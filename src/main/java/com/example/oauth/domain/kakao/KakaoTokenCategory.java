package com.example.oauth.domain.kakao;

import lombok.Getter;

@Getter
public enum KakaoTokenCategory {
    GRANT_TYPE("grant_type"),
    CLIENT_ID("client_id"),
    REDIRECT_URI("redirect_uri"),
    CODE("code"),
    CLIENT_SECRET("client_secret");

    private String key;

    private KakaoTokenCategory(String key){
        this.key = key;
    }

}
