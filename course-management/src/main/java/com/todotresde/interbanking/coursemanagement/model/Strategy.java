package com.todotresde.interbanking.coursemanagement.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * The type Strategy.
 */
@Data
public class Strategy {
    private String userId;
    private Float cash;
    private List<StockOptionStrategy> stockOptionStrategies;
}
