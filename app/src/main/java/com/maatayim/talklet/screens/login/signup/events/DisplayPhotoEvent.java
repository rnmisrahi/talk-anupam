package com.maatayim.talklet.screens.login.signup.events;

import android.net.Uri;

/**
 * Created by Sophie on 5/25/2017.
 */

public class DisplayPhotoEvent {

    private Uri photo;

    public DisplayPhotoEvent(Uri photo){

        this.photo = photo;
    }


    public Uri getPhoto() {
        return photo;
    }
}
