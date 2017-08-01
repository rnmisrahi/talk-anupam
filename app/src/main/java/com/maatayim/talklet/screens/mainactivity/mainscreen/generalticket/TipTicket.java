package com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket;


import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sophie on 5/26/2017
 */

public class TipTicket implements Parcelable {

    private final String tip;
    private final boolean isAssertion;
    private String babyPhoto;
    private boolean isDisplay;

    public TipTicket(String tip, boolean isAssertion, String babyPhoto){

        this.tip = tip;
        this.isAssertion = isAssertion;
        this.babyPhoto = babyPhoto;
        this.isDisplay = true;
    }

    public TipTicket(Parcel in) {
        tip = in.readString();
        isAssertion = in.readByte() != 0;
    }

    public static final Creator<TipTicket> CREATOR = new Creator<TipTicket>() {
        @Override
        public TipTicket createFromParcel(Parcel in) {
            return new TipTicket(in);
        }

        @Override
        public TipTicket[] newArray(int size) {
            return new TipTicket[size];
        }
    };

    public String getTip() {
        return tip;
    }

    public boolean isAssertion() {
        return isAssertion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tip);
        dest.writeByte((byte) (isAssertion ? 1 : 0));
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
}
