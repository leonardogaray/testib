package com.todotresde.interbanking.coursemanagement.service;

import com.todotresde.interbanking.coursemanagement.model.*;
import com.todotresde.interbanking.coursemanagement.repository.CourseRepository;
import com.todotresde.interbanking.coursemanagement.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * The type Strategy service.
 */
@Service
public class StrategyServiceImpl implements StrategyService{
    @Autowired
    private StockOptionService stockOptionService;
    @Autowired
    private StockOptionStrategyService stockOptionStrategyService;

    @Override
    public void simulate(Strategy strategy, List<StockOption> previousStockOptions, List<StockOption> currentStockOptions) {
        List<StockOption> possibleStockOptionsToBuy = new ArrayList<StockOption>();
        List<StockOption> possibleStockOptionsToSell = new ArrayList<StockOption>();

        for (StockOption previousStockOption : previousStockOptions) {
            StockOption currentStockOption = stockOptionService.findByBrandIsIn(currentStockOptions, previousStockOption.getBrand());

            if (currentStockOption != null) {
                if (previousStockOption.getPrice() < currentStockOption.getPrice() * 0.99) {
                    possibleStockOptionsToBuy.add(currentStockOption);
                }

                if (previousStockOption.getPrice() * 1.02 > currentStockOption.getPrice()) {
                    possibleStockOptionsToSell.add(currentStockOption);
                }
            }
        }

        buyStockOptions(strategy, possibleStockOptionsToBuy);
        sellStockOptions(strategy, possibleStockOptionsToSell);
    }

    @Override
    public void buyStockOptions(Strategy strategy, List<StockOption> stockOptions){
        for(StockOption stockOption : stockOptions) {
            buyStockOption(strategy, stockOption);
        }
    }

    @Override
    public void sellStockOptions(Strategy strategy, List<StockOption> stockOptions){
        for(StockOption stockOption : stockOptions) {
            sellStockOption(strategy, stockOption);
        }
    }

    private void buyStockOption(Strategy strategy, StockOption stockOption){
        List <StockOptionStrategy> stockOptionStrategies = stockOptionStrategyService.findByBrandIsIn(strategy.getStockOptionStrategies(), stockOption.getBrand());

        if(!stockOptionStrategies.isEmpty()) {
            Integer numberOfStockOptions = (int) Math.floor(1000 / stockOption.getPrice());
            strategy.setCash(strategy.getCash() - (numberOfStockOptions * stockOption.getPrice()));
            strategy.getStockOptionStrategies().add(new StockOptionStrategy(numberOfStockOptions, stockOption));
        }
    }

    private void sellStockOption(Strategy strategy, StockOption stockOption){
        strategy.getStockOptionStrategies().forEach(stockOptionStrategy -> {
            if(stockOption.getBrand().equals(stockOptionStrategy.getBrand())){
                stockOptionStrategy.setSellDate(stockOption.getDate());
                stockOptionStrategy.setSellPrice(stockOption.getPrice());
                strategy.setCash(strategy.getCash() + (stockOptionStrategy.getCount() * stockOptionStrategy.getSellPrice()));
            }
        });
    }
}
