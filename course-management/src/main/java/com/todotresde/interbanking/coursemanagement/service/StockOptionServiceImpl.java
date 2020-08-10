package com.todotresde.interbanking.coursemanagement.service;

import com.todotresde.interbanking.coursemanagement.model.Course;
import com.todotresde.interbanking.coursemanagement.model.StockOption;
import com.todotresde.interbanking.coursemanagement.model.Transaction;
import com.todotresde.interbanking.coursemanagement.repository.CourseRepository;
import com.todotresde.interbanking.coursemanagement.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * The type Stock option service.
 */
@Service
public class StockOptionServiceImpl implements StockOptionService{
    @Override
    public StockOption findByBrandIsIn(Collection<StockOption> stockOptions, String brand) {
        return stockOptions.stream().filter(stockOption -> brand.equals(stockOption.getBrand())).findFirst().orElse(null);
    }
}
