package cn.edu.sjtu.se.walknshot.apiserver.controllers;

import cn.edu.sjtu.se.walknshot.apiserver.SpringServer;
import cn.edu.sjtu.se.walknshot.csapi.serverapi.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthenticationController {
    @RequestMapping("/login")
    @ResponseBody
    public Object login(
            @RequestParam(value = "user", required = true) String user,
            @RequestParam(value = "password", required = true) String password
            ) {
        Authentication auth = SpringServer.serviceFactory.createAuthentication();
        return auth.login(user, password);
    }
}