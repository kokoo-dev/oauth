package com.example.oauth.domain.google;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoogleToken {
    private String access_token;
    private int expires_in;
    private String refresh_token;
    private String token_type;
    private String id_token;

    private String code;
    private String scope;
    private String authuser;
    private String prompt;
}
