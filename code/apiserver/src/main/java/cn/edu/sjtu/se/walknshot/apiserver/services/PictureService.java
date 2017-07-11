package cn.edu.sjtu.se.walknshot.apiserver.services;

import cn.edu.sjtu.se.walknshot.apiserver.daos.PictureDAO;
import cn.edu.sjtu.se.walknshot.apiserver.daos.StorageDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;

@Service
public class PictureService {
    private PictureDAO pictureDAO;
    private StorageDAO storageDAO;

    private final String storageCollection = "picture";

    public PictureService(PictureDAO pictureDAO, StorageDAO storageDAO) {
        this.pictureDAO = pictureDAO;
        this.storageDAO = storageDAO;
    }

    @Transactional
    public String storePicture(int userId, InputStream stream) {
        return storageDAO.storeFile(storageCollection, stream);
    }
}
