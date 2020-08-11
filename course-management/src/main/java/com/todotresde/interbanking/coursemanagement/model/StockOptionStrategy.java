package com.todotresde.interbanking.coursemanagement.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The type Stock option strategy.
 */
@Data
public class StockOptionStrategy implements Comparable<StockOptionStrategy>{
    private Integer count;
    private String brand;
    private Float previousBuyPrice;
    private Float buyPrice;
    private Float previousSellPrice;
    private Float sellPrice;
    private LocalDate previousBuyDate;
    private LocalDate buyDate;
    private LocalDate previousSellDate;
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
    @Override
    public int compareTo(StockOptionStrategy stockOptionStrategy) {
        return getBuyDate().compareTo(stockOptionStrategy.getBuyDate());
    }

}
