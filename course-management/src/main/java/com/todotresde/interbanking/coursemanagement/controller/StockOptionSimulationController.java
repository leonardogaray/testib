package com.todotresde.interbanking.coursemanagement.controller;

import com.todotresde.interbanking.coursemanagement.message.ResponseMessage;
import com.todotresde.interbanking.coursemanagement.model.FileInfo;
import com.todotresde.interbanking.coursemanagement.model.StockOptionSimulation;
import com.todotresde.interbanking.coursemanagement.model.Strategy;
import com.todotresde.interbanking.coursemanagement.service.FileUploadService;
import com.todotresde.interbanking.coursemanagement.service.StockOptionSimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Stock option simulation controller.
 */
@Controller
public class StockOptionSimulationController {

    /**
     * The Stock option simulation service.
     */
    @Autowired
    StockOptionSimulationService stockOptionSimulationService;

    /**
     * Gets file.
     *
     * @param filename the filename
     * @return the file
     */
    @GetMapping("/service/simulate/{filename:.+}")
    public ResponseEntity<?> getFile(@PathVariable String filename) {
        List<Strategy> strategies = stockOptionSimulationService.simulate(filename);
        return ResponseEntity.ok(strategies);
    }
}
