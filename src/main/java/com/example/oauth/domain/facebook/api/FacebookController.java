package com.example.oauth.domain.facebook.api;

import com.example.oauth.domain.facebook.application.FacebookServiceImpl;
import com.example.oauth.domain.facebook.dto.FacebookToken;
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
@RequestMapping("/facebook")
public class FacebookController {
    Logger logger = LoggerFactory.getLogger(FacebookController.class);

    @Autowired
    FacebookServiceImpl facebookServiceImpl;

    @GetMapping("/oauth")
    public ModelAndView oauth(FacebookToken facebookToken, HttpSession session){
        JSONObject tokenObject = facebookServiceImpl.getToken(facebookToken.getCode());

        ModelAndView mav = ControllerUtil.getLoginAfterMav("facebook", tokenObject, session);

        return mav;
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        String accessToken = (String)session.getAttribute("oauthToken");

        JSONObject deleteObject = facebookServiceImpl.logout(accessToken);
        Boolean isSuccess = (Boolean) deleteObject.get("success");

        if(isSuccess != null) {
            if (isSuccess) {
                ControllerUtil.removeSession(session, "oauthToken");
            }
        }

        return "redirect:/";
    }

}
