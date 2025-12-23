package com.example.storedata.Controller;

import com.example.storedata.model.ImageKitAuthResponse;
import com.example.storedata.service.ImageKitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/imagekit")
public class ImageKitController {

    @Autowired
    private ImageKitService imageKitService;

    @GetMapping("/auth")
    public ImageKitAuthResponse getAuth() throws Exception {
        // Optional: check if user is teacher before returning token
        return imageKitService.generateAuthToken();
    }
}
