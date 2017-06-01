package com.maatayim.talklet.repository.retrofit.model.recording;

/**
 * Created by Sophie on 5/28/2017.
 */

public class RecordingMetadata {

    private long time;
    private String type;
    private String childid;

    public RecordingMetadata(long time, String type, String childid){

        this.time = time;
        this.type = type;
        this.childid = childid;
    }


    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChildid() {
        return childid;
    }

    public void setChildid(String childid) {
        this.childid = childid;
    }
}
