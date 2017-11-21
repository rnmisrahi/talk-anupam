package com.maatayim.talklet.screens.mainactivity.record;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Omri on 11/13/2017
 */
public class FTPData {
    private String userName;
    private String password;
    private String host;
    private String port;
    private String remotePath;
    private String fileName;
    private URL ftpUrl;

    private FTPData(String userName, String password, String host, String port,
                    String remotePath, String fileName) {
        this.userName = userName;
        this.password = password;
        this.host = host;
        this.port = port;
        this.remotePath = remotePath;
        this.fileName = fileName;
    }

    public static FTPData create(String userName, String password, String host, String port,
                                 String remotePath, String fileName)
            throws MalformedURLException {
        FTPData ftpData = new FTPData(userName, password, host, port, remotePath, fileName);
        ftpData.generateFtpUrl();
        return ftpData;
    }

    private void generateFtpUrl() throws MalformedURLException {
        String ftpUrlTemplate = "ftp://%s:%s@%s:%s%s/%s;type=i";
        String url = String.format(ftpUrlTemplate, userName, password, host, port, remotePath, fileName);
        ftpUrl = new URL(url);
    }

    public URL getFtpUrl() {
        return ftpUrl;
    }
}
