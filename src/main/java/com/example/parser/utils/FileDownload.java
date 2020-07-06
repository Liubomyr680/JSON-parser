package com.example.parser.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileDownload {

    private File tmp;
    private String fileUrl;
    private byte[] buffer;
    private int read = 0;
    private static final Logger log = LoggerFactory.getLogger(FileDownload.class);

    public FileDownload(String fileUrl) throws IOException {
        buffer = new byte[1024];
        tmp = File.createTempFile("dozvoli-na-vikidi-onovlenii", ".tmp", (new File("/Users/kristina/Downloads/")));
//        fileUrl = "https://data.gov.ua/dataset/94409436-a198-4b9d-9738-844405c5df94/resource/4ca1d5a9-543f-42a5-bc8c-d6fe5650be40/download/dozvoli-na-vikidi-onovlenii.json";
        this.fileUrl = fileUrl;

    }

    public File getTmp() {
        return tmp;
    }

    public void download() throws IOException {
        URL url = new URL(fileUrl);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        BufferedInputStream in = new BufferedInputStream(http.getInputStream());
        FileOutputStream fos = new FileOutputStream(tmp);
        BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);

        while((read =in.read(buffer,0,1024))>=0)
            bout.write(buffer,0,read);

        bout.close();
        in.close();
        log.info("download complete");
        tmp.deleteOnExit();
    }
}
