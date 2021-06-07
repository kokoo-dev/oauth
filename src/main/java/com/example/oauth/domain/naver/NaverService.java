package com.example.oauth.domain.naver;

import com.example.oauth.common.ApiCall;
import com.example.oauth.common.OAuthService;
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
public class NaverService implements OAuthService {
    Logger logger = LoggerFactory.getLogger(NaverService.class);

    @Autowired
    NaverApi naverApi;

    @Override
    public JSONObject getToken(String code) {
        Map<String, String> paramMap = createTokenParamMap(code);
        String requestUrl = naverApi.getAuthHost() + naverApi.getTokenPath();
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
        map.put(NaverTokenCategory.GRANT_TYPE.getKey(), naverApi.getGrantTypeIssue());
        map.put(NaverTokenCategory.CLIENT_ID.getKey(), naverApi.getClientId());
        map.put(NaverTokenCategory.CLIENT_SECRET.getKey(), naverApi.getClientSecret());
        map.put(NaverTokenCategory.REDIRECT_URI.getKey(), naverApi.getRedirectUri());
        map.put(NaverTokenCategory.CODE.getKey(), code);
//        map.put(NaverTokenCategory.STATE.getKey(), );

        return map;
    }

    @Override
    public Map<String, String> createAuthParamMap() {
        SecureRandom random = new SecureRandom();
        String state = new BigInteger(130, random).toString();
        Map<String, String> map = new HashMap<>();

        map.put(NaverAuthCategory.RESPONSE_TYPE.getKey(), naverApi.getResponseType());
        map.put(NaverAuthCategory.CLIENT_ID.getKey(), naverApi.getClientId());
        map.put(NaverAuthCategory.REDIRECT_URI.getKey(), naverApi.getRedirectUri());
        map.put(NaverAuthCategory.STATE.getKey(), state);

        return map;
    }

    public JSONObject logout(String access_token){
        Map<String, String> paramMap = createLogoutParamMap(access_token);
        String requestUrl = naverApi.getAuthHost() + naverApi.getTokenPath();
        String param = ApiCall.createUrl(paramMap);
        JSONObject jsonObject = null;

        try {
            jsonObject = ApiCall.callGetApi(requestUrl, param);
        } catch (IOException ie){
            logger.error(ie.getMessage());
        }

        return jsonObject;
    }

    public Map<String, String> createLogoutParamMap(String access_token) {
        Map<String, String> map = new HashMap<>();
        map.put(NaverTokenCategory.GRANT_TYPE.getKey(), naverApi.getGrantTypeDelete());
        map.put(NaverTokenCategory.CLIENT_ID.getKey(), naverApi.getClientId());
        map.put(NaverTokenCategory.CLIENT_SECRET.getKey(), naverApi.getClientSecret());
        map.put(NaverTokenCategory.ACCESS_TOKEN.getKey(), access_token);
        map.put(NaverTokenCategory.SERVICE_PROVIDER.getKey(), naverApi.getServiceProvider());

        return map;
    }
}
