package com.example.parser.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileDownload {

    private static File tmpFile = null;
    private static final Logger log = LoggerFactory.getLogger(FileDownload.class);

    public static File download(String fileUrl) {

        BufferedInputStream in = null;
        BufferedOutputStream bout = null;
        byte[] buffer = new byte[1024];

        try {
            tmpFile = File.createTempFile("dozvoli-na-vikidi-onovlenii", ".tmp");
            URL url = new URL(fileUrl);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(http.getInputStream());
            FileOutputStream fos = new FileOutputStream(tmpFile);
            bout = new BufferedOutputStream(fos, 1024);

            int read;
            while((read = in.read(buffer,0,1024))>=0)
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
        tmpFile.deleteOnExit();

        return tmpFile;
    }
}
