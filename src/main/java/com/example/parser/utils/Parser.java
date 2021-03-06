package com.example.parser.utils;

import com.example.parser.dto.ParsingResponse;
import com.example.parser.entity.Permit;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;

public class Parser {

    private int fileCounter;

    public int getFileCounter() {
        return fileCounter;
    }

    public List<Permit> startParsingTheFile(File file) throws IOException, ParseException {

        ParsingResponse parsingResponse = new ParsingResponse();
        FileReader reader = new FileReader(file);
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);

        List<Permit> jsonList = new LinkedList<>();

        Iterator i = jsonArray.iterator();

        while (i.hasNext()) {
            JSONObject innerObj = (JSONObject) i.next();
            Permit permit = new Permit();

            permit.setEdrpou(String.valueOf(innerObj.get("edrpou")));
            permit.setNumber(String.valueOf(innerObj.get("number")));
            permit.setName(String.valueOf(innerObj.get("name")));
            permit.setData_type(String.valueOf(innerObj.get("data_type")));
            permit.setValidity(String.valueOf(innerObj.get("validity")));
            permit.setLegal_address(String.valueOf(innerObj.get("legal_address")));
            permit.setActual_address(String.valueOf(innerObj.get("actual_address")));
            jsonList.add(permit);
            fileCounter++;
        }
        parsingResponse.setFileRecordsCounter(fileCounter);

        return jsonList;
    }
}
