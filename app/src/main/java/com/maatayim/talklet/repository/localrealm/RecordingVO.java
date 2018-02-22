package com.maatayim.talklet.repository.localrealm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by rbpatel on 11/10/2017.
 */

public class RecordingVO extends RealmObject {
    @PrimaryKey
    private int id;

    private String path;
    private long createdAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public boolean getIsUploaded() {
        return isUploaded;
    }

    public void setIsUploaded(boolean isUploaded) {
        this.isUploaded = isUploaded;
    }

    private boolean isUploaded;
}
