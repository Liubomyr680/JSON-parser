package com.example.parser.controller;


import com.example.parser.service.PermitService;
import com.example.parser.utils.FileDownload;
import com.example.parser.utils.Parser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Controller
public class ParserController {

    private String fileUrl = "https://data.gov.ua/dataset/94409436-a198-4b9d-9738-844405c5df94/resource/4ca1d5a9-543f-42a5-bc8c-d6fe5650be40/download/dozvoli-na-vikidi-onovlenii.json";
    private PermitService permitService;
    private Parser parser;
    private static final Logger log = LoggerFactory.getLogger(Parser.class);

    public ParserController(PermitService permitService) throws IOException {
        this.permitService = permitService;
        parser = new Parser();
    }

    @GetMapping("/hi")
    public String hi(){
        return "Start";
    }

    @PostMapping("/start")
    public String startParsing() throws IOException, ParseException {

        parser.parser();

        return parser.getStr();
    }
}
