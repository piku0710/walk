package cn.edu.sjtu.se.walknshot.apiserver.services;

import cn.edu.sjtu.se.walknshot.apiserver.daos.PictureDAO;
import cn.edu.sjtu.se.walknshot.apiserver.daos.StorageDAO;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class PictureService {
    private PictureDAO pictureDAO;
    private StorageDAO storageDAO;

    public PictureService(PictureDAO pictureDAO, StorageDAO storageDAO) {
        this.pictureDAO = pictureDAO;
        this.storageDAO = storageDAO;
    }

    public boolean storePicture(int userId, InputStream stream) {
        return false;
    }
}
