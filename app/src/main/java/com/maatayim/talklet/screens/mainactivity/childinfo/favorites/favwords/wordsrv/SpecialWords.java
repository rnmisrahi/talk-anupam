package com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords.wordsrv;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Sophie on 6/25/2017.
 */

public class SpecialWords implements Parcelable {

    private final String wordsSection;
    private final String word;
    private final int frequency;
    private final int age; // in month
    private final int percentUsage;
    private final List<String> adviceList;

    public SpecialWords(String wordsSection, String word, int frequency, int age, int percentUsage,
                        List<String> adviceList){

        this.wordsSection = wordsSection;
        this.word = word;
        this.frequency = frequency;
        this.age = age;
        this.percentUsage = percentUsage;
        this.adviceList = adviceList;
    }


    protected SpecialWords(Parcel in) {
        wordsSection = in.readString();
        word = in.readString();
        frequency = in.readInt();
        age = in.readInt();
        percentUsage = in.readInt();
        adviceList = in.createStringArrayList();
    }

    public static final Creator<SpecialWords> CREATOR = new Creator<SpecialWords>() {
        @Override
        public SpecialWords createFromParcel(Parcel in) {
            return new SpecialWords(in);
        }

        @Override
        public SpecialWords[] newArray(int size) {
            return new SpecialWords[size];
        }
    };

    public String getWordsSection() {
        return wordsSection;
    }

    public String getWord() {
        return word;
    }

    public int getFrequency() {
        return frequency;
    }

    public int getAge() {
        return age;
    }


    public int getPercentUsage() {
        return percentUsage;
    }

    public List<String> getAdviceList() {
        return adviceList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(wordsSection);
        dest.writeString(word);
        dest.writeInt(frequency);
        dest.writeInt(age);
        dest.writeInt(percentUsage);
        dest.writeStringList(adviceList);
    }
}
