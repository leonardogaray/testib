package com.todotresde.interbanking.coursemanagement.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The type Stock option strategy.
 */
@Data
public class StockOptionStrategy {
    private Integer count;
    private String brand;
    private Float buyPrice;
    private Float sellPrice;
    private LocalDate buyDate;
    private LocalDate sellDate;

    /**
     * Instantiates a new Stock option strategy.
     *
     * @param count       the count
     * @param stockOption the stock option
     */
    public StockOptionStrategy(Integer count, StockOption stockOption){
        this.count = count;
        this.brand = stockOption.getBrand();
        this.buyPrice = stockOption.getPrice();
        this.buyDate = stockOption.getDate();
    }
}
