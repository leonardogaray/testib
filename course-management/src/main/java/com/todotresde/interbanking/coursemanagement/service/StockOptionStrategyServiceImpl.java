package com.todotresde.interbanking.coursemanagement.service;

import com.todotresde.interbanking.coursemanagement.model.StockOption;
import com.todotresde.interbanking.coursemanagement.model.StockOptionStrategy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Stock option strategy service.
 */
@Service
public class StockOptionStrategyServiceImpl implements StockOptionStrategyService{
    @Override
    public List<StockOptionStrategy> findByBrandIsIn(Collection<StockOptionStrategy> StockOptionStrategies, String brand) {
        return StockOptionStrategies.stream().filter(stockOptionStrategy -> brand.equals(stockOptionStrategy.getBrand())).collect(Collectors
                .toCollection(ArrayList::new));
    }
}
