package com.example.oauth.global.util;

import org.json.simple.JSONObject;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.SecureRandom;

public class RandomUtil {

    /**
     *
     * @param numBits
     * @return
     */
    public static String getRandomNumStr(int numBits){
        SecureRandom random = new SecureRandom();
        String result = new BigInteger(130, random).toString();

        return result;
    }
}

