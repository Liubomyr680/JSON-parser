package com.example.parser.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ResponseFromParsing {
    private float TimeSpent;
    private Date FileDate;
    private int FileRecordsCounter;
    private int DBNewRecordsCounter;
}
