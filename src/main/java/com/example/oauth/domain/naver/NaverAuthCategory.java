package com.example.oauth.domain.naver;

import lombok.Getter;

@Getter
public enum NaverAuthCategory {
    RESPONSE_TYPE("response_type"),
    CLIENT_ID("client_id"),
    REDIRECT_URI("redirect_uri"),
    STATE("state");

    private String key;
    private NaverAuthCategory(String key){
        this.key = key;
    }
}
