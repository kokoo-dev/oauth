package com.example.oauth.domain.naver.application;

import com.example.oauth.domain.naver.domain.NaverApi;
import com.example.oauth.domain.naver.domain.NaverAuthCategory;
import com.example.oauth.domain.naver.domain.NaverTokenCategory;
import com.example.oauth.global.common.ApiCall;
import com.example.oauth.global.util.RandomUtil;
import com.example.oauth.domain.oauth.OAuthService;
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
public class NaverServiceImpl implements OAuthService {
    Logger logger = LoggerFactory.getLogger(NaverServiceImpl.class);

    @Autowired
    NaverApi naverApi;

    @Override
    public JSONObject getToken(String code) {
        Map<String, String> paramMap = createTokenParamMap(code);
        String requestUrl = naverApi.getAuthHost() + naverApi.getTokenPath();
        String param = UrlUtil.createQueryStr(paramMap);
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

        return map;
    }

    @Override
    public Map<String, String> createAuthParamMap() {
        String state = RandomUtil.getRandomNumStr(130);
        Map<String, String> map = new HashMap<>();

        map.put(NaverAuthCategory.RESPONSE_TYPE.getKey(), naverApi.getResponseType());
        map.put(NaverAuthCategory.CLIENT_ID.getKey(), naverApi.getClientId());
        map.put(NaverAuthCategory.REDIRECT_URI.getKey(), naverApi.getRedirectUri());
        map.put(NaverAuthCategory.STATE.getKey(), state);

        return map;
    }

    @Override
    public JSONObject logout(String accessToken){
        Map<String, String> paramMap = createLogoutParamMap(accessToken);
        String requestUrl = naverApi.getAuthHost() + naverApi.getTokenPath();
        String param = UrlUtil.createQueryStr(paramMap);
        JSONObject jsonObject = null;

        try {
            jsonObject = ApiCall.callGetApi(requestUrl, param);
        } catch (IOException ie){
            logger.error(ie.getMessage());
        }

        return jsonObject;
    }

    public Map<String, String> createLogoutParamMap(String accessToken) {
        Map<String, String> map = new HashMap<>();
        map.put(NaverTokenCategory.GRANT_TYPE.getKey(), naverApi.getGrantTypeDelete());
        map.put(NaverTokenCategory.CLIENT_ID.getKey(), naverApi.getClientId());
        map.put(NaverTokenCategory.CLIENT_SECRET.getKey(), naverApi.getClientSecret());
        map.put(NaverTokenCategory.ACCESS_TOKEN.getKey(), accessToken);
        map.put(NaverTokenCategory.SERVICE_PROVIDER.getKey(), naverApi.getServiceProvider());

        return map;
    }
}
