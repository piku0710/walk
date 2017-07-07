package cn.edu.sjtu.se.walknshot.apiserver.daos;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class StorageDAO {
    private String basePath;
    private SessionFactory sessionFactory;

    public StorageDAO(String basePath, SessionFactory sessionFactory) {
        this.basePath = basePath;
        this.sessionFactory = sessionFactory;
    }
}
