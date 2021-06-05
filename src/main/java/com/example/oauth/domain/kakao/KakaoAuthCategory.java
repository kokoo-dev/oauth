package com.example.oauth.domain.kakao;

public enum KakaoAuthCategory {
    CLIENT_ID("client_id", "앱 생성 시 발급받은 REST API"),
    REDIRECT_URI("redirect_uri", "인가 코드가 리다이렉트될 URI"),
    RESPONSE_TYPE("response_type", "code로 고정"),
    STATE("state", "로그인 요청과 콜백 간에 상태를 유지하기 위해 사용되는 임의의 문자열"),
    PROMPT("prompt", "동의 화면 요청 시 추가 상호작용을 요청하고자 할 때 전달하는 파라미터");

    private String key;
    private String description;

    private KakaoAuthCategory(String key, String description){
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
