package com.example.parser.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ParsingResponse {
    private float timeSpent;
    private Date fileDate;
    private int fileRecordsCounter;
    private int dataBaseNewRecordsCounter;
}
