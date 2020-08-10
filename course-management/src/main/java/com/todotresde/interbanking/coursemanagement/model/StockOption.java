package com.todotresde.interbanking.coursemanagement.model;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * The type Stock option.
 */
@Data
public class StockOption implements Comparable<StockOption>{
    private String brand;
    private Float price;
    private LocalDate date;

    /**
     * Instantiates a new Stock option.
     *
     * @param brand the brand
     * @param date  the date
     * @param price the price
     */
    public StockOption(String brand, String date, String price){
        DateTimeFormatter formatter_1 = DateTimeFormatter.ofPattern("d/MM/yyyy");

        this.brand = brand;
        this.date = LocalDate.parse(date, formatter_1);
        this.price = new Float(price.replace("$", ""));
    }

    @Override
    public int compareTo(StockOption stockOption) {
        return getDate().compareTo(stockOption.getDate());
    }
}
