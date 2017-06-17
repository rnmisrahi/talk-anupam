package com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sophie on 5/26/2017.
 */

public class GeneralTipTicket implements Parcelable {

    private final String tip;
    private final boolean isAssertion;

    public GeneralTipTicket(String tip, boolean isAssertion){

        this.tip = tip;
        this.isAssertion = isAssertion;
    }

    protected GeneralTipTicket(Parcel in) {
        tip = in.readString();
        isAssertion = in.readByte() != 0;
    }

    public static final Creator<GeneralTipTicket> CREATOR = new Creator<GeneralTipTicket>() {
        @Override
        public GeneralTipTicket createFromParcel(Parcel in) {
            return new GeneralTipTicket(in);
        }

        @Override
        public GeneralTipTicket[] newArray(int size) {
            return new GeneralTipTicket[size];
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
}
