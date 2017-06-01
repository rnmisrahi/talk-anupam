package com.maatayim.talklet.screens.login.signup.events;

import android.content.Intent;

/**
 * Created by Sophie on 6/1/2017.
 */

public class FacebookLoginActivityResultEvent {

    private final int requestCode;
    private final int resultCode;
    private final Intent data;

    public FacebookLoginActivityResultEvent(int requestCode, int resultCode, Intent data){

        this.requestCode = requestCode;
        this.resultCode = resultCode;
        this.data = data;
    }


    public int getRequestCode() {
        return requestCode;
    }

    public int getResultCode() {
        return resultCode;
    }

    public Intent getData() {
        return data;
    }
}
