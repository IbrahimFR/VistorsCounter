package com.flightright.counter.generator;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.types.enums.StringType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;
import static net.andreinc.mockneat.types.enums.DomainSuffixType.POPULAR;

public class FileGenerator {
    public static void main(String[] args) {
        generateCsvFile();
    }

   private static void generateCsvFile(){
       MockNeat m = MockNeat.threadLocal();
       final Path path = Paths.get("./src/main/resources/data/test.csv");

       m.fmt("#{email},#{phone},#{source}")
               .param("email", m.emails())
               .param("phone", m.strings().size(10).types(StringType.NUMBERS))
               .param("source", m.urls().domains(POPULAR))
               .list(10000000)
               .consume(list -> {
                   try { Files.write(path, list, CREATE, WRITE); }
                   catch (IOException e) { e.printStackTrace(); }
               });
   }
}
