package com.maatayim.talklet.repository.retrofit.model.recording;

import com.maatayim.talklet.repository.retrofit.model.general.Tip;

/**
 * Created by Sophie on 5/28/2017.
 */

public class RecordingNotificationResponse {

    private Tip tip;
    private boolean usedTodaysWord;

    public RecordingNotificationResponse(Tip tip, boolean usedTodaysWord){

        this.tip = tip;
        this.usedTodaysWord = usedTodaysWord;
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public boolean isUsedTodaysWord() {
        return usedTodaysWord;
    }

    public void setUsedTodaysWord(boolean usedTodaysWord) {
        this.usedTodaysWord = usedTodaysWord;
    }
}
