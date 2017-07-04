package cn.edu.sjtu.se.walknshot.apiserver.services;

import cn.edu.sjtu.se.walknshot.apiserver.daos.TokenDAO;
import cn.edu.sjtu.se.walknshot.apiserver.daos.UserDAO;
import cn.edu.sjtu.se.walknshot.apiserver.entities.Token;
import cn.edu.sjtu.se.walknshot.apiserver.entities.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class Authentication extends ServiceBase {
    private TokenDAO tokenDAO;
    private UserDAO userDAO;

    public Authentication(TokenDAO tokenDAO, UserDAO userDAO) {
        this.tokenDAO = tokenDAO;
        this.userDAO = userDAO;
    }

    @Transactional
    public cn.edu.sjtu.se.walknshot.apimessages.Token login(String username, String password) {
        cn.edu.sjtu.se.walknshot.apimessages.Token mToken = new cn.edu.sjtu.se.walknshot.apimessages.Token();
        mToken.setUserId(0);

        User user = userDAO.getUserByName(username);
        if (user == null || !user.getPassword().equals(password))
            return mToken;

        Token token = new Token();
        token.setUserId(user.getId());
        String passphrase = user.getName() + Math.random();
        try {
            token.setPassphrase(DatatypeConverter.printHexBinary(MessageDigest.getInstance("SHA-256").digest(passphrase.getBytes())));
        } catch (NoSuchAlgorithmException e) {
            // impossible
        }
        tokenDAO.storeToken(token);

        mToken.setUserId(token.getUserId());
        mToken.setPassphrase(token.getPassphrase());
        return mToken;
    }
}
