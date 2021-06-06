package com.example.oauth.domain.google;

import com.example.oauth.common.ApiCall;
import com.example.oauth.domain.OAuthService;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class GoogleService implements OAuthService {
    Logger logger = LoggerFactory.getLogger(GoogleService.class);

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
        map.put(GoogleTokenCategory.GRANT_TYPE.getKey(), googleApi.getGrant_type());

        return map;
    }

    @Override
    public Map<String, String> createAuthParamMap(){
        Map<String, String> map = new HashMap<>();
        map.put(GoogleAuth.CLIENT_ID.getKey(), googleApi.getClientId());
        map.put(GoogleAuth.REDIRECT_URI.getKey(), googleApi.getRedirectUri());
        map.put(GoogleAuth.SCOPE.getKey(), googleApi.getScope());
        map.put(GoogleAuth.RESPONSE_TYPE.getKey(), googleApi.getResponseType());
        map.put(GoogleAuth.ACCESS_TYPE.getKey(), googleApi.getAccess_type());

        return map;
    }
}
