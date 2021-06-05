package com.example.oauth.domain.kakao;

public enum KakaoTokenCategory {
    GRANT_TYPE("grant_type", "authorization_code로 고정"),
    CLIENT_ID("client_id", "앱 생성 시 발급받은 REST API"),
    REDIRECT_URI("redirect_uri", "인가 코드가 리다이렉트된 URI"),
    CODE("code", "인가 코드 받기 요청으로 얻은 인가 코드"),
    CLIENT_SECRET("client_secret", "토큰 발급 시, 보안을 강화하기 위해 추가 확인하는 코드");

    private String key;
    private String description;

    private KakaoTokenCategory(String key, String description){
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
