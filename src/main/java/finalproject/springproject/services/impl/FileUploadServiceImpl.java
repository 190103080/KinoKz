package finalproject.springproject.services.impl;

import finalproject.springproject.models.User;
import finalproject.springproject.services.FileUploadService;
import finalproject.springproject.services.UserService;
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
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private UserService userService;

    @Value("${loadURL}")
    private String myLoadURL;

    @Value("${uploadURL")
    private String uploadURL;

    @Override
    public boolean uploadAva(MultipartFile file) {

        try {

            if (file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png")) {
                User user = userService.getCurrentUser();

                String fileName = DigestUtils.sha1Hex(user.getId() + " avatar") + ".png";
                byte bytes[] = file.getBytes();
                Path path = Paths.get(uploadURL + fileName);
                Files.write(path, bytes);

                user.setAvatar(fileName);
                userService.saveUserData(user);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public byte[] getAvatar(String token) throws IOException {

        String picURL = myLoadURL + "user.png";
        if (token != null) {
            if (token.equals(userService.getCurrentUser().getAvatar()))
                picURL = myLoadURL + token;
        }

        InputStream in;

        try {
            ClassPathResource resource = new ClassPathResource(picURL);
            in = resource.getInputStream();
        } catch (Exception e) {
            picURL = myLoadURL + "user.png";
            ClassPathResource resource = new ClassPathResource(picURL);
            in = resource.getInputStream();
        }

        return IOUtils.toByteArray(in);
    }

}
