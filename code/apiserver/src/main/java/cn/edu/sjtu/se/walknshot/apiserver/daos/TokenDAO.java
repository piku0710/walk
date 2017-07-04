package cn.edu.sjtu.se.walknshot.apiserver.daos;

import cn.edu.sjtu.se.walknshot.apiserver.entities.Token;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class TokenDAO {
    private SessionFactory sessionFactory;

    public TokenDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void storeToken(Token token) {
        sessionFactory.getCurrentSession().saveOrUpdate(token);
    }
}
