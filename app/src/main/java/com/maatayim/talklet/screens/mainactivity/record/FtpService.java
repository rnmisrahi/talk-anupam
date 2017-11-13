package com.maatayim.talklet.screens.mainactivity.record;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.maatayim.talklet.screens.mainactivity.record.RecordPresenter.FILE_PATH;

/**
 * Created by Omri on 11/13/2017
 */

public class FtpService extends IntentService {

    public static final int EOF = -1;

    public FtpService() {
        super("FtpService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        checkNotNull(intent, "intent must not be null");
        Bundle extras = checkNotNull(intent.getExtras(), "intent must have extras");
        String path = extras.getString(FILE_PATH);
        FTPData ftpData = null;
        try {
            ftpData = generateFtpData(path);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        uploadFile(path, ftpData);
        deleteFile("");  // todo delete the file after upload
    }

    void uploadFile(String filePath, FTPData ftpData) {
        final int BUFFER_SIZE = 1024;
        try {
            URL url = ftpData.getFtpUrl();
            URLConnection conn = url.openConnection();
            OutputStream outputStream = conn.getOutputStream();
            FileInputStream inputStream = new FileInputStream(filePath);

            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != EOF) {
                outputStream.write(buffer, 0, bytesRead);
            }

            inputStream.close();
            outputStream.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private FTPData generateFtpData(String filePath) throws MalformedURLException {
        final String USER_NAME = "pythondemoapp\\talklet-python-demo";
        final String PASSWORD = "T@lkl3t123";
        final String HOST = "waws-prod-dm1-017.ftp.azurewebsites.windows.net";
        final String PORT = "990";
        final String UPLOAD_PATH = "/TalkletDemo/Audio";
        String fileName = getFileNameFromPath(filePath);
        return FTPData.create(USER_NAME, PASSWORD, HOST, PORT, UPLOAD_PATH, fileName);
    }

    @NonNull
    private String getFileNameFromPath(String filePath) {
        File file = new File(filePath);
        return file.getName();
    }

}
