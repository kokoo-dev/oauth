package com.example.oauth.domain.naver.api;

import com.example.oauth.domain.naver.application.NaverServiceImpl;
import com.example.oauth.domain.naver.dto.NaverToken;
import com.example.oauth.global.util.ControllerUtil;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/naver")
public class NaverController {
    @Autowired
    NaverServiceImpl naverServiceImpl;

    @GetMapping("/oauth")
    public ModelAndView oauth(NaverToken naverToken, HttpSession session){
        JSONObject tokenObject = naverServiceImpl.getToken(naverToken.getCode());

        ModelAndView mav = ControllerUtil.getLoginAfterMav("naver", tokenObject, session);

        return mav;
    }

    @PostMapping("/logout")
    public String logout(NaverToken naverToken, HttpSession session) {
        String access_token = (String)session.getAttribute("oauthToken");
        JSONObject logoutObject = naverServiceImpl.logout(access_token);

        if(access_token != null){
            session.removeAttribute("oauthToken");
        }

        return "redirect:/";
    }
}
