package com.maatayim.talklet.repository.retrofit.model.user;

/**
 * Created by Sophie on 7/4/2017
 */

public class LoginFacebookRequest {
    private String facebookId;

    public LoginFacebookRequest() {
    }

    public LoginFacebookRequest(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }
}
