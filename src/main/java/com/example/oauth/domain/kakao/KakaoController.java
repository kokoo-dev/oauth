package com.example.oauth.domain.kakao;

import com.example.oauth.common.CommonUtil;
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
    KakaoService kakaoService;

    @GetMapping("/oauth")
    public ModelAndView oauth(KakaoToken kakaoToken, HttpSession session) {
        logger.info("call.. oauth");

        JSONObject tokenObject = kakaoService.getToken(kakaoToken.getCode());

        ModelAndView mav = CommonUtil.getLoginAfterMav("kakao", tokenObject, session);

        logger.info(tokenObject.toString());

        return mav;
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        logger.info("call.. logout");

        String access_token = (String)session.getAttribute("oauthToken");
        JSONObject logoutObject = kakaoService.logout(access_token);

        if(session.getAttribute("oauthToken") != null && logoutObject.get("id") != null){
            session.removeAttribute("oauthToken");
        }

        logger.info(logoutObject.toString());

        return "redirect:/";
    }
}
