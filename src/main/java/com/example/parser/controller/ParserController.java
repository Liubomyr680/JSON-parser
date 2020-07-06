package com.example.parser.controller;


import com.example.parser.service.PermitService;
import com.example.parser.utils.Parser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class ParserController {

    private String fileUrl = "https://data.gov.ua/dataset/94409436-a198-4b9d-9738-844405c5df94/resource/4ca1d5a9-543f-42a5-bc8c-d6fe5650be40/download/dozvoli-na-vikidi-onovlenii.json";
    private PermitService permitService;
    private static final Logger log = LoggerFactory.getLogger(Parser.class);

    public ParserController(PermitService permitService) throws IOException {
        this.permitService = permitService;
    }

    @GetMapping("/start")
    public JSONObject start() throws IOException, ParseException {

       return permitService.startParsing(fileUrl);
    }
}
