package com.example.oauth.domain.google;

public enum GoogleTokenCategory {
    CODE("code"),
    CLIENT_ID("client_id"),
    CLIENT_SECRET("client_secret"),
    REDIRECT_URI("redirect_uri"),
    GRANT_TYPE("grant_type");

    private String key;
    private GoogleTokenCategory(String key){
        this.key = key;
    }

    public String getKey(){
        return key;
    }
}
