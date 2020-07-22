package com.example.parser.controller;

import com.example.parser.dto.FileUrl;
import com.example.parser.dto.ParsingResponse;
import com.example.parser.entity.Permit;
import com.example.parser.utils.DataFromDBService;
import com.example.parser.utils.StartParsing;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
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

    @GetMapping("/data")
    public ModelAndView dataPages(Model model) {

        Page<Permit> page = dataFromDBService.listAll();
        List<Permit> permitList = page.getContent();

        int totalRecords = page.getNumberOfElements();
        int totalPages = page.getTotalPages();

        model.addAttribute("totalRecords",totalRecords);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("permitList", permitList);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("permit.html");

        return modelAndView;
    }

    @SneakyThrows
    @PostMapping("/start")
    public ParsingResponse start(@RequestBody FileUrl fileUrl) {

        return startParsing.processData(fileUrl.getFileUrl());
    }


}

