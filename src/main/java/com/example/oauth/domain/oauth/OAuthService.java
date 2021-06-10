package com.example.oauth.domain.oauth;

import org.json.simple.JSONObject;

import java.util.Map;

public interface OAuthService {
    public JSONObject getToken(String code);
    public Map<String, String> createTokenParamMap(String code);
    public Map<String, String> createAuthParamMap();
    public JSONObject logout(String accessToken);
}
