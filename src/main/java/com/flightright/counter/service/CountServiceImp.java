package com.flightright.counter.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CountServiceImp implements CountService {

    @Value("classpath:/data/test.csv")
    Resource resourceFile;

    @Value("classpath:data/*")
    private Resource[] resources;

    @Override
    public long countPageVisits() {
        try {
            File file = resourceFile.getFile();
            Set<String> visitedUsers = new HashSet<>();

            try(BufferedReader br = new BufferedReader(new FileReader(file))){
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

    @Override
    public List<String> getFilesNames() {
        return resources == null ? Collections.emptyList() :
                Stream.of(resources)
                        .map(o -> o.getFilename())
                        .collect(Collectors.toList());
    }


}
