package cn.edu.sjtu.se.walknshot.apiserver.daos;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class PictureDAO {
    private SessionFactory sessionFactory;

    public PictureDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
