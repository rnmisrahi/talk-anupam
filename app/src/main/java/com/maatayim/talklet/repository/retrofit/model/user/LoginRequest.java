package com.maatayim.talklet.repository.retrofit.model.user;

/**
 * Created by Sophie on 7/4/2017.
 */

public class LoginRequest {
    private String facebookId;

    public LoginRequest() {
    }

    public LoginRequest(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }
}
