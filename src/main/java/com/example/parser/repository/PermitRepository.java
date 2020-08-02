package com.example.parser.repository;

import com.example.parser.entity.Permit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PermitRepository extends JpaRepository<Permit, Long> {

}
