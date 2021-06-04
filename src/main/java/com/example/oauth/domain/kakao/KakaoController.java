package com.example.oauth.domain.kakao;

import com.example.oauth.common.ApiCall;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;


@Controller
@RequestMapping("/kakao")
public class KakaoController {
    Logger logger = LoggerFactory.getLogger(KakaoController.class);

    @Autowired
    KakaoService kakaoService;

    @GetMapping("/oauth")
    public ModelAndView oauth(Kakao kakao, HttpSession session){
        logger.info("call.. oauth");

        JSONObject tokenObject = kakaoService.getToken(kakao.getCode());

        ModelAndView mav = new ModelAndView();
        mav.setViewName("thymeleaf/loginAfter");
        mav.addObject("token", tokenObject);
        session.setAttribute("kakaoToken", tokenObject.get("access_token"));

        logger.info(tokenObject.toString());

        return mav;
    }

    @PostMapping("/logout")
    public String logout(Kakao kakao, HttpSession session) throws Exception{
        logger.info("call.. logout");

        JSONObject logoutObject = kakaoService.logout(kakao.getAccessToken());

        if(session.getAttribute("kakaoToken") != null && logoutObject.get("id") != null){
            session.removeAttribute("kakaoToken");
        }

        logger.info(logoutObject.toString());

        return "redirect:/";
    }
}
