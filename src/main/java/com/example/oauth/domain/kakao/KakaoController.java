package com.example.oauth.domain.kakao;

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

        ModelAndView mav = new ModelAndView();
        mav.setViewName("thymeleaf/loginAfter");
        mav.addObject("token", tokenObject);
        session.setAttribute("kakaoToken", tokenObject.get("access_token"));

        logger.info(tokenObject.toString());

        return mav;
    }

    @PostMapping("/logout")
    public String logout(KakaoToken kakaoToken, HttpSession session) {
        logger.info("call.. logout");

        JSONObject logoutObject = kakaoService.logout(kakaoToken.getAccessToken());

        if(session.getAttribute("kakaoToken") != null && logoutObject.get("id") != null){
            session.removeAttribute("kakaoToken");
        }

        logger.info(logoutObject.toString());

        return "redirect:/";
    }
}
