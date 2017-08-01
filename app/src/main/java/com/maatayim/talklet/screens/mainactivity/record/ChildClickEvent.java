package com.maatayim.talklet.screens.mainactivity.record;

/**
 * Created by Sophie on 8/1/2017
 */

public class ChildClickEvent {

    private boolean isClickedChildSetChanged = false;

    public ChildClickEvent(boolean isClickedChildSetChanged){

        this.isClickedChildSetChanged = isClickedChildSetChanged;
    }


    public boolean isClickedChildSetChanged() {
        return isClickedChildSetChanged;
    }

    public void setClickedChildSetChanged(boolean clickedChildSetChanged) {
        isClickedChildSetChanged = clickedChildSetChanged;
    }
}
