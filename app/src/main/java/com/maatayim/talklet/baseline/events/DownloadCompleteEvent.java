package com.maatayim.talklet.baseline.events;

/**
 * Created by Sophie on 7/9/2017
 */

public class DownloadCompleteEvent {

    private boolean isDownloadComplete;
    private static int downloadCompletedNum = 0;

    public DownloadCompleteEvent(boolean isDownloadComplete){

        this.isDownloadComplete = isDownloadComplete;
        downloadCompletedNum++;
    }

    public boolean isDownloadComplete() {
        return isDownloadComplete;
    }

    public void setDownloadComplete(boolean downloadComplete) {
        isDownloadComplete = downloadComplete;
    }

    public static int getDownloadCompletedNum() {
        return downloadCompletedNum;
    }
}
