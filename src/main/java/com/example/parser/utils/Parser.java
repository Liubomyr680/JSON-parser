package com.example.parser.utils;

import com.example.parser.entity.PermitForEmissionsOfPollutants;
import com.example.parser.repository.PermitRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

public class Parser {

   // private static final String filePath = "C:\\Users\\Любомир\\Downloads\\dozvoli-na-vikidi-v-atmosferu-onovlenii.json";
    private PermitForEmissionsOfPollutants permit;
    private PermitForEmissionsOfPollutants permit2;
    private final PermitRepository permitRepository;
    private long time;
    private Date date;
    private int counter;

    public Parser(PermitRepository permitRepository) {
        this.permitRepository = permitRepository;
    }

    public void parser(String filePath) throws IOException, ParseException {

        long start = System.currentTimeMillis();
        FileReader reader = new FileReader(filePath);
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);

        Iterator i = jsonArray.iterator();

        while (i.hasNext()) {

            JSONObject innerObj = (JSONObject) i.next();
            permit = new PermitForEmissionsOfPollutants();
            permit2 = new PermitForEmissionsOfPollutants();

            permit.setEdrpou(String.valueOf(innerObj.get("edrpou")));
            permit.setNumber(String.valueOf(innerObj.get("number")));
            permit.setName(String.valueOf(innerObj.get("name")));
            permit.setData_type(String.valueOf(innerObj.get("data_type")));
            permit.setValidity(String.valueOf(innerObj.get("validity")));
            permit.setLegal_address(String.valueOf(innerObj.get("legal_address")));
            permit.setActual_address(String.valueOf(innerObj.get("actual_address")));
            counter++;
            permit2 = permitRepository.findById(counter);

            if(!permit.equals(permit2)){
                permitRepository.save(permit);
            }

        }
        long end = System.currentTimeMillis();
        time = end - start;
        date = new Date();

        System.out.println(counter);






    }



}
