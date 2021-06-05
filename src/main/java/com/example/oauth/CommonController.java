package com.example.oauth;

import com.example.oauth.common.ApiCall;
import com.example.oauth.domain.google.GoogleApi;
import com.example.oauth.domain.google.GoogleService;
import com.example.oauth.domain.kakao.KakaoApi;
import com.example.oauth.domain.kakao.KakaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommonController {
    @Autowired
    KakaoService kakaoService;

    @Autowired
    GoogleService googleService;

    @Autowired
    KakaoApi kakaoApi;

    @Autowired
    GoogleApi googleApi;

    @GetMapping("/")
    public ModelAndView loginView(){
        ModelAndView mav = new ModelAndView();
        String kakaoUrl = kakaoApi.getAuthHost() + kakaoApi.getAuthorizePath() + ApiCall.createUrl(kakaoService.createAuthParamMap());
        String googleUrl = googleApi.getAuthHost() + googleApi.getAuthPath() + ApiCall.createUrl(googleService.createAuthParamMap());

        mav.setViewName("thymeleaf/login");
        mav.addObject("kakaoUrl", kakaoUrl);
        mav.addObject("googleUrl", googleUrl);

        return mav;
    }

}
