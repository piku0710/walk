package cn.edu.sjtu.se.walknshot.apiserver.services;

import cn.edu.sjtu.se.walknshot.apimessages.Token;

public class Authentication {
    public Token login(String user, String password) {
        Token token = new Token();
        token.setUserId(42);
        token.setPassphrase("haha");
        return token;
    }
}
