package com.maatayim.talklet.screens.mainactivity.record;

import android.media.MediaRecorder;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.File;
import java.io.FileDescriptor;

/**
 * Created by Sophie on 7/2/2017
 */

public class MediaRecordWrapper implements Parcelable {

    private MediaRecorder mediaRecord;
//    private FileDescriptor fileSavePath;

    public MediaRecordWrapper(MediaRecorder mediaRecord){

        this.mediaRecord = mediaRecord;
//        this.fileSavePath = fileSavePath;
    }



//    public FileDescriptor getFileSavePath() {
//        return fileSavePath;
//    }
//
//    public void setFileSavePath(FileDescriptor fileSavePath) {
//        this.fileSavePath = fileSavePath;
//    }

//    public MediaRecorder getMediaRecord() {
//        return mediaRecord;
//    }

//    public void setMediaRecord(MediaRecorder mediaRecord) {
//        this.mediaRecord = mediaRecord;
//    }

    public void stop(){
        this.mediaRecord.stop();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
