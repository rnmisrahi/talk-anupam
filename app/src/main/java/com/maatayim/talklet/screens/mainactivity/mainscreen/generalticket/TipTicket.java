package com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sophie on 5/26/2017
 */

public class TipTicket implements Parcelable {

    private final String tip;
    private final String tipType;
    private String babyPhoto;
    private boolean isDisplay;

    public TipTicket(String tip, String tipType, String babyPhoto){

        this.tip = tip;
        this.tipType = tipType;
        this.babyPhoto = babyPhoto;
        this.isDisplay = true;
    }


    public String getTip() {
        return tip;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }


    public String getBabyPhoto() {
        return babyPhoto;
    }

    public void setBabyPhoto(String babyPhoto) {
        this.babyPhoto = babyPhoto;
    }

    public boolean isDisplay() {
        return isDisplay;
    }

    public void setDisplay(boolean display) {
        isDisplay = display;
    }

    public String getTipType() {
        return tipType;
    }
}
