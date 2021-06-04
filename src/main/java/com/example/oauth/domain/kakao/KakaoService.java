package com.example.oauth.domain.kakao;

import com.example.oauth.common.ApiCall;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class KakaoService {
    Logger logger = LoggerFactory.getLogger(KakaoService.class);
    @Autowired
    KakaoApi kakaoApi;

    public JSONObject getToken(String code){
        Map<String, String> paramMap = createTokenParamMap(code);

        String requestUrl = kakaoApi.getAuthHost() + kakaoApi.getTokenPath();
        String param = ApiCall.createUrl(paramMap);

        JSONObject jsonObject = null;

        try {
            jsonObject = ApiCall.callGetApi(requestUrl, param);
        } catch (IOException ie){
            logger.error(ie.getMessage());
        }

        return jsonObject;
    }

    public JSONObject logout(String accessToken){
        String requestUrl = kakaoApi.getApiHost() + kakaoApi.getLogoutPath();
        JSONObject jsonObject = null;

        try {
            jsonObject = ApiCall.callPostApi(requestUrl, KakaoLogout.AUTHORIZATION.getKey(), "Bearer " + accessToken);
        } catch (IOException ie){
            logger.error(ie.getMessage());
        }

        return jsonObject;

    }

    public Map<String, String> createAuthParamMap(){
        Map<String, String> map = new HashMap<>();
        map.put(KakaoAuth.CLIENT_ID.getKey(),kakaoApi.getApiKey());
        map.put(KakaoAuth.REDIRECT_URI.getKey(), kakaoApi.getRedirectUri());
        map.put(KakaoAuth.RESPONSE_TYPE.getKey(), "code");

        return map;
    }

    public Map<String, String> createTokenParamMap(String code){
        Map<String, String> map = new HashMap<>();
        map.put(KakaoToken.GRANT_TYPE.getKey(), "authorization_code");
        map.put(KakaoToken.CLIENT_ID.getKey(), kakaoApi.getApiKey());
        map.put(KakaoToken.REDIRECT_URI.getKey(), kakaoApi.getRedirectUri());
        map.put(KakaoToken.CODE.getKey(), code);

        return map;
    }

    public Map<String, String> createLogoutParamMap(String accessToken){
        Map<String, String> map = new HashMap<>();
        map.put(KakaoLogout.AUTHORIZATION.getKey(), "Bearer " + accessToken);

        return map;
    }
}
