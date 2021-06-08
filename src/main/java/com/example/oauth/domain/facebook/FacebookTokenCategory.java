package com.example.oauth.domain.facebook;

import lombok.Getter;

@Getter
public enum FacebookTokenCategory {
    CLIENT_ID("client_id"),
    REDIRECT_URI("redirect_uri"),
    CLIENT_SECRET("client_secret"),
    CODE("code");

    private String key;
    private FacebookTokenCategory(String key){
        this.key = key;
    }
}
