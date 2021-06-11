package com.example.oauth.global.common;

import com.example.oauth.domain.facebook.domain.FacebookApi;
import com.example.oauth.domain.facebook.application.FacebookServiceImpl;
import com.example.oauth.domain.google.domain.GoogleApi;
import com.example.oauth.domain.google.application.GoogleServiceImpl;
import com.example.oauth.domain.kakao.domain.KakaoApi;
import com.example.oauth.domain.kakao.applictaion.KakaoServiceImpl;
import com.example.oauth.domain.naver.domain.NaverApi;
import com.example.oauth.domain.naver.application.NaverServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommonController {
    @Autowired
    KakaoServiceImpl kakaoServiceImpl;

    @Autowired
    GoogleServiceImpl googleServiceImpl;

    @Autowired
    NaverServiceImpl naverServiceImpl;

    @Autowired
    FacebookServiceImpl facebookServiceImpl;

    @Autowired
    KakaoApi kakaoApi;

    @Autowired
    GoogleApi googleApi;

    @Autowired
    NaverApi naverApi;

    @Autowired
    FacebookApi facebookApi;

    @GetMapping("/")
    public ModelAndView loginView(){
        ModelAndView mav = new ModelAndView();
        String kakaoUrl = kakaoApi.getAuthHost() + kakaoApi.getAuthorizePath() + ApiCall.createQueryStr(kakaoServiceImpl.createAuthParamMap());
        String googleUrl = googleApi.getAuthHost() + googleApi.getAuthPath() + ApiCall.createQueryStr(googleServiceImpl.createAuthParamMap());
        String naverUrl = naverApi.getAuthHost() + naverApi.getAuthPath() + ApiCall.createQueryStr(naverServiceImpl.createAuthParamMap());
        String facebookUrl = facebookApi.getAuthHost() + facebookApi.getAuthPath() + ApiCall.createQueryStr(facebookServiceImpl.createAuthParamMap());

        mav.setViewName("thymeleaf/login");
        mav.addObject("kakaoUrl", kakaoUrl);
        mav.addObject("googleUrl", googleUrl);
        mav.addObject("naverUrl", naverUrl);
        mav.addObject("facebookUrl", facebookUrl);

        return mav;
    }

}
