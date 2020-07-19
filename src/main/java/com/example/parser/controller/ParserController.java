package com.example.parser.controller;

import com.example.parser.dto.FileUrl;
import com.example.parser.dto.ParsingResponse;
import com.example.parser.utils.StartParsing;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParserController {

    private final StartParsing startParsing;

    public ParserController(StartParsing startParsing) {
        this.startParsing = startParsing;
    }

    @SneakyThrows
    @PostMapping("/start")
    public ParsingResponse start(@RequestBody FileUrl fileUrl) {

        return startParsing.processData(fileUrl.getFileUrl());
    }
}

