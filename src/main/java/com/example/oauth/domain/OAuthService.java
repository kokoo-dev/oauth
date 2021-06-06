package com.example.oauth.domain;

import org.json.simple.JSONObject;

import java.util.Map;

public interface OAuthService {
    public JSONObject getToken(String code);
    public Map<String, String> createTokenParamMap(String code);
    public Map<String, String> createAuthParamMap();
}
