package com.example.oauth.domain.kakao;

public enum KakaoLogoutCategory {
    AUTHORIZATION("Authorization", "사용자 인증 수단");

    private String key;
    private String description;

    private KakaoLogoutCategory(String key, String description){
        this.key = key;
        this.description = description;
    }

    public String getKey(){
        return key;
    }

    public String getDescription(){
        return description;
    }
}
