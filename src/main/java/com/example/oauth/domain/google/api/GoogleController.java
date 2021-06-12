package com.example.oauth.domain.google.api;

import com.example.oauth.domain.google.application.GoogleServiceImpl;
import com.example.oauth.domain.google.dto.GoogleToken;
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
@RequestMapping("/google")
public class GoogleController {
    Logger logger = LoggerFactory.getLogger(GoogleController.class);

    @Autowired
    GoogleServiceImpl googleServiceImpl;

    @GetMapping("/oauth")
    public ModelAndView oauth(GoogleToken googleToken, HttpSession session){
        logger.info("call.. oauth");

        JSONObject tokenObject = googleServiceImpl.getToken(googleToken.getCode());

        ModelAndView mav = ControllerUtil.getLoginAfterMav("google", tokenObject, session);

        return mav;
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        logger.info("call.. logout");

        ControllerUtil.removeSession(session, "oauthToken");

        return "redirect:/";
    }
}
