package cn.edu.sjtu.se.walknshot.apiserver.controllers;

import cn.edu.sjtu.se.walknshot.apiserver.services.Authentication;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        Authentication auth = new Authentication();
        return auth.login(user, password);
    }
}