package com.maatayim.talklet.screens.loginactivity.signup.events;

import android.net.Uri;

/**
 * Created by Sophie on 5/25/2017
 */

public class DisplayPhotoEvent {

    private Uri photo;
    private String photoUrl;

    public DisplayPhotoEvent(Uri photo, String photoUrl){

        this.photo = photo;
        this.photoUrl = photoUrl;
    }


    public Uri getPhoto() {
        return photo;
    }

    public void setPhoto(Uri photo) {
        this.photo = photo;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
