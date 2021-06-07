package com.example.oauth.domain.google;

import lombok.Getter;

@Getter
public enum GoogleAuthCategory {
    CLIENT_ID("client_id"),
    REDIRECT_URI("redirect_uri"),
    RESPONSE_TYPE("response_type"),
    SCOPE("scope"),
    ACCESS_TYPE("access_type");

    private String key;
    private String description;

    private GoogleAuthCategory(String key){
        this.key = key;
    }
}
