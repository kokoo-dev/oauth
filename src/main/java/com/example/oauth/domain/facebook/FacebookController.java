package com.example.oauth.domain.facebook;

import com.example.oauth.common.ApiCall;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/facebook")
public class FacebookController {
    Logger logger = LoggerFactory.getLogger(FacebookController.class);

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

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        String accessToken = (String)session.getAttribute("oauthToken");

        Map<String, String> map = new HashMap<>();
        map.put("input_token",accessToken);
        map.put("access_token",accessToken);

        String param = ApiCall.createUrl(map);
        JSONObject jsonObject = null;
        try {
            jsonObject = ApiCall.callGetApi("https://graph.facebook.com/debug_token", param);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject dataObject = (JSONObject)jsonObject.get("data");

        JSONObject deleteObject = null;

        try {
            deleteObject = ApiCall.callDeleteApi("https://graph.facebook.com/" + dataObject.get("user_id") + "/permissions","?access_token=" + accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.info(deleteObject.toString());

        return "";
    }
}
