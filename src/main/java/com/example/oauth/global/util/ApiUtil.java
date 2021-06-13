package com.example.oauth.global.util;

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

public class ApiUtil {
    static Logger logger = LoggerFactory.getLogger(ApiUtil.class);

    public static JSONObject callPostApi(String urlInfo, Map<String, String> paramMap) throws IOException {
        URL url = new URL(urlInfo);
        String param = UrlUtil.createQueryStr(paramMap);
        String postParam = UrlUtil.changeGetParamToPostParam(param);
        byte[] postDataBytes = postParam.getBytes(StandardCharsets.UTF_8.name());

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

    public static JSONObject callGetApi(String urlInfo, String queryStr) throws IOException {
        URL url = new URL(urlInfo + queryStr);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod(HttpMethod.GET.name());
        connection.setRequestProperty("Content-type", ContentType.APPLICATION_JSON.getType());

        return getResponse(connection);
    }

    public static JSONObject callDeleteApi(String urlInfo, String queryStr) throws IOException {
        URL url = new URL(urlInfo + queryStr);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod(HttpMethod.DELETE.name());
        connection.setRequestProperty("Content-type", ContentType.APPLICATION_JSON.getType());

        return getResponse(connection);
    }

    private static JSONObject getResponse(HttpURLConnection connection) throws IOException{
        BufferedReader br = readResponseStream(connection);
        String result = getResult(br);

        logger.info("result :: " + result);

        br.close();
        connection.disconnect();

        JSONObject jsonObject = convertStringToJson(result);

        return jsonObject;
    }

    private static BufferedReader readResponseStream(HttpURLConnection connection) throws IOException{
        BufferedReader br;

        if(connection.getResponseCode() == HttpURLConnection.HTTP_OK || connection.getResponseCode() == HttpURLConnection.HTTP_MOVED_TEMP)
            br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8.name()));
        else
            br = new BufferedReader(new InputStreamReader(connection.getErrorStream(), StandardCharsets.UTF_8.name()));

        return br;
    }

    private static String getResult(BufferedReader bufferedReader) throws IOException {
        StringBuilder result = new StringBuilder();
        String line = "";

        while((line = bufferedReader.readLine()) != null){
            result.append(line);
        }

        return result.toString();
    }

    private static JSONObject convertStringToJson(String s){
        JSONObject jsonObject = null;

        try {
            JSONParser jsonParser = new JSONParser();
            jsonObject = (JSONObject) jsonParser.parse(s);

        } catch (ParseException ie){
            logger.error(ie.getMessage());
        }

        return jsonObject;
    }

}
