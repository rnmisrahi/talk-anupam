package com.maatayim.talklet.screens.loginactivity.signup.choosephoto;

import android.content.Intent;

/**
 * Created by Sophie on 5/25/2017.
 */

public class ActivityResultEvent {

    private final int requestCode;
    private final int resultCode;
    private final Intent data;

    public ActivityResultEvent(int requestCode, int resultCode, Intent data){

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
