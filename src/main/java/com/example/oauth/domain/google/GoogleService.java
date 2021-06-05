package com.example.oauth.domain.google;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GoogleService {

    @Autowired
    GoogleApi googleApi;


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
