package com.example.parser.controller;

import com.example.parser.dto.FileUrl;
import com.example.parser.dto.ParsingResponse;
import com.example.parser.entity.Permit;
import com.example.parser.utils.DataFromDBService;
import com.example.parser.utils.StartParsing;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class ParserController {

    private final StartParsing startParsing;
    private final DataFromDBService dataFromDBService;

    public ParserController(StartParsing startParsing, DataFromDBService dataFromDB) {
        this.startParsing = startParsing;
        this.dataFromDBService = dataFromDB;
    }

    @GetMapping(value = "/")
    public List<Permit> dataPages(Model model, @RequestParam(defaultValue = "0") int page) {

        Page<Permit> pageData = dataFromDBService.listAll(page);
        List<Permit> permitList = pageData.getContent();

//        int totalPages = pageData.getTotalPages();
//
//        model.addAttribute("totalPages",totalPages);
//        model.addAttribute("permitList", permitList);
//
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("permit");

        return permitList;
    }

    @SneakyThrows
    @PostMapping("/start")
    public ParsingResponse start(@RequestBody FileUrl fileUrl) {

        return startParsing.processData(fileUrl.getFileUrl());
    }


}

