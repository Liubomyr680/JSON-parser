package com.example.parser.repository;

import com.example.parser.entity.Permit;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagingPermitRepository extends PagingAndSortingRepository<Permit, Long> {


}
