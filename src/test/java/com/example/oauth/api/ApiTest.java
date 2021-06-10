package com.example.oauth.api;

import com.example.oauth.common.ApiCall;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ApiTest {

    @Test
    public void testCreateUrl(){
        String result = "?param1=123&param2=456&param3=789";
        String domain = "http://test.domain.com";
        Map<String, String> map = new LinkedHashMap<>();
        map.put("param1", "123");
        map.put("param2", "456");
        map.put("param3", "789");

        assertEquals(result, ApiCall.createQueryStr(map));
    }

    @Test
    public void testCreateNullParamUrl(){
        String result = "?param1=&param2=&param3=";
        String domain = "http://test.domain.com";
        Map<String, String> map = new LinkedHashMap<>();
        map.put("param1", null);
        map.put("param2", null);
        map.put("param3", null);

        assertEquals(result, ApiCall.createQueryStr(map));
    }

}