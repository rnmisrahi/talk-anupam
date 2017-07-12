package com.maatayim.talklet.baseline.events;

/**
 * Created by Sophie on 7/9/2017.
 */

public class DowmloadCompleteEvent {

    private boolean isDownloadComplete;

    public DowmloadCompleteEvent(boolean isDownloadComplete){

        this.isDownloadComplete = isDownloadComplete;
    }

    public boolean isDownloadComplete() {
        return isDownloadComplete;
    }

    public void setDownloadComplete(boolean downloadComplete) {
        isDownloadComplete = downloadComplete;
    }
}
