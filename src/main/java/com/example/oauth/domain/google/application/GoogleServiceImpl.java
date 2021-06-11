package com.example.oauth.domain.google.application;

import com.example.oauth.domain.google.domain.GoogleApi;
import com.example.oauth.domain.google.domain.GoogleAuthCategory;
import com.example.oauth.domain.google.domain.GoogleTokenCategory;
import com.example.oauth.global.common.ApiCall;
import com.example.oauth.domain.oauth.OAuthService;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class GoogleServiceImpl implements OAuthService {
    Logger logger = LoggerFactory.getLogger(GoogleServiceImpl.class);

    @Autowired
    GoogleApi googleApi;

    @Override
    public JSONObject getToken(String code){
        Map<String, String> paramMap = createTokenParamMap(code);
        String requestUrl = googleApi.getAuthHost() + googleApi.getTokenPath();

        JSONObject jsonObject = null;

        try {
            jsonObject = ApiCall.callPostApi(requestUrl, paramMap);
        } catch (IOException ie){
            logger.error(ie.getMessage());
        }

        return jsonObject;
    }

    @Override
    public Map<String, String> createTokenParamMap(String code){
        Map<String, String> map = new HashMap<>();
        map.put(GoogleTokenCategory.CODE.getKey(), code);
        map.put(GoogleTokenCategory.CLIENT_ID.getKey(), googleApi.getClientId());
        map.put(GoogleTokenCategory.CLIENT_SECRET.getKey(), googleApi.getClientPassword());
        map.put(GoogleTokenCategory.REDIRECT_URI.getKey(), googleApi.getRedirectUri());
        map.put(GoogleTokenCategory.GRANT_TYPE.getKey(), googleApi.getGrantTypeIssue());

        return map;
    }

    @Override
    public Map<String, String> createAuthParamMap(){
        Map<String, String> map = new HashMap<>();
        map.put(GoogleAuthCategory.CLIENT_ID.getKey(), googleApi.getClientId());
        map.put(GoogleAuthCategory.REDIRECT_URI.getKey(), googleApi.getRedirectUri());
        map.put(GoogleAuthCategory.SCOPE.getKey(), googleApi.getScope());
        map.put(GoogleAuthCategory.RESPONSE_TYPE.getKey(), googleApi.getResponseType());
        map.put(GoogleAuthCategory.ACCESS_TYPE.getKey(), googleApi.getAccessType());

        return map;
    }

    @Override
    public JSONObject logout(String accessToken) {
        return null;
    }
}
