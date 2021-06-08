package com.example.oauth.domain.google;

import com.example.oauth.common.ApiCall;
import com.example.oauth.domain.kakao.KakaoController;
import com.example.oauth.domain.kakao.KakaoToken;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

@Controller
@RequestMapping("/google")
public class GoogleController {
    Logger logger = LoggerFactory.getLogger(GoogleController.class);

    @Autowired
    GoogleService googleService;

    @GetMapping("/oauth")
    public ModelAndView oauth(GoogleToken googleToken, HttpSession session){
        logger.info("call.. oauth");

        JSONObject tokenObject = googleService.getToken(googleToken.getCode());

        ModelAndView mav = new ModelAndView();
        mav.setViewName("thymeleaf/loginAfter");
        mav.addObject("token", tokenObject);
        mav.addObject("path", "google");
        session.setAttribute("oauthToken", tokenObject.get("access_token"));

        return mav;
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        logger.info("call.. logout");

        if(session.getAttribute("oauthToken") != null){
            session.removeAttribute("oauthToken");
        }

        return "redirect:/";
    }
}
