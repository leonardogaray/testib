package com.todotresde.interbanking.coursemanagement.service;

import com.todotresde.interbanking.coursemanagement.model.Course;
import com.todotresde.interbanking.coursemanagement.model.Transaction;

import java.util.List;

/**
 * The interface Course service.
 */
public interface CourseService {

    /**
     * All courses list.
     *
     * @return the list
     */
    List<Course> allCourses();

    /**
     * Find course by id course.
     *
     * @param courseId the course id
     * @return the course
     */
    Course findCourseById(Long courseId);

    /**
     * Find transaction of user list.
     *
     * @param userId the user id
     * @return the list
     */
    List<Transaction> findTransactionOfUser(Long userId);

    /**
     * Find transaction of course list.
     *
     * @param courseId the course id
     * @return the list
     */
    List<Transaction> findTransactionOfCourse(Long courseId);

    /**
     * Save transaction.
     *
     * @param transaction the transaction
     * @return the transaction
     */
    Transaction save(Transaction transaction);
}
