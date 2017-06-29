package cn.edu.sjtu.se.walknshot.services.impl;

import cn.edu.sjtu.se.walknshot.csapi.serverapi.Authentication;

public class AuthenticationImpl implements Authentication {
    @Override
    public Integer login(String user, String password) {
        return 42;
    }
}
