package com.example.parser.repository;

import com.example.parser.entity.Permit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PermitRepository extends JpaRepository<Permit, Long> {

//    PermitForEmissionsOfPollutants findByEdrpou(String edrpou);
//    PermitForEmissionsOfPollutants findByNumber(String number);
    Page<Permit> findAll(Pageable pageable);
}
