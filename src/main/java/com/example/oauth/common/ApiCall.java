package com.example.oauth.common;

import com.example.oauth.domain.kakao.KakaoController;
import com.nimbusds.common.contenttype.ContentType;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;

public class ApiCall {
    static Logger logger = LoggerFactory.getLogger(ApiCall.class);

    public static JSONObject callPostApi(String urlInfo, Map<String, String> paramMap) throws IOException {
        URL url = new URL(urlInfo);
        String param = createUrl(paramMap);
        byte[] postDataBytes = param.replace("?","").toString().getBytes(StandardCharsets.UTF_8.name());

        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod(HttpMethod.POST.name());
        connection.setRequestProperty("Content-Type", ContentType.APPLICATION_URLENCODED.getType());
        connection.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        connection.setDoOutput(true);
        connection.getOutputStream().write(postDataBytes); // POST 호출

        return getResponse(connection);
    }

    public static JSONObject callPostApi(String urlInfo, String key, String value) throws IOException {
        URL url = new URL(urlInfo);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod(HttpMethod.POST.name());
        connection.setRequestProperty(key, value);

        return getResponse(connection);
    }

    public static JSONObject callGetApi(String urlInfo, String param) throws IOException {
        URL url = new URL(urlInfo + param);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod(HttpMethod.GET.name());
        connection.setRequestProperty("Content-type", ContentType.APPLICATION_JSON.getType());

        return getResponse(connection);
    }

    public static JSONObject callDeleteApi(String urlInfo, String param) throws IOException {
        URL url = new URL(urlInfo + param);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod(HttpMethod.DELETE.name());
        connection.setRequestProperty("Content-type", ContentType.APPLICATION_JSON.getType());

        return getResponse(connection);
    }

    private static JSONObject getResponse(HttpURLConnection connection) throws IOException{
        BufferedReader br;
        InputStream inputStream;

        if(connection.getResponseCode() == HttpURLConnection.HTTP_OK)
            br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8.name()));
        else
            br = new BufferedReader(new InputStreamReader(connection.getErrorStream(), StandardCharsets.UTF_8.name()));

        StringBuilder result = new StringBuilder();
        String line = "";

        while((line = br.readLine()) != null){
            result.append(line);
        }

        logger.info("result :: " + result.toString());

        br.close();
        connection.disconnect();

        JSONObject jsonObject = null;

        try {
            JSONParser jsonParser = new JSONParser();
            jsonObject = (JSONObject) jsonParser.parse(result.toString());

        } catch (ParseException ie){
            logger.error(ie.getMessage());
        }

        return jsonObject;
    }

    public static String createUrl(Map<String, String> urlMap){
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
}
