package com.example.oauth;

import com.example.oauth.common.ApiCall;
import com.example.oauth.domain.google.GoogleApi;
import com.example.oauth.domain.google.GoogleService;
import com.example.oauth.domain.kakao.KakaoApi;
import com.example.oauth.domain.kakao.KakaoService;
import com.example.oauth.domain.naver.NaverApi;
import com.example.oauth.domain.naver.NaverService;
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
    NaverService naverService;

    @Autowired
    KakaoApi kakaoApi;

    @Autowired
    GoogleApi googleApi;

    @Autowired
    NaverApi naverApi;

    @GetMapping("/")
    public ModelAndView loginView(){
        ModelAndView mav = new ModelAndView();
        String kakaoUrl = kakaoApi.getAuthHost() + kakaoApi.getAuthorizePath() + ApiCall.createUrl(kakaoService.createAuthParamMap());
        String googleUrl = googleApi.getAuthHost() + googleApi.getAuthPath() + ApiCall.createUrl(googleService.createAuthParamMap());
        String naverUrl = naverApi.getAuthHost() + naverApi.getAuthPath() + ApiCall.createUrl(naverService.createAuthParamMap());

        mav.setViewName("thymeleaf/login");
        mav.addObject("kakaoUrl", kakaoUrl);
        mav.addObject("googleUrl", googleUrl);
        mav.addObject("naverUrl", naverUrl);

        return mav;
    }

}
