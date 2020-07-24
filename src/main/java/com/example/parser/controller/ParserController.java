package com.example.parser.controller;

import com.example.parser.dto.FileUrl;
import com.example.parser.dto.ParsingResponse;
import com.example.parser.entity.Permit;
import com.example.parser.service.DataFromDBService;
import com.example.parser.service.StartParsingService;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParserController {

    private final StartParsingService startParsing;
    private final DataFromDBService dataFromDBService;

    public ParserController(StartParsingService startParsing, DataFromDBService dataFromDB) {
        this.startParsing = startParsing;
        this.dataFromDBService = dataFromDB;
    }

    @GetMapping(value = "/")
    public Page<Permit> dataPages(@RequestParam(defaultValue = "0") int page) {

        return dataFromDBService.listAll(page);
    }

    @SneakyThrows
    @PostMapping("/start")
    public ParsingResponse start(@RequestBody FileUrl fileUrl) {

        return startParsing.processData(fileUrl.getFileUrl());
    }


}

