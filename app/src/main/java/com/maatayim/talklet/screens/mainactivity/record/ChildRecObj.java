package com.maatayim.talklet.screens.mainactivity.record;

import com.maatayim.talklet.screens.mainactivity.mainscreen.MainScreenChild;

import java.util.List;

/**
 * Created by Sophie on 7/5/2017
 */

public class ChildRecObj extends MainScreenChild {

    private boolean isSelected;

    public ChildRecObj(String id, String url, int wordCount, int total, List<Tip> tips) {
        super(id, url, wordCount, total, tips);
        this.isSelected = false;

    }

    public ChildRecObj(MainScreenChild child){
        super(child.getId(), child.getUrl(), child.getWordCount(), child.getTotal(), child.getTips());
        this.isSelected = false;
    }


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
