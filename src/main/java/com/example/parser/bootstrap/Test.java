package com.example.parser.bootstrap;

import com.example.parser.repository.PermitRepository;
import com.example.parser.utils.Parser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@Component
public class Test implements CommandLineRunner  {

    private static final String filePath = "/Users/kristina/Downloads/dozvoli-na-vikidi-onovlenii.tmp";
    private String fileUrl = "https://data.gov.ua/dataset/94409436-a198-4b9d-9738-844405c5df94/resource/4ca1d5a9-543f-42a5-bc8c-d6fe5650be40/download/dozvoli-na-vikidi-onovlenii.json";
    private final PermitRepository permitRepository;

    public Test(PermitRepository permitRepository) {
        this.permitRepository = permitRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Parser parser = new Parser(permitRepository);
        parser.parser();



    }
}


