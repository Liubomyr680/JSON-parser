package com.example.parser.controller;

import com.example.parser.dto.FileUrl;
import com.example.parser.dto.ParsingResponse;
import com.example.parser.entity.Permit;
import com.example.parser.service.DataFromDBService;
import com.example.parser.service.StartParsingService;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public Page<Permit> dataPages(
            @RequestParam(defaultValue = "0") int page,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC)Pageable pageable) {

        return dataFromDBService.listAll(pageable);
    }

    @GetMapping(value = "/findById")
    public Page<Permit> idDataPages(Pageable pageable,
            @RequestParam(required = false, defaultValue = "") int id) {
        return dataFromDBService.findById(pageable, id);
    }

    @GetMapping(value = "/findByNumber")
    public Page<Permit> numberDataPages(Pageable pageable,
            @RequestParam(required = false, defaultValue = "") String number) {
        return dataFromDBService.findByNumber(pageable, number);
    }

    @SneakyThrows
    @PostMapping("/start")
    public ParsingResponse start(@RequestBody FileUrl fileUrl) {

        return startParsing.processData(fileUrl.getFileUrl());
    }


}

