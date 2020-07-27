package com.example.parser.service;

import com.example.parser.entity.Permit;
import com.example.parser.repository.PagingPermitRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Data
@Service
public class DataFromDBService {

    private PagingPermitRepository pagingPermitRepository;

    @Autowired
    public DataFromDBService(PagingPermitRepository pagingPermitRepository) {
        this.pagingPermitRepository = pagingPermitRepository;
    }

    public Page<Permit> listAll(Pageable pageable){
        return pagingPermitRepository.findAll(pageable);
    }

    public Page<Permit> findById(Pageable pageable, int id){
        return pagingPermitRepository.findById(pageable, id);
    }

    public Page<Permit> findByNumber(Pageable pageable, String number){
        return pagingPermitRepository.findByNumber(pageable, number);
    }

}
