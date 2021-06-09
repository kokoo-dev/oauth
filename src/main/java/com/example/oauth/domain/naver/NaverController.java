package com.example.oauth.domain.naver;

import com.example.oauth.common.ControllerUtil;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@Controller
@RequestMapping("/naver")
public class NaverController {
    @Autowired
    NaverService naverService;

    @GetMapping("/oauth")
    public ModelAndView oauth(NaverToken naverToken, HttpSession session){
        JSONObject tokenObject = naverService.getToken(naverToken.getCode());

        ModelAndView mav = ControllerUtil.getLoginAfterMav("naver", tokenObject, session);

        return mav;
    }

    @PostMapping("/logout")
    public String logout(NaverToken naverToken, HttpSession session) {
        String access_token = (String)session.getAttribute("oauthToken");
        JSONObject logoutObject = naverService.logout(access_token);

        if(access_token != null){
            session.removeAttribute("oauthToken");
        }

        return "redirect:/";
    }
}
