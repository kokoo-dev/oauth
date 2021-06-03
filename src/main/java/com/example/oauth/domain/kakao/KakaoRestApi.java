package com.example.oauth.domain.kakao;

public enum KakaoRestApi {
    AUTH_HOST("https://kauth.kakao.com"),
    API_HOST("https://kapi.kakao.com"),
    AUTHORIZE_PATH("/oauth/authorize"),
    TOKEN_PATH("/oauth/token"),
    LOGOUT_PATH("/v1/user/logout"),
    API_KEY("9f14bae62ddae3bc77dcc628741601b9"),
    REDIRECT_URI("http://localhost:8090/kakao/oauth");

    private String value;
    private KakaoRestApi(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
