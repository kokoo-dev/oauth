package com.example.oauth.domain.facebook.application;

import com.example.oauth.domain.facebook.domain.FacebookApi;
import com.example.oauth.domain.facebook.domain.FacebookTokenCategory;
import com.example.oauth.global.util.ApiUtil;
import com.example.oauth.global.util.RandomUtil;
import com.example.oauth.domain.oauth.OAuthService;
import com.example.oauth.domain.naver.domain.NaverAuthCategory;
import com.example.oauth.global.util.UrlUtil;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class FacebookServiceImpl implements OAuthService {
    Logger logger = LoggerFactory.getLogger(FacebookServiceImpl.class);

    @Autowired
    FacebookApi facebookApi;

    @Override
    public JSONObject getToken(String code) {
        Map<String, String> paramMap = createTokenParamMap(code);
        String requestUrl = facebookApi.getTokenHost() + facebookApi.getTokenPath();
        String param = UrlUtil.createQueryStr(paramMap);

        JSONObject jsonObject = null;

        try {
            jsonObject = ApiUtil.callGetApi(requestUrl, param);
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
        String state = RandomUtil.getRandomNumStr(130);
        Map<String, String> map = new HashMap<>();

        map.put(NaverAuthCategory.CLIENT_ID.getKey(), facebookApi.getClientId());
        map.put(NaverAuthCategory.REDIRECT_URI.getKey(), facebookApi.getRedirectUri());
        map.put(NaverAuthCategory.STATE.getKey(), state);

        return map;
    }

    @Override
    public JSONObject logout(String accessToken){
        Map<String, String> debugParamMap = createDebugTokenParamMap(accessToken);
        String debugParam = UrlUtil.createQueryStr(debugParamMap);
        String requestDebugUrl = facebookApi.getTokenHost() + facebookApi.getDebugTokenPath();
        JSONObject debugObject = null;

        try {
            debugObject = ApiUtil.callGetApi(requestDebugUrl, debugParam);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject dataObject = (JSONObject)debugObject.get("data");
        String userId = dataObject.get("user_id").toString();

        Map<String, String> deleteParamMap = createDeleteTokenParamMap(accessToken);
        String deleteParam = UrlUtil.createQueryStr(deleteParamMap);
        String requestDeleteUrl = facebookApi.getTokenHost() + facebookApi.getPermissionsPath().replace("@userId", userId);
        JSONObject deleteObject = null;

        try {
            deleteObject = ApiUtil.callDeleteApi(requestDeleteUrl,deleteParam);
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
