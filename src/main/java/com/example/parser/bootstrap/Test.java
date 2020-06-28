package com.example.parser.bootstrap;

import com.example.parser.repository.PermitRepository;
import com.example.parser.utils.Parser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class Test implements CommandLineRunner  {

    private static final String filePath = "/Users/kristina/Downloads/dozvoli-na-vikidi-onovlenii.json";
    private final PermitRepository permitRepository;

    public Test(PermitRepository permitRepository) {
        this.permitRepository = permitRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Parser parser = new Parser(permitRepository);
        parser.parser(filePath);

        }
    }


