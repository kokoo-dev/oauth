package com.example.oauth.global.util;

import org.json.simple.JSONObject;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.SecureRandom;

public class ControllerUtil {

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

    public static boolean removeSession(HttpSession session, String name){
        if(session.getAttribute(name) != null) {
            session.removeAttribute(name);
            return true;
        }

        return false;
    }
}

