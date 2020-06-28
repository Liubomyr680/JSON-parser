package com.example.parser.utils;

import com.example.parser.entity.PermitForEmissionsOfPollutants;
import com.example.parser.repository.PermitRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Parser {

    private PermitForEmissionsOfPollutants permit;
    private final PermitRepository permitRepository;
    private float time;
    private Date date;
    private int fileCounter;
    private int dataBaseCounter;
    private static final Logger log = LoggerFactory.getLogger(Parser.class);

    public Parser(PermitRepository permitRepository) {
        this.permitRepository = permitRepository;
    }

    public void parser(String filePath) throws IOException, ParseException {

        long start = System.currentTimeMillis();

        FileReader reader = new FileReader(filePath);
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);

        List<PermitForEmissionsOfPollutants> BD_list = new ArrayList<>(permitRepository.findAll());
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
            permitRepository.save(k);
            dataBaseCounter++;
        }

        long end = System.currentTimeMillis();
        time = TimeUnit.MILLISECONDS.toSeconds(end - start);
        date = new Date();

        log.info("\n1) Time spent processing the file " + time + " seconds");
        log.info("\n2) Date the file was processed " + date);
        log.info("\n3) How many records were read from the file " + fileCounter);
        log.info("\n4) How many new records have been added to the database " + dataBaseCounter);
    }
}
