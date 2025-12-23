package com.example.storedata.service;

import com.example.storedata.model.ImageKitAuthResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
public class ImageKitService {

    @Value("${imagekit.private.key}")
    private String privateKey;

    public ImageKitAuthResponse generateAuthToken() throws Exception {
        String token = String.valueOf(System.currentTimeMillis());
        long expire = (System.currentTimeMillis() / 1000) + 2400; // 40 minutes

        String signature = generateSignature(token);

        return new ImageKitAuthResponse(token, expire, signature);
    }

    private String generateSignature(String token) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA1");
        SecretKeySpec keySpec = new SecretKeySpec(privateKey.getBytes(), "HmacSHA1");
        mac.init(keySpec);

        byte[] rawHmac = mac.doFinal(token.getBytes());
        return Base64.getEncoder().encodeToString(rawHmac);
    }
}
