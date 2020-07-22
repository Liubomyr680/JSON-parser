package com.example.parser.utils;

import com.example.parser.entity.Permit;
import com.example.parser.repository.PagingPermitRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

@Data
@Service
public class DataFromDBService {

    private PagingPermitRepository pagingPermitRepository;

    @Autowired
    public DataFromDBService(PagingPermitRepository pagingPermitRepository) {
        this.pagingPermitRepository = pagingPermitRepository;
    }

    public Page<Permit> listAll(){
        Pageable pageable = PageRequest.of(0,10);
        return pagingPermitRepository.findAll(pageable);
    }
}
