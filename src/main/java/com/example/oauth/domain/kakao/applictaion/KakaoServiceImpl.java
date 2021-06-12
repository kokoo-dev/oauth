package com.example.oauth.domain.kakao.applictaion;

import com.example.oauth.domain.kakao.domain.KakaoApi;
import com.example.oauth.domain.kakao.domain.KakaoAuthCategory;
import com.example.oauth.domain.kakao.domain.KakaoLogoutCategory;
import com.example.oauth.domain.kakao.domain.KakaoTokenCategory;
import com.example.oauth.global.common.ApiCall;
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
public class KakaoServiceImpl implements OAuthService {
    Logger logger = LoggerFactory.getLogger(KakaoServiceImpl.class);
    @Autowired
    KakaoApi kakaoApi;

    @Override
    public JSONObject getToken(String code){
        Map<String, String> paramMap = createTokenParamMap(code);

        String requestUrl = kakaoApi.getAuthHost() + kakaoApi.getTokenPath();
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
    public Map<String, String> createAuthParamMap(){
        Map<String, String> map = new HashMap<>();
        map.put(KakaoAuthCategory.CLIENT_ID.getKey(),kakaoApi.getApiKey());
        map.put(KakaoAuthCategory.REDIRECT_URI.getKey(), kakaoApi.getRedirectUri());
        map.put(KakaoAuthCategory.RESPONSE_TYPE.getKey(), kakaoApi.getResponseType());

        return map;
    }

    @Override
    public Map<String, String> createTokenParamMap(String code){
        Map<String, String> map = new HashMap<>();
        map.put(KakaoTokenCategory.GRANT_TYPE.getKey(), kakaoApi.getGrantTypeIssue());
        map.put(KakaoTokenCategory.CLIENT_ID.getKey(), kakaoApi.getApiKey());
        map.put(KakaoTokenCategory.REDIRECT_URI.getKey(), kakaoApi.getRedirectUri());
        map.put(KakaoTokenCategory.CODE.getKey(), code);

        return map;
    }

    @Override
    public JSONObject logout(String accessToken){
        String requestUrl = kakaoApi.getApiHost() + kakaoApi.getLogoutPath();
//        Map<String, String> paramMap = createLogoutParamMap(accessToken);

        JSONObject jsonObject = null;

        try {
//            jsonObject = ApiCall.callPostApi(requestUrl, paramMap);
            jsonObject = ApiCall.callPostApi(requestUrl, KakaoLogoutCategory.AUTHORIZATION.getKey(), kakaoApi.getAuthType() + " " + accessToken);
        } catch (IOException ie){
            logger.error(ie.getMessage());
        }

        return jsonObject;

    }

    public Map<String, String> createLogoutParamMap(String accessToken){
        Map<String, String> map = new HashMap<>();
        map.put(KakaoLogoutCategory.AUTHORIZATION.getKey(), kakaoApi.getAuthType() + " " + accessToken);

        return map;
    }
}
