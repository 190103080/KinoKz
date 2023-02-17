package finalproject.springproject.services.impl;

import finalproject.springproject.models.CinemaModel;
import finalproject.springproject.services.CinemaFileUploadService;
import finalproject.springproject.services.CinemaService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class CinemaFileUploadServiceImpl implements CinemaFileUploadService {

    @Autowired
    private CinemaService cinemaService;

    @Value("${loadURL}")
    private String myLoadURL;

    @Value("${uploadURL}")
    private String uploadURL;


    @Override
    public boolean uploadCinemaAvatar(MultipartFile file, CinemaModel cinemaModel) {

        try {
            if (file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png")) {

                String fileName = DigestUtils.sha1Hex(cinemaModel.getId() + " image") + ".png";
                byte bytes[] = file.getBytes();
                Path path = Paths.get(uploadURL + fileName);
                Files.write(path, bytes);

                cinemaModel.setImage(fileName);
                cinemaService.saveCinema(cinemaModel);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public byte[] getCinemaAvatar(String token) throws IOException {
        String picURL = myLoadURL + "image.png";
        if (token != null) {
            if (token.equals(cinemaService.getCurrentUser().getImage()))
                picURL = myLoadURL + token;
        }

        InputStream in;

        try {
            ClassPathResource resource = new ClassPathResource(picURL);
            in = resource.getInputStream();
        } catch (Exception e) {
            picURL = myLoadURL + "image.png";
            ClassPathResource resource = new ClassPathResource(picURL);
            in = resource.getInputStream();
        }

        return IOUtils.toByteArray(in);
    }
}
