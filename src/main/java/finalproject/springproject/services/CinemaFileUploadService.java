package finalproject.springproject.services;

import finalproject.springproject.models.CinemaModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CinemaFileUploadService {

    boolean uploadCinemaAvatar(MultipartFile file, CinemaModel cinemaModel);
    byte[] getCinemaAvatar(String token) throws IOException;

}
