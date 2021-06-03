package com.example.oauth.domain.kakao;

import com.example.oauth.common.ApiCall;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class KakaoService {
    Logger logger = LoggerFactory.getLogger(KakaoService.class);

    public JSONObject getToken(String code){
        Map<String, String> paramMap = createTokenParamMap(code);
        String requestUrl = KakaoRestApi.AUTH_HOST.getValue() + KakaoRestApi.TOKEN_PATH.getValue();
        String param = ApiCall.createUrl(paramMap);

        JSONObject jsonObject = null;

        try {
            jsonObject = ApiCall.callPostApi(requestUrl, param);
        } catch (IOException ie){
            logger.error(ie.getMessage());
        }

        return jsonObject;
    }

    public JSONObject logout(String accessToken){
        Map<String, String> paramMap = createLogoutParamMap(accessToken);
        String requestUrl = KakaoRestApi.API_HOST.getValue() + KakaoRestApi.LOGOUT_PATH.getValue();
        String param = ApiCall.createUrl(paramMap);
        JSONObject jsonObject = null;

        try {
            jsonObject = ApiCall.callPostApi(requestUrl, param);
        } catch (IOException ie){
            logger.error(ie.getMessage());
        }

        return jsonObject;

    }

    public Map<String, String> createAuthParamMap(){
        Map<String, String> map = new HashMap<>();
        map.put(KakaoAuth.CLIENT_ID.getKey(), KakaoRestApi.API_KEY.getValue());
        map.put(KakaoAuth.REDIRECT_URI.getKey(), KakaoRestApi.REDIRECT_URI.getValue());
        map.put(KakaoAuth.RESPONSE_TYPE.getKey(), "code");

        return map;
    }

    public Map<String, String> createTokenParamMap(String code){
        Map<String, String> map = new HashMap<>();
        map.put(KakaoToken.GRANT_TYPE.getKey(), "authorization_code");
        map.put(KakaoToken.CLIENT_ID.getKey(), KakaoRestApi.API_KEY.getValue());
        map.put(KakaoToken.REDIRECT_URI.getKey(), KakaoRestApi.REDIRECT_URI.getValue());
        map.put(KakaoToken.CODE.getKey(), code);

        return map;
    }

    public Map<String, String> createLogoutParamMap(String accessToken){
        Map<String, String> map = new HashMap<>();
        map.put(KakaoLogout.AUTHORIZATION.getKey(), "Bearer " + accessToken);

        return map;
    }
}
