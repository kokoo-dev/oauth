package com.example.oauth.domain.google;

import com.example.oauth.domain.kakao.KakaoController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Controller
@RequestMapping("/google")
public class GoogleController {
    Logger logger = LoggerFactory.getLogger(GoogleController.class);

    @GetMapping("/oauth")
    public void oauth(GoogleToken googleToken, HttpServletRequest request){
        Enumeration<String> em = request.getParameterNames();
        while(em.hasMoreElements()){
            System.out.println(em.nextElement());
        }

        logger.info(googleToken.getCode());
        logger.info(googleToken.getScope());
        logger.info(googleToken.getAuthuser());
        logger.info(googleToken.getPrompt());
    }
}
