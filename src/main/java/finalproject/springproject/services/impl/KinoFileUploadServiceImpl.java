package finalproject.springproject.services.impl;

import finalproject.springproject.models.Kinoteatry;
import finalproject.springproject.services.KinoFileUploadService;
import finalproject.springproject.services.KinoService;
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
public class KinoFileUploadServiceImpl implements KinoFileUploadService {

    @Autowired
    private KinoService kinoService;

    @Value("${loadkiURL}")
    private String myLoadkiURL;

    @Value("${uploadkiURL}")
    private String uploadkiURL;

    @Override
    public boolean uploadKinoteatryAvatar(MultipartFile file, Kinoteatry kinoteatry) {
        try {
            if (file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png")) {

                String fileName = DigestUtils.sha1Hex(kinoteatry.getId() + " image") + ".png";
                byte bytes[] = file.getBytes();
                Path path = Paths.get(uploadkiURL + fileName);
                Files.write(path, bytes);

                kinoteatry.setKiImage(fileName);
                kinoService.saveKinoteatry(kinoteatry);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public byte[] getKinoteatryAvatar(String token) throws IOException {
        String picURL = myLoadkiURL + "image.png";
        if (token != null) {
            if (token.equals(kinoService.getCurrentUser().getKiImage()))
                picURL = myLoadkiURL + token;
        }

        InputStream in;

        try {
            ClassPathResource resource = new ClassPathResource(picURL);
            in = resource.getInputStream();
        } catch (Exception e) {
            picURL = myLoadkiURL + "image.png";
            ClassPathResource resource = new ClassPathResource(picURL);
            in = resource.getInputStream();
        }

        return IOUtils.toByteArray(in);
    }

}
