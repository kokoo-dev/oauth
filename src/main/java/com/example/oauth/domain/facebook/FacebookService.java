package com.example.oauth.domain.facebook;

import com.example.oauth.common.ApiCall;
import com.example.oauth.common.OAuthService;
import com.example.oauth.domain.naver.NaverAuthCategory;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Service
public class FacebookService implements OAuthService {
    Logger logger = LoggerFactory.getLogger(FacebookService.class);

    @Autowired
    FacebookApi facebookApi;

    @Override
    public JSONObject getToken(String code) {
        Map<String, String> paramMap = createTokenParamMap(code);
        String requestUrl = facebookApi.getTokenHost() + facebookApi.getTokenPath();
        String param = ApiCall.createUrl(paramMap);

        JSONObject jsonObject = null;

        try {
            jsonObject = ApiCall.callGetApi(requestUrl, param);
        } catch (IOException ie){
            logger.error(ie.getMessage());
        }

        return jsonObject;
    }

    @Override
    public Map<String, String> createTokenParamMap(String code) {
        Map<String, String> map = new HashMap<>();
        map.put(FacebookTokenCategory.CODE.getKey(), code);
        map.put(FacebookTokenCategory.CLIENT_ID.getKey(), facebookApi.getClientId());
        map.put(FacebookTokenCategory.CLIENT_SECRET.getKey(), facebookApi.getClientSecret());
        map.put(FacebookTokenCategory.REDIRECT_URI.getKey(), facebookApi.getRedirectUri());

        return map;
    }

    @Override
    public Map<String, String> createAuthParamMap() {
        SecureRandom random = new SecureRandom();
        String state = new BigInteger(130, random).toString();
        Map<String, String> map = new HashMap<>();

        map.put(NaverAuthCategory.CLIENT_ID.getKey(), facebookApi.getClientId());
        map.put(NaverAuthCategory.REDIRECT_URI.getKey(), facebookApi.getRedirectUri());
        map.put(NaverAuthCategory.STATE.getKey(), state);

        return map;
    }
}
