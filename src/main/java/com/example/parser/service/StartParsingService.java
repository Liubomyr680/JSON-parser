package com.example.parser.service;

import com.example.parser.dto.ParsingResponse;
import com.example.parser.entity.Permit;
import com.example.parser.repository.PermitRepository;
import com.example.parser.utils.FileDownload;
import com.example.parser.utils.Parser;
import lombok.Data;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Data
@Service
public class StartParsingService {

    private static final Logger log = LoggerFactory.getLogger(StartParsingService.class);
    private PermitRepository permitRepository;

    public StartParsingService(PermitRepository permitRepository) {
        this.permitRepository = permitRepository;
    }

    public ParsingResponse processData(String fileUrl) {

        int newRecordsCounter = 0;
        Parser parser = new Parser();
        ParsingResponse parsingResponse = new ParsingResponse();
        List<Permit> dataFromParsing = null;

        long start = System.currentTimeMillis();

        try {
             dataFromParsing = new ArrayList<>(parser.startParsingTheFile(FileDownload.download(fileUrl)));
        } catch (IOException | ParseException e) {
            log.error("Parsing problem", e);
        }

        long end = System.currentTimeMillis();
        float time = TimeUnit.MILLISECONDS.toSeconds(end - start);
        parsingResponse.setTimeSpent(time);
        Date date = new Date();
        parsingResponse.setFileDate(new Date());
        parsingResponse.setFileRecordsCounter(parser.getFileCounter());

        List<Permit> dataFromDB = new ArrayList<>(permitRepository.findAll());
        Set<Permit> union = new HashSet(dataFromParsing);
        union.removeAll(dataFromDB);

        for (Permit newRecords : union) {
            permitRepository.save(newRecords);
            newRecordsCounter++;
        }

        parsingResponse.setDataBaseNewRecordsCounter(newRecordsCounter);
        log.info("1) Time spent processing the file {} seconds", time);
        log.info("2) Date the file was processed {}", date);
        log.info("3) How many records were read from the file {}", parser.getFileCounter());
        log.info("4) How many new records have been added to the database {}", newRecordsCounter);

        return parsingResponse;
    }
}
