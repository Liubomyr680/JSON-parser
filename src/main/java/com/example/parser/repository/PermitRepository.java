package com.example.parser.repository;

import com.example.parser.entity.PermitForEmissionsOfPollutants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermitRepository extends JpaRepository<PermitForEmissionsOfPollutants, Long> {

    List<PermitForEmissionsOfPollutants> findByEdrpou(String number);

    PermitForEmissionsOfPollutants findById(int id);

}
