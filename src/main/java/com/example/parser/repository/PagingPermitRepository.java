package com.example.parser.repository;

import com.example.parser.entity.Permit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PagingPermitRepository extends PagingAndSortingRepository<Permit, Long> {

    Page<Permit> findById(Pageable pageable, int id);

//    @Query(value = "SELECT * FROM json_data WHERE number LIKE '123%'",
//            nativeQuery = true)
//    Page<Permit> findByNumber(Pageable pageable, String number);

    @Query(value = "SELECT * FROM json_data j WHERE j.number LIKE CONCAT('%',:number ,'%')",
            nativeQuery = true)
    Page<Permit> findByNumber(Pageable pageable, @Param("number") String number);

}
