package com.example.parser.controller;


import com.example.parser.dto.FileUrl;
import com.example.parser.dto.ResponseFromParsing;
import com.example.parser.service.PermitService;
import com.example.parser.utils.Parser;
import lombok.SneakyThrows;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class ParserController {

    private PermitService permitService;
    private static final Logger log = LoggerFactory.getLogger(Parser.class);

    public ParserController(PermitService permitService) {
        this.permitService = permitService;
    }

    @SneakyThrows
    @PostMapping("/start")
    public ResponseFromParsing start(@RequestBody FileUrl fileUrl) {

        return permitService.startParsing(fileUrl.getFileUrl());
    }
}

