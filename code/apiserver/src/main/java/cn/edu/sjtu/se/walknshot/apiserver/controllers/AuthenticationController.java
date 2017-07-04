package cn.edu.sjtu.se.walknshot.apiserver.controllers;

import cn.edu.sjtu.se.walknshot.apimessages.Token;
import cn.edu.sjtu.se.walknshot.apiserver.services.AuthenticationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthenticationController {
    private AuthenticationService auth;

    public AuthenticationController(AuthenticationService auth) {
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

    @RequestMapping("/validate/token")
    @ResponseBody
    public Object auth(
            @RequestParam(value = "token", required = true) String sToken
            ) {
        return auth.validateToken(Token.fromString(sToken));
    }
}