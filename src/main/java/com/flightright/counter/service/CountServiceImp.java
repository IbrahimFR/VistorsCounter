package com.flightright.counter.service;

import com.flightright.counter.exception.FileGenerationException;
import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRow;
import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.types.enums.StringType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.flightright.counter.exception.FileNotFoundException;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;
import static net.andreinc.mockneat.types.enums.DomainSuffixType.POPULAR;

@Service
public class CountServiceImp implements CountService {
    Logger logger = LoggerFactory.getLogger(CountServiceImp.class);

    @Value("${app.data.location}")
    private String dataLocation;

    @Override
    public long countPageVisitors(String fileName, boolean readFaster) {
        Instant start = Instant.now();
        var files = getFiles();
        Optional<File> file = files == null ? Optional.empty() : Stream.of(files).filter(f -> f.getName().equals(fileName)).findFirst();
        long count = 0;
        if (file.isPresent()) {
            try {
                if (readFaster) {
                    count = readFaster(file.get());
                } else {
                    count = readSlower(file.get());
                }
                Instant end = Instant.now();
                Duration timeElapsed = Duration.between(start, end);
                logger.trace("Reading File Duration: " + timeElapsed.getSeconds());
                return count;
            } catch (IOException e) {
                throw new FileNotFoundException(e.getMessage());
            }
        }
        return count;
    }

    @Override
    public List<String> getFilesNames() {
        File[] files = getFiles();
        return files == null ? Collections.emptyList() : Stream.of(files).map(File::getName).collect(Collectors.toList());
    }

    @Override
    public boolean generateCsvFile(String fileName) {
        MockNeat m = MockNeat.threadLocal();
        final Path path = Paths.get(dataLocation + fileName + ".csv");

        m.fmt("#{email},#{phone},#{source}").param("email", m.emails()).param("phone", m.strings().size(10).types(StringType.NUMBERS)).param("source", m.urls().domains(POPULAR)).list(10000) // feel free to change the size (e.g. 10000000)
                .consume(list -> {
                    try {
                        Files.write(path, list, CREATE, WRITE);
                    } catch (IOException e) {
                        throw new FileGenerationException(e.getMessage());
                    }
                });
        return true;
    }

    private long readFaster(File file) throws IOException {
        Set<String> visitedUsers = new HashSet<>();
        final Path path = Paths.get(file.getPath());
        try (CsvReader csvReader = CsvReader.builder().build(path)) {
            for (final Iterator<CsvRow> iterator = csvReader.stream().iterator(); iterator.hasNext(); ) {
                final CsvRow csvRow = iterator.next();
                visitedUsers.add(csvRow.getField(0) + csvRow.getField(1));
            }
            return visitedUsers.size();
        }
    }

    private long readSlower(File file) throws IOException {
        Set<String> visitedUsers = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String emailAndMobile = line.split(",")[0] + line.split(",")[1];
                visitedUsers.add(emailAndMobile);
            }
            return visitedUsers.size();
        } catch (IOException e) {
            throw new FileNotFoundException(e.getMessage());
        }
    }

    private File[] getFiles() {
        var folder = new File(dataLocation);
        return folder.listFiles();
    }
}
