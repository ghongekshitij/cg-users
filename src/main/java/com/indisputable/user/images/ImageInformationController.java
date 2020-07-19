package com.indisputable.user.images;

import com.indisputable.user.info.UserInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ImageInformationController {

    @Autowired
    private ImageInformationService imageInformationService;

    @PostMapping("/upload_image")
    public ResponseEntity<ImageInformation> uploadImageInformation(@RequestParam("file") MultipartFile file) {

        return new ResponseEntity<>(new ImageInformation(), HttpStatus.OK);
    }
}
