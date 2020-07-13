package com.example.parser.service;

import com.example.parser.dto.ParsingResponse;
import com.example.parser.entity.PermitForEmissionsOfPollutants;
import com.example.parser.repository.PermitRepository;
import com.example.parser.utils.Parser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PermitService {

    private final PermitRepository permitRepository;
    private Parser parser;

    public PermitService(PermitRepository permitRepository) {
        this.permitRepository = permitRepository;
        parser = new Parser(permitRepository);
    }

    public PermitForEmissionsOfPollutants save(PermitForEmissionsOfPollutants permitForEmissionsOfPollutants) {
        return permitRepository.save(permitForEmissionsOfPollutants);
    }

    public List<PermitForEmissionsOfPollutants> getAll() {
        return permitRepository.findAll();
    }

    public void delete(Long id) {
        permitRepository.deleteById(id);
    }

    public PermitForEmissionsOfPollutants findByNumber(String number) {
        return permitRepository.findByNumber(number);
    }

    public ParsingResponse startParsing(String fileUrl) throws IOException, ParseException {

        return parser.startParsing(fileUrl);
    }
}
