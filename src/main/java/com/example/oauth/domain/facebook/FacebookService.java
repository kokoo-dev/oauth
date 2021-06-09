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

    public JSONObject logout(String accessToken){
        Map<String, String> debugParamMap = createDebugTokenParamMap(accessToken);
        String debugParam = ApiCall.createUrl(debugParamMap);
        String requestDebugUrl = facebookApi.getTokenHost() + facebookApi.getDebugTokenPath();
        JSONObject debugObject = null;

        try {
            debugObject = ApiCall.callGetApi(requestDebugUrl, debugParam);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject dataObject = (JSONObject)debugObject.get("data");
        String userId = dataObject.get("user_id").toString();

        Map<String, String> deleteParamMap = createDeleteTokenParamMap(accessToken);
        String deleteParam = ApiCall.createUrl(deleteParamMap);
        String requestDeleteUrl = facebookApi.getTokenHost() + facebookApi.getPermissionsPath().replace("@userId", userId);
        JSONObject deleteObject = null;

        try {
            deleteObject = ApiCall.callDeleteApi(requestDeleteUrl,deleteParam);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return deleteObject;
    }

    public Map<String, String> createDebugTokenParamMap(String accessToken){
        Map<String, String> map = new HashMap<>();
        map.put(FacebookTokenCategory.INPUT_TOKEN.getKey(),accessToken);
        map.put(FacebookTokenCategory.ACCESS_TOKEN.getKey(), accessToken);

        return map;
    }

    public Map<String, String> createDeleteTokenParamMap(String accessToken){
        Map<String, String> map = new HashMap<>();
        map.put(FacebookTokenCategory.ACCESS_TOKEN.getKey(),accessToken);

        return map;
    }
}
