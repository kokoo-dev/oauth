package com.example.oauth.domain.kakao.api;

import com.example.oauth.domain.kakao.applictaion.KakaoServiceImpl;
import com.example.oauth.domain.kakao.dto.KakaoToken;
import com.example.oauth.global.util.ControllerUtil;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/kakao")
public class KakaoController {
    Logger logger = LoggerFactory.getLogger(KakaoController.class);

    @Autowired
    KakaoServiceImpl kakaoServiceImpl;

    @GetMapping("/oauth")
    public ModelAndView oauth(KakaoToken kakaoToken, HttpSession session) {
        logger.info("call.. oauth");

        JSONObject tokenObject = kakaoServiceImpl.getToken(kakaoToken.getCode());

        ModelAndView mav = ControllerUtil.getLoginAfterMav("kakao", tokenObject, session);

        logger.info(tokenObject.toString());

        return mav;
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        logger.info("call.. logout");

        String access_token = (String)session.getAttribute("oauthToken");
        JSONObject logoutObject = kakaoServiceImpl.logout(access_token);

        if(logoutObject.get("id") != null){
            ControllerUtil.removeSession(session, "oauthToken");
        }

        logger.info(logoutObject.toString());

        return "redirect:/";
    }
}
