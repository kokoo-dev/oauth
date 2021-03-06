package com.example.oauth.util;

import com.example.oauth.global.util.UrlUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UtilTest {

    @Test
    public void testCreateQueryStr(){
        String result = "?param1=123&param2=456&param3=789";
        Map<String, String> map = new LinkedHashMap<>();
        map.put("param1", "123");
        map.put("param2", "456");
        map.put("param3", "789");

        assertEquals(result, UrlUtil.createQueryStr(map));
    }

    @Test
    public void testCreateNullQueryStr(){
        String result = "?param1=&param2=&param3=";
        Map<String, String> map = new LinkedHashMap<>();
        map.put("param1", null);
        map.put("param2", null);
        map.put("param3", null);

        assertEquals(result, UrlUtil.createQueryStr(map));
    }

}