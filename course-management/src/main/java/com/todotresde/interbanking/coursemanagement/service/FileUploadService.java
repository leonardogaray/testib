package com.todotresde.interbanking.coursemanagement.service;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import com.todotresde.interbanking.coursemanagement.model.StockOption;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * The interface File upload service.
 */
public interface FileUploadService {
    /**
     * Init.
     */
    public void init();

    /**
     * Save.
     *
     * @param file the file
     */
    public void save(MultipartFile file);

    /**
     * Load resource.
     *
     * @param filename the filename
     * @return the resource
     */
    public Resource load(String filename);

    /**
     * Delete all.
     */
    public void deleteAll();

    /**
     * Load all stream.
     *
     * @return the stream
     */
    public Stream<Path> loadAll();

    public void generateCSV(String filename);

    public List<StockOption> getCSV(String filename);
}
