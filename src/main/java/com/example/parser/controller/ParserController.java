package com.example.parser.controller;

import com.example.parser.dto.FileUrl;
import com.example.parser.dto.ParsingResponse;
import com.example.parser.repository.PermitRepository;
import com.example.parser.utils.StartProgram;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParserController {

    private final PermitRepository permitRepository;

    public ParserController(PermitRepository permitRepository) {
      this.permitRepository = permitRepository;
    }

    @SneakyThrows
    @PostMapping("/start")
    public ParsingResponse start(@RequestBody FileUrl fileUrl) {

      StartProgram startProgram = new StartProgram(permitRepository);
      return startProgram.start(fileUrl.getFileUrl());
    }
}

