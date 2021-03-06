package com.todotresde.interbanking.coursemanagement.service;

import com.todotresde.interbanking.coursemanagement.model.StockOption;
import com.todotresde.interbanking.coursemanagement.model.StockOptionSimulation;
import com.todotresde.interbanking.coursemanagement.model.Strategy;
import com.todotresde.interbanking.coursemanagement.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
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
        strategies = new ArrayList<>();

        stockOptionSimulation = new StockOptionSimulation(new Long(1), readFile(filename));

        strategies.add(new Strategy());

        startsSimulation();

        strategies.forEach(strategy -> strategy.sortStockOptionStrategies());

        return strategies;
    }

    @Override
    public List<StockOption> readFile(String filename){
        Path file = root.resolve(filename);
        BufferedReader bufferedReader = null;
        String line = "";
        String cvsSplitBy = ", ";
        List<StockOption> stockOptions = new ArrayList<>();

        try {
            bufferedReader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(file.toString()), "UTF-8"));
            while ((line = bufferedReader.readLine()) != null) {
                String[] stockOptionFromFile = line.split(cvsSplitBy);

                if(StockOption.IsValid(stockOptionFromFile[0], stockOptionFromFile[1], stockOptionFromFile[2])){
                    StockOption stockOption = new StockOption(stockOptionFromFile[0], stockOptionFromFile[1], stockOptionFromFile[2]);
                    stockOptions.add(stockOption);
                }
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
            Map<LocalDate, List<StockOption>> stockOptionSimulationByDays = new TreeMap<LocalDate, List<StockOption>>();

            stockOptionSimulation.getStockOptions().parallelStream().forEach((stockOption -> {
                if(!stockOptionSimulationByDays.containsKey(stockOption.getDate())){
                    stockOptionSimulationByDays.put(stockOption.getDate(), new ArrayList<>());
                }
                stockOptionSimulationByDays.get(stockOption.getDate()).add(stockOption);
            }));

            processStockOptionByStrategy(stockOptionSimulationByDays);
        }
    }

    private void processStockOptionByStrategy(Map<LocalDate, List<StockOption>> stockOptionSimulationByBrands){
        LocalDate previousDate = null;
        LocalDate currentDate = null;
        List<StockOption> previousStockOptions = new ArrayList<>();
        List<StockOption> currentStockOptions = new ArrayList<>();

        for (Map.Entry<LocalDate, List<StockOption>> stockOptions : stockOptionSimulationByBrands.entrySet()){
            currentStockOptions = stockOptions.getValue();
            if(previousStockOptions.isEmpty()){
                previousStockOptions = currentStockOptions;
            }

            for(Strategy strategy : strategies) {
                strategyService.simulate(strategy, previousStockOptions, currentStockOptions);
            };

            previousStockOptions = currentStockOptions;
        }

        for(Strategy strategy : strategies) {
            strategyService.sellStockOptions(strategy, previousStockOptions, true);
        };
    }
}
