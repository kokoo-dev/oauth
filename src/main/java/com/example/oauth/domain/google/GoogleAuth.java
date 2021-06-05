package com.example.oauth.domain.google;

public enum GoogleAuth {
    CLIENT_ID("client_id"),
    REDIRECT_URI("redirect_uri"),
    RESPONSE_TYPE("response_type"),
    SCOPE("scope"),
    ACCESS_TYPE("access_type");

    private String key;
    private String description;

    private GoogleAuth(String key){
        this.key = key;
    }

    public String getKey(){
        return key;
    }
}
