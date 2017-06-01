package com.maatayim.talklet.repository.retrofit.model.general;

import java.util.List;

/**
 * Created by Sophie on 5/28/2017.
 */

public class CreateRecordingRequest {

    private List<String> children;
    private long date;
    private String file;

    public CreateRecordingRequest(List<String> children, long date, String file){

        this.children = children;
        this.date = date;
        this.file = file;
    }


    public List<String> getChildren() {
        return children;
    }

    public void setChildren(List<String> children) {
        this.children = children;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
