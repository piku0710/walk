package cn.edu.sjtu.se.walknshot.apiserver.controllers;

import cn.edu.sjtu.se.walknshot.apiserver.services.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthenticationController {
    private Authentication auth;

    public AuthenticationController(Authentication auth) {
        this.auth = auth;
    }

    @RequestMapping("/login")
    @ResponseBody
    public Object login(
            @RequestParam(value = "user", required = true) String user,
            @RequestParam(value = "password", required = true) String password
            ) {
        return auth.login(user, password);
    }
}