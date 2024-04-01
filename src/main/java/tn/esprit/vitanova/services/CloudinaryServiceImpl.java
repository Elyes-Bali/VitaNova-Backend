package tn.esprit.vitanova.services;

import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class CloudinaryServiceImpl {

    private static final String FOLDER_CONST = "folder";
    private static final String SECURE_URL_CONST = "secure_url";
    private final Cloudinary cloudinary;

    public String upload(MultipartFile multipartFile) throws Exception {
        try {
            BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
            if (bi == null) {
                throw new Exception("Invalid image");
            }
            Map<String, String> optionsMap = new HashMap<>();
            File file = convert(multipartFile);
            Map result = cloudinary.uploader().upload(file, optionsMap);
            file.delete();
            return result.get(SECURE_URL_CONST).toString();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    public File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }


}
