package com.example.oauth.common;

import org.json.simple.JSONObject;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.SecureRandom;

public class CommonUtil {

    /**
     *
     * @param path path경로
     * @param tokenObject 토큰 조회 결과 객체
     * @param session
     * @return
     */
    public static ModelAndView getLoginAfterMav(String path, JSONObject tokenObject, HttpSession session){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("thymeleaf/loginAfter");
        mav.addObject("token", tokenObject);
        mav.addObject("path", path);
        session.setAttribute("oauthToken", tokenObject.get("access_token"));

        return mav;
    }

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

