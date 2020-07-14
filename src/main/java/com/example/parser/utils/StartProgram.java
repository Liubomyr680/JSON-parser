package com.example.parser.utils;

import com.example.parser.dto.ParsingResponse;
import com.example.parser.entity.PermitForEmissionsOfPollutants;
import com.example.parser.repository.PermitRepository;
import lombok.Data;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Data
public class StartProgram {

    private static final Logger log = LoggerFactory.getLogger(StartProgram.class);

    private PermitRepository permitRepository;
    private int newRecordsCounter;

    public StartProgram(PermitRepository permitRepository) {
        this.permitRepository = permitRepository;
    }

    public ParsingResponse start(String fileUrl) {

        Parser parser = new Parser();
        ParsingResponse parsingResponse = new ParsingResponse();
        List<PermitForEmissionsOfPollutants> dataFromParsing = null;

        long start = System.currentTimeMillis();
        try {
             dataFromParsing = new ArrayList<>(parser.startParsing(FileDownload.download(fileUrl)));
        } catch (IOException | ParseException e) {
            log.error(e.getMessage());
        }
        long end = System.currentTimeMillis();
        float time = TimeUnit.MILLISECONDS.toSeconds(end - start);
        parsingResponse.setTimeSpent(time);
        Date date = new Date();
        parsingResponse.setFileDate(new Date());
        parsingResponse.setFileRecordsCounter(parser.getFileCounter());


        List<PermitForEmissionsOfPollutants> dataFromDB = new ArrayList<>(permitRepository.findAll());

        Set<PermitForEmissionsOfPollutants> union = new HashSet(dataFromParsing);
        union.removeAll(dataFromDB);

        for (PermitForEmissionsOfPollutants k : union) {
            permitRepository.save(k);
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
