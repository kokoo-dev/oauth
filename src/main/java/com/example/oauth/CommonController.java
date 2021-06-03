package com.example.oauth;

import com.example.oauth.common.ApiCall;
import com.example.oauth.domain.kakao.KakaoRestApi;
import com.example.oauth.domain.kakao.KakaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommonController {
    @Autowired
    KakaoService kakaoService;

    @GetMapping("/")
    public ModelAndView loginView(){
        ModelAndView mav = new ModelAndView();
        String kakaoUrl = KakaoRestApi.AUTH_HOST.getValue() + KakaoRestApi.AUTHORIZE_PATH.getValue() + ApiCall.createUrl(kakaoService.createAuthParamMap());
        mav.setViewName("thymeleaf/login");
        mav.addObject("kakaoUrl", kakaoUrl);

        return mav;
    }
}
