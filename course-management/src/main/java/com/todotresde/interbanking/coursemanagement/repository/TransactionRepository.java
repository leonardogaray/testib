package com.todotresde.interbanking.coursemanagement.repository;

import com.todotresde.interbanking.coursemanagement.model.Course;
import com.todotresde.interbanking.coursemanagement.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * The interface Transaction repository.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /**
     * Find all by user id list.
     *
     * @param userId the user id
     * @return the list
     */
    List<Transaction> findAllByUserId(Long userId);

    /**
     * Find all by course id list.
     *
     * @param courseId the course id
     * @return the list
     */
    List<Transaction> findAllByCourseId(Long courseId);

}
