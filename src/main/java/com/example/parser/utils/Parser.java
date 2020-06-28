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
    private int File_counter;
    private int DB_counter;
    private static Logger log = LoggerFactory.getLogger(Parser.class);

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
            File_counter++;
        }

        Set<PermitForEmissionsOfPollutants> union = new HashSet(JsonList);
        union.removeAll(BD_list);

        for (PermitForEmissionsOfPollutants k : union) {
            permitRepository.save(k);
            DB_counter++;
        }

        long end = System.currentTimeMillis();
        time = TimeUnit.MILLISECONDS.toSeconds(end - start);
        date = new Date();

        log.info("\n1) Затрачений час на обробку файлу " + time + " секунд" +
                "\n2) Дата коли файл був оброблений " + date +
                "\n3) Скільки записів вичитало з файлу " + File_counter +
                "\n4) Скільки нових записів додало у базу " + DB_counter +
                "\n");
    }
}
