package com.example.oauth.global.util;

import java.util.Map;
import java.util.Set;

public class UrlUtil {
    public static String createQueryStr(Map<String, String> urlMap){
        StringBuilder urlBuilder = new StringBuilder();
        Set<String> keys = urlMap.keySet();
        String link = "?";
        String equal = "=";

        for(String key : keys){
            String value = urlMap.get(key) == null ? "" : urlMap.get(key);

            urlBuilder.append(link + key + equal + value);

            if("?".equals(link))
                link = "&";
        }

        return urlBuilder.toString();
    }

    public static String changeGetParamToPostParam(String queryStr){
        String result = queryStr;

        if(queryStr.startsWith("?"))
            result = queryStr.substring(1);

        return result;
    }
}
