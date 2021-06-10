package com.example.oauth.common;

import com.example.oauth.domain.facebook.FacebookApi;
import com.example.oauth.domain.facebook.FacebookService;
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
    FacebookService facebookService;

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
        String kakaoUrl = kakaoApi.getAuthHost() + kakaoApi.getAuthorizePath() + ApiCall.createQueryStr(kakaoService.createAuthParamMap());
        String googleUrl = googleApi.getAuthHost() + googleApi.getAuthPath() + ApiCall.createQueryStr(googleService.createAuthParamMap());
        String naverUrl = naverApi.getAuthHost() + naverApi.getAuthPath() + ApiCall.createQueryStr(naverService.createAuthParamMap());
        String facebookUrl = facebookApi.getAuthHost() + facebookApi.getAuthPath() + ApiCall.createQueryStr(facebookService.createAuthParamMap());

        mav.setViewName("thymeleaf/login");
        mav.addObject("kakaoUrl", kakaoUrl);
        mav.addObject("googleUrl", googleUrl);
        mav.addObject("naverUrl", naverUrl);
        mav.addObject("facebookUrl", facebookUrl);

        return mav;
    }

}
