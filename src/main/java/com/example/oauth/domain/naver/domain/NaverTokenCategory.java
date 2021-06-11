package com.example.oauth.domain.naver.domain;

import lombok.Getter;

@Getter
public enum NaverTokenCategory {
    GRANT_TYPE("grant_type"),
    CLIENT_ID("client_id"),
    CLIENT_SECRET("client_secret"),
    REDIRECT_URI("redirect_uri"),
    CODE("code"),
    STATE("state"),
    ACCESS_TOKEN("access_token"),
    SERVICE_PROVIDER("service_provider");

    private String key;
    private NaverTokenCategory(String key){
        this.key = key;
    }
}
