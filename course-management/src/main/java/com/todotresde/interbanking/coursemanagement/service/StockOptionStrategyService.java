package com.todotresde.interbanking.coursemanagement.service;

import com.todotresde.interbanking.coursemanagement.model.StockOption;
import com.todotresde.interbanking.coursemanagement.model.StockOptionStrategy;

import java.util.Collection;
import java.util.List;

/**
 * The interface Stock option strategy service.
 */
public interface StockOptionStrategyService {
    /**
     * Find by brand is in list.
     *
     * @param stockOptions the stock options
     * @param brand        the brand
     * @return the list
     */
    public List<StockOptionStrategy> findByBrandIsIn(Collection<StockOptionStrategy> stockOptions, String brand);

}
