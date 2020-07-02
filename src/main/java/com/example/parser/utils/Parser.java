package com.example.parser.utils;

import com.example.parser.entity.PermitForEmissionsOfPollutants;
import com.example.parser.repository.PermitRepository;
import com.example.parser.service.PermitService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class Parser {

    private PermitForEmissionsOfPollutants permit;
    private PermitService permitService;
    private float time;
    private Date date;
    private int fileCounter;
    private int dataBaseCounter;
    private static final Logger log = LoggerFactory.getLogger(Parser.class);

    private String str;

    public String getStr() {
        return str = "Time = " + time +"\n"
                + "Date = " + date + "\n"
                + "Records from file " + fileCounter +"\n"
                + "Records saved to DB " + dataBaseCounter+"\n";
    }



    public Parser() {
    }

    public Parser(PermitService permitService){
        this.permitService = permitService;
    }

    public void parser() throws IOException, ParseException {

        FileDownload fileDownload = new FileDownload();
        fileDownload.download();

        long start = System.currentTimeMillis();

        FileReader reader = new FileReader(fileDownload.getTmp());
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);

        List<PermitForEmissionsOfPollutants> BD_list = new ArrayList<>(permitService.getAll());
        List<PermitForEmissionsOfPollutants> JsonList = new LinkedList<>();

        Iterator i = jsonArray.iterator();

        while (i.hasNext()) {
            JSONObject innerObj = (JSONObject) i.next();
            permit = new PermitForEmissionsOfPollutants();

            permit.setEdrpou(String.valueOf(innerObj.get("edrpou")));
            permit.setNumber(String.valueOf(innerObj.get("number")));
            permit.setName(String.valueOf(innerObj.get("name")));
            permit.setData_type(String.valueOf(innerObj.get("data_type")));
            permit.setValidity(String.valueOf(innerObj.get("validity")));
            permit.setLegal_address(String.valueOf(innerObj.get("legal_address")));
            permit.setActual_address(String.valueOf(innerObj.get("actual_address")));
            JsonList.add(permit);
            fileCounter++;
        }

        Set<PermitForEmissionsOfPollutants> union = new HashSet(JsonList);
        union.removeAll(BD_list);

        for (PermitForEmissionsOfPollutants k : union) {
            permitService.save(k);
            dataBaseCounter++;
        }

        long end = System.currentTimeMillis();
        time = TimeUnit.MILLISECONDS.toSeconds(end - start);
        date = new Date();

        log.info("1) Time spent processing the file {} seconds", time);
        log.info("2) Date the file was processed {}", date);
        log.info("3) How many records were read from the file {}", fileCounter);
        log.info("4) How many new records have been added to the database {}", dataBaseCounter);
    }
}
