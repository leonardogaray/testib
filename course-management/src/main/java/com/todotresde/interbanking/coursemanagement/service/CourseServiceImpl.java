package com.todotresde.interbanking.coursemanagement.service;

import com.todotresde.interbanking.coursemanagement.model.Course;
import com.todotresde.interbanking.coursemanagement.model.Transaction;
import com.todotresde.interbanking.coursemanagement.repository.CourseRepository;
import com.todotresde.interbanking.coursemanagement.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Course service.
 */
@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Course> allCourses(){
        return courseRepository.findAll();
    }

    @Override
    public Course findCourseById(Long courseId){
        return courseRepository.findById(courseId).orElse(null);
    }

    @Override
    public List<Transaction> findTransactionOfUser(Long userId){
        return transactionRepository.findAllByUserId(userId);
    }

    @Override
    public List<Transaction> findTransactionOfCourse(Long courseId){
        return transactionRepository.findAllByCourseId(courseId);
    }

    @Override
    public Transaction save(Transaction transaction){
        return transactionRepository.save(transaction);
    }
}
