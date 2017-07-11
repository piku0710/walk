package cn.edu.sjtu.se.walknshot.apiserver.daos;

import cn.edu.sjtu.se.walknshot.apiserver.entities.Storage;
import org.apache.commons.io.FileExistsException;
import org.apache.commons.io.FileUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Repository
public class StorageDAO {
    private String basePath;
    private SessionFactory sessionFactory;

    private final String tmpfilePrefix = "walknshot-storage-";
    private final int bufferSize = 4096;

    public StorageDAO(String basePath, SessionFactory sessionFactory) {
        this.basePath = basePath;
        this.sessionFactory = sessionFactory;
    }

    public String storeFile(String collection, InputStream istream) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }

        File tmpFile;
        FileOutputStream ostream;
        try {
            tmpFile = File.createTempFile(tmpfilePrefix, "");
            ostream = new FileOutputStream(tmpFile);
        } catch (IOException e) {
            return null;
        }

        byte[] buffer = new byte[bufferSize];
        while (true) {
            int rsize = 0;
            try {
                rsize = istream.read(buffer);
                if (rsize == -1)
                    break;
                ostream.write(buffer, 0, rsize);
                digest.update(buffer, 0, rsize);
            } catch (IOException e) {
                return null;
            }
        }

        String filename = DatatypeConverter.printHexBinary(digest.digest()).toLowerCase();

        // test whether the file exists
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Storage s WHERE s.filename = :filename");
        query.setParameter("filename", filename);
        if (!query.list().isEmpty())
            return filename;

        String fullPath = basePath + "/" + collection + "/" + filename;
        fullPath.replace("/", File.separator);

        try {
            ostream.close();
            File newFile = new File(fullPath);
            FileUtils.moveFile(tmpFile, newFile);
        } catch (IOException e) {
            return null;
        }

        Storage storage = new Storage();
        storage.setFilename(filename);
        session.save(storage);
        return filename;
    }
}
