package com.flightright.counter.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * responsible to test {@link CountService}
 */
@ExtendWith(MockitoExtension.class)
public class CounterServiceTest {

    @InjectMocks
    private CountServiceImp countService;


    public static final String FILENAME = "test-visitors.csv";
    public static final String PATH = "./src/test/resources/data";

    @BeforeEach
    void init() {
        ReflectionTestUtils.setField(countService, "dataLocation", PATH);
    }

    @Test
    public void testCountPageVisitsWithReadFaster() {
        long count = countService.countPageVisitors(FILENAME, true);
        // then - verify the output
        assertThat(count).isNotNull();
        assertThat(count).isEqualTo(1);
    }

    @Test
    public void testCountPageVisitsWithReadSlower() {
        long count = countService.countPageVisitors(FILENAME, false);
        // then - verify the output
        assertThat(count).isNotNull();
        assertThat(count).isEqualTo(1);
    }

    @Test
    public void testGetFileNames() {
        var filesNames = countService.getFilesNames();
        // then - verify the output
        assertThat(filesNames).isNotNull();
        assertThat(filesNames.size()).isEqualTo(1);
        assertThat(filesNames.get(0)).isEqualTo(FILENAME);
    }

    @Test
    public void testGenerateCsvFile() {
        var generated = countService.generateCsvFile(FILENAME);
        // then - verify the output
        assertThat(generated).isNotNull();
        assertThat(generated).isEqualTo(true);
    }

}
