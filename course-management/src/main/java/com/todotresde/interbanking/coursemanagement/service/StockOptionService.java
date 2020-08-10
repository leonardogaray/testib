package com.todotresde.interbanking.coursemanagement.service;

import com.todotresde.interbanking.coursemanagement.model.Course;
import com.todotresde.interbanking.coursemanagement.model.StockOption;
import com.todotresde.interbanking.coursemanagement.model.Transaction;

import java.util.Collection;
import java.util.List;

/**
 * The interface Stock option service.
 */
public interface StockOptionService {
    /**
     * Find by brand is in stock option.
     *
     * @param stockOptions the stock options
     * @param brand        the brand
     * @return the stock option
     */
    public StockOption findByBrandIsIn(Collection<StockOption> stockOptions, String brand);

}
