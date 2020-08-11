package com.todotresde.interbanking.coursemanagement.service;

import com.todotresde.interbanking.coursemanagement.model.Course;
import com.todotresde.interbanking.coursemanagement.model.StockOption;
import com.todotresde.interbanking.coursemanagement.model.Strategy;
import com.todotresde.interbanking.coursemanagement.model.Transaction;

import java.util.List;
import java.util.Map;

/**
 * The interface Strategy service.
 */
public interface StrategyService {
    /**
     * Simulate.
     *
     * @param strategy             the strategy
     * @param previousStockOptions the previous stock options
     * @param currentStockOptions  the current stock options
     */
    public void simulate(Strategy strategy, List<StockOption> previousStockOptions, List<StockOption> currentStockOptions);

    /**
     * Buy stock options.
     *
     * @param strategy     the strategy
     * @param stockOptions the stock options
     */
    public void buyStockOptions(Strategy strategy, List<StockOption> stockOptions);

    /**
     * Sell stock options.
     *
     * @param strategy     the strategy
     * @param stockOptions the stock options
     */
    public void sellStockOptions(Strategy strategy, List<StockOption> stockOptions, Boolean forceCell);
}
