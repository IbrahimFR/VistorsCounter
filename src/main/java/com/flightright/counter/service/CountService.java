package com.flightright.counter.service;

import java.util.List;

/**
 * Count Service is  responsible to read a huge csv file
 * and count the number of visitors
 */
public interface CountService {
    /**
     * count the page unique visitors from multiple sources.
     *
     * @param fileName   file to be read
     * @param readFaster flag to enable user to read faster or slower using two different functionalities.
     * @return number of visitors
     */
    long countPageVisitors(String fileName, boolean readFaster);

    /**
     * get the file names.
     *
     * @return list of file names
     */
    List<String> getFilesNames();

    /**
     * generate the csv file with anonymous and save it.
     *
     * @param fileName desired file name
     * @return boolean
     */
    boolean generateCsvFile(String fileName);
}
