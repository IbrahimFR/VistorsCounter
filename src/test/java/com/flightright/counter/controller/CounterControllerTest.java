package com.flightright.counter.controller;

import com.flightright.counter.model.GenerateFileRequestBody;
import com.flightright.counter.service.CountServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * CounterControllerTest responsible to test {@link CounterController}
 */
@WebMvcTest(CounterControllerImp.class)
class CounterControllerTest {

    private CounterControllerImp controller;

    @MockBean
    private CountServiceImp countServiceImp;

    public static final String FILENAME = "test-visitors.csv";


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new CounterControllerImp(countServiceImp);
    }

    @Test
    void retrieveVisitorsCountWithReadFasterTest() {
        long count = 1L;
        given(countServiceImp.countPageVisitors(FILENAME, true)).willReturn(count);

        var visitorCount = controller.getCounts(FILENAME, true);

        assertThat(visitorCount, is(count));
    }

    @Test
    void retrieveVisitorsCountWithReadSlowerTest() {
        long count = 1L;
        given(countServiceImp.countPageVisitors(FILENAME, false)).willReturn(count);

        var visitorCount = controller.getCounts(FILENAME, false);

        assertThat(visitorCount, is(count));
    }

    @Test
    void retrieveFileNamesTest() {
        var files = List.of(FILENAME);
        given(countServiceImp.getFilesNames()).willReturn(files);
        var filesNames = controller.getFiles();

        assertThat(filesNames, is(files));
        assertThat(filesNames.size(), is(1));
        assertThat(filesNames.get(0), is(FILENAME));
    }

    @Test
    void generateCsvFilesTest() {
        given(countServiceImp.generateCsvFile(FILENAME)).willReturn(true);
        var response = controller.generateCsvFile(mockedRequestBody());

        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
    }

    private static GenerateFileRequestBody mockedRequestBody() {
        var requestBody = new GenerateFileRequestBody();
        requestBody.setFileName(FILENAME);
        return requestBody;
    }
}