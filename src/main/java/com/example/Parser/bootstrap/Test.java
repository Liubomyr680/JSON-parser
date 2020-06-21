package com.example.Parser.bootstrap;

import com.example.Parser.entity.PermitForEmissionsOfPollutants;

import com.example.Parser.repository.PermitRepository;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.util.Iterator;


@Component
public class Test implements CommandLineRunner  {

    private static final String filePath = "C:\\Users\\Любомир\\Downloads\\dozvoli-na-vikidi-v-atmosferu-onovlenii.json";
    private PermitForEmissionsOfPollutants permit;
    private final PermitRepository permitRepository;

    public Test(PermitRepository permitRepository) {
        this.permitRepository = permitRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        FileReader reader = new FileReader(filePath);
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);

//        for(int i=0; i<jsonArray.size(); i++)
//            System.out.println("The " + i + " element of the array: " + jsonArray.get(i));

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

            permitRepository.save(permit);
//            System.out.println("edrpou "+ innerObj.get("edrpou") +
//
//                    " number " + innerObj.get("number") +
//                    " name "  + innerObj.get("name") +
//                    " data_type " + innerObj.get("data_type") +
//                    " validity " + innerObj.get("validity") +
//                    " legal_address " + innerObj.get("legal_address") +
//                    "actual_address " + innerObj.get("actual_address")+"\n\n" );
        }
    }
}

