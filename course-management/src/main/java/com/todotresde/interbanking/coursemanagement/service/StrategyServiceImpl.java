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
                Float priceDifference = previousStockOption.getPrice() - currentStockOption.getPrice();
                if ( priceDifference > 0 && priceDifference > previousStockOption.getPrice() * 0.01 ) {
                    possibleStockOptionsToBuy.add(currentStockOption);
                }

                if (priceDifference < 0 && Math.abs(priceDifference) > previousStockOption.getPrice() * 0.02) {
                    possibleStockOptionsToSell.add(currentStockOption);
                }
            }
        }

        buyStockOptions(strategy, possibleStockOptionsToBuy);
        sellStockOptions(strategy, possibleStockOptionsToSell, false);
    }

    @Override
    public void buyStockOptions(Strategy strategy, List<StockOption> stockOptions){
        for(StockOption stockOption : stockOptions) {
            buyStockOption(strategy, stockOption);
        }
    }

    @Override
    public void sellStockOptions(Strategy strategy, List<StockOption> stockOptions, Boolean forceCell){
        for(StockOption stockOption : stockOptions) {
            sellStockOption(strategy, stockOption, forceCell);
        }
    }

    private void buyStockOption(Strategy strategy, StockOption stockOption){
        List <StockOptionStrategy> stockOptionStrategies = stockOptionStrategyService.findByBrandIsInToSell(strategy.getStockOptionStrategies(), stockOption.getBrand());

        if(stockOptionStrategies.isEmpty()) {
            Integer numberOfStockOptions = (int) Math.floor(1000 / stockOption.getPrice());
            strategy.setCash(strategy.getCash() - (numberOfStockOptions * stockOption.getPrice()));
            strategy.getStockOptionStrategies().add(new StockOptionStrategy(numberOfStockOptions, stockOption));

        }
    }

    private void sellStockOption(Strategy strategy, StockOption stockOption, Boolean forceSell){
        strategy.getStockOptionStrategies().forEach(stockOptionStrategy -> {
            Boolean mandatoryToSell = forceSell && stockOption.getBrand().equals(stockOptionStrategy.getBrand())
                    && stockOptionStrategy.getSellDate() == null;
            Boolean canSell = stockOption.getBrand().equals(stockOptionStrategy.getBrand()) && stockOptionStrategy.getSellDate() == null
                    && stockOption.getDate().isAfter(stockOptionStrategy.getBuyDate());

            if(mandatoryToSell || canSell){
                stockOptionStrategy.setSellDate(stockOption.getDate());
                stockOptionStrategy.setSellPrice(stockOption.getPrice());
                strategy.setCash(strategy.getCash() + (stockOptionStrategy.getCount() * stockOptionStrategy.getSellPrice()));
            }
        });
    }
}
