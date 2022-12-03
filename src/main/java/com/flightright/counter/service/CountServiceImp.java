package com.flightright.counter.service;

import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;
//10000736
@Service
public class CountServiceImp implements CountService {

    @Value("classpath:/test.csv")
    Resource resourceFile;

    @Override
    public long countPageVisits() {
        try {
            File file = resourceFile.getFile();
            Set<String> visitedUsers = new HashSet<>();

            try(BufferedReader br = new BufferedReader(new FileReader(file));){
                String line;
                while((line = br.readLine())!= null){
                    String emailAndMobile = line.split(",")[0] + line.split(",")[1];
                    visitedUsers.add(emailAndMobile);
                }

               return visitedUsers.stream().count();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    }
