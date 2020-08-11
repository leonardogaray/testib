package com.todotresde.interbanking.coursemanagement.service;

import com.todotresde.interbanking.coursemanagement.model.StockOption;
import com.todotresde.interbanking.coursemanagement.model.Strategy;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

/**
 * The interface Stock option simulation service.
 */
public interface StockOptionSimulationService {
    /**
     * Simulate list.
     *
     * @param filename the filename
     * @return the list
     */
    public List<Strategy> simulate(String filename);

    public List<StockOption> readFile(String filename);
}
