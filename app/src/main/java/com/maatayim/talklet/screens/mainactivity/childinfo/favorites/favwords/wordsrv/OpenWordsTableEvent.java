package com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords.wordsrv;

import com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords.CustomWordSection;

/**
 * Created by Sophie on 6/25/2017.
 */

public class OpenWordsTableEvent {

    private String wordsType;
    private CustomWordSection customView;

    public OpenWordsTableEvent(String wordsType, CustomWordSection customView){

        this.wordsType = wordsType;
        this.customView = customView;
    }


    public String getWordsType() {
        return wordsType;
    }

    public void setWordsType(String wordsType) {
        this.wordsType = wordsType;
    }


    public CustomWordSection getCustomView() {
        return customView;
    }

    public void setCustomView(CustomWordSection customView) {
        this.customView = customView;
    }
}
