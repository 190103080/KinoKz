package finalproject.springproject.services;

import finalproject.springproject.models.CinemaModel;
import finalproject.springproject.models.Kinoteatry;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface KinoFileUploadService {

    boolean uploadKinoteatryAvatar(MultipartFile file, Kinoteatry kinoteatry);
    byte[] getKinoteatryAvatar(String token) throws IOException;

}
