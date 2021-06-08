package com.example.oauth.domain.facebook;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@Controller
@RequestMapping("/facebook")
public class FacebookController {
    @Autowired
    FacebookService facebookService;

    @GetMapping("/oauth")
    public ModelAndView oauth(FacebookToken facebookToken, HttpSession session){
        JSONObject tokenObject = facebookService.getToken(facebookToken.getCode());

        ModelAndView mav = new ModelAndView();
        mav.setViewName("thymeleaf/loginAfter");
        mav.addObject("token", tokenObject);
        mav.addObject("path", "facebook");
        session.setAttribute("oauthToken", tokenObject.get("access_token"));

        return mav;
    }
}
