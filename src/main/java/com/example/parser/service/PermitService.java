package com.example.parser.service;

import com.example.parser.entity.PermitForEmissionsOfPollutants;
import com.example.parser.repository.PermitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermitService implements CrudService<PermitForEmissionsOfPollutants> {


    private final PermitRepository permitRepository;

    @Override
    public PermitForEmissionsOfPollutants save(PermitForEmissionsOfPollutants permitForEmissionsOfPollutants) {
        return permitRepository.save(permitForEmissionsOfPollutants);
    }

    public PermitService(PermitRepository permitRepository) {
        this.permitRepository = permitRepository;
    }

    @Override
    public List<PermitForEmissionsOfPollutants> getAll() {
        return permitRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        permitRepository.deleteById(id);

    }

    @Override
    public PermitForEmissionsOfPollutants findByNumber(String number) {
        return permitRepository.findByNumber(number);
    }
}
