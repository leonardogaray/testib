package com.todotresde.interbanking.coursemanagement.service;

import com.todotresde.interbanking.coursemanagement.model.StockOption;
import com.todotresde.interbanking.coursemanagement.model.StockOptionSimulation;
import com.todotresde.interbanking.coursemanagement.model.Strategy;
import com.todotresde.interbanking.coursemanagement.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

/**
 * The type Stock option simulation service.
 */
@Service
public class StockOptionSimulationServiceImpl implements StockOptionSimulationService {

    @Autowired
    private StrategyService strategyService;

    private final Path root = Paths.get("uploads");
    private StockOptionSimulation stockOptionSimulation;

    private List<Strategy> strategies;

    @Override
    public List<Strategy> simulate(String filename){
        stockOptionSimulation = new StockOptionSimulation(new Long(1), readFile(filename));

        strategies.add(new Strategy());
        strategies.add(new Strategy());

        startsSimulation();

        return strategies;
    }

    private List<StockOption> readFile(String filename){
        Path file = root.resolve(filename);
        BufferedReader bufferedReader = null;
        String line = "";
        String cvsSplitBy = ",";
        List<StockOption> stockOptions = new ArrayList<>();

        try {
            bufferedReader = new BufferedReader(new FileReader(file.toString()));
            while ((line = bufferedReader.readLine()) != null) {
                String[] stockOptionFromFile = line.split(cvsSplitBy);
                StockOption stockOption = new StockOption(stockOptionFromFile[0], stockOptionFromFile[1], stockOptionFromFile[2]);
                stockOptions.add(stockOption);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return stockOptions;
        }
    }

    private void startsSimulation(){
        if(stockOptionSimulation != null){
            Map<LocalDate, List<StockOption>> stockOptionSimulationByDays = new HashMap<LocalDate, List<StockOption>>();

            stockOptionSimulation.getStockOptions().parallelStream().forEach((stockOption -> {
                if(!stockOptionSimulationByDays.containsKey(stockOption.getDate())){
                    stockOptionSimulationByDays.put(stockOption.getDate(), new ArrayList<>());
                }
                stockOptionSimulationByDays.get(stockOption.getBrand()).add(stockOption);
            }));

            processStockOptionByStrategy(stockOptionSimulationByDays);
        }
    }

    private void processStockOptionByStrategy(Map<LocalDate, List<StockOption>> stockOptionSimulationByBrands){
        LocalDate previousDate = null;
        LocalDate currentDate = null;
        List<StockOption> previousStockOptions = null;
        List<StockOption> currentStockOptions = null;

        for (Map.Entry<LocalDate, List<StockOption>> stockOptions : stockOptionSimulationByBrands.entrySet()){
            currentStockOptions = stockOptions.getValue();
            if(previousStockOptions == null){
                previousStockOptions = currentStockOptions;
            }

            for(Strategy strategy : strategies) {
                strategyService.simulate(strategy, previousStockOptions, currentStockOptions);
            };

            previousStockOptions = currentStockOptions;
        }

        for(Strategy strategy : strategies) {
            strategyService.sellStockOptions(strategy, previousStockOptions);
        };
    }
}
