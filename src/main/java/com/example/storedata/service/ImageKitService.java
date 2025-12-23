package com.example.storedata.service;

import com.example.storedata.model.ImageKitAuthResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class ImageKitService {

    @Value("${imagekit.private.key}")
    private String privateKey;

    public ImageKitAuthResponse generateAuthToken() throws Exception {

        String token = String.valueOf(System.currentTimeMillis());
        long expire = (System.currentTimeMillis() / 1000) + 2400; // 40 minutes

        System.out.println("ImageKit Token: " + token);
        System.out.println("ImageKit Expire: " + expire);

        String signature = generateSignature(token, expire);

        System.out.println("ImageKit Signature: " + signature);

        return new ImageKitAuthResponse(token, expire, signature);
    }

    private String generateSignature(String token, long expire) throws Exception {

        String dataToSign = token + expire;

        System.out.println("Data To Sign: " + dataToSign);

        Mac mac = Mac.getInstance("HmacSHA1");
        SecretKeySpec secretKey =
                new SecretKeySpec(privateKey.getBytes(StandardCharsets.UTF_8), "HmacSHA1");

        mac.init(secretKey);

        byte[] rawHmac = mac.doFinal(dataToSign.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(rawHmac);
    }
}
