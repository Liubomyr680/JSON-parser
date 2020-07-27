package com.example.parser.repository;

import com.example.parser.entity.Permit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagingPermitRepository extends PagingAndSortingRepository<Permit, Long> {

    Page<Permit> findById(Pageable pageable, int id);
    Page<Permit> findByNumber(Pageable pageable, String number);

}
