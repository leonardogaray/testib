package com.todotresde.interbanking.coursemanagement.service;

import com.todotresde.interbanking.coursemanagement.model.Course;
import com.todotresde.interbanking.coursemanagement.model.Transaction;

import java.util.List;

public interface CourseService {

    List<Course> allCourses();

    Course findCourseById(Long courseId);

    List<Transaction> findTransactionOfUser(Long userId);

    List<Transaction> findTransactionOfCourse(Long courseId);

    Transaction save(Transaction transaction);
}
