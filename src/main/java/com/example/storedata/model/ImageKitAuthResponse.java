package com.example.storedata.model;

public class ImageKitAuthResponse {
    private String token;
    private long expire;
    private String signature;

    public ImageKitAuthResponse(String token, long expire, String signature) {
        this.token = token;
        this.expire = expire;
        this.signature = signature;
    }
    public ImageKitAuthResponse(){}

    // Getters and Setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public long getExpire() { return expire; }
    public void setExpire(long expire) { this.expire = expire; }

    public String getSignature() { return signature; }
    public void setSignature(String signature) { this.signature = signature; }
}
