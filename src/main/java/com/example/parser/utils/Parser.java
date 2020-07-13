package com.example.parser.utils;

import com.example.parser.dto.ParsingResponse;
import com.example.parser.entity.PermitForEmissionsOfPollutants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class Parser {

    private static final Logger log = LoggerFactory.getLogger(Parser.class);
    private int fileCounter;

    public int getFileCounter() {
        return fileCounter;
    }

    public List<PermitForEmissionsOfPollutants> startParsing(String fileUrl) throws IOException, ParseException {

        ParsingResponse parsingResponse = new ParsingResponse();
        FileDownload.download(fileUrl);

        FileReader reader = new FileReader(FileDownload.getTmpFile());
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);

        List<PermitForEmissionsOfPollutants> JsonList = new LinkedList<>();

        Iterator i = jsonArray.iterator();

        while (i.hasNext()) {
            JSONObject innerObj = (JSONObject) i.next();
            PermitForEmissionsOfPollutants permit = new PermitForEmissionsOfPollutants();

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
        parsingResponse.setFileRecordsCounter(fileCounter);

        return JsonList;
    }
}
