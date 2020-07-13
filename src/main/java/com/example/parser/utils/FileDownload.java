package com.example.parser.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FileDownload {

    private File tmp;
    private String fileUrl;
    private byte[] buffer;
    private int read = 0;
    private static final Logger log = LoggerFactory.getLogger(FileDownload.class);

    public FileDownload(String fileUrl) throws IOException {
        buffer = new byte[1024];
        tmp = File.createTempFile("dozvoli-na-vikidi-onovlenii", ".tmp");
        this.fileUrl = fileUrl;
    }

    public File getTmp() {
        return tmp;
    }

    public void download() {

        BufferedInputStream in = null;
        BufferedOutputStream bout = null;
        try {
            URL url = new URL(fileUrl);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(http.getInputStream());
            FileOutputStream fos = new FileOutputStream(tmp);
            bout = new BufferedOutputStream(fos, 1024);

            while((read =in.read(buffer,0,1024))>=0)
                bout.write(buffer,0,read);

        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            try {
                if (bout != null) {
                    bout.close();
                    in.close();
                }
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }

        log.info("download complete");
        tmp.deleteOnExit();
    }
}
