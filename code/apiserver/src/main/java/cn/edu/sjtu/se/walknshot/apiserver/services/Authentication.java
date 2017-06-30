package cn.edu.sjtu.se.walknshot.apiserver.services;

import cn.edu.sjtu.se.walknshot.apiserver.entities.Token;
import cn.edu.sjtu.se.walknshot.apiserver.entities.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class Authentication extends ServiceBase {
    public cn.edu.sjtu.se.walknshot.apimessages.Token login(String username, String password) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM User u WHERE u.name = :user AND u.password = :password");
        query.setParameter("user", username);
        query.setParameter("password", password);
        List entries = query.list();

        cn.edu.sjtu.se.walknshot.apimessages.Token mToken = new cn.edu.sjtu.se.walknshot.apimessages.Token();
        mToken.setUserId(0);
        if (entries.isEmpty())
            return mToken;

        User user = (User) entries.get(0);
        Token token = new Token();
        token.setUserId(user.getId());
        String passphrase = user.getName() + Math.random();
        try {
            token.setPassphrase(DatatypeConverter.printHexBinary(MessageDigest.getInstance("SHA-256").digest(passphrase.getBytes())));
        } catch (NoSuchAlgorithmException e) {
            // impossible
        }
        session.save(token);

        mToken.setUserId(token.getUserId());
        mToken.setPassphrase(token.getPassphrase());
        return mToken;
    }
}
