package com.todotresde.interbanking.coursemanagement.repository;

import com.todotresde.interbanking.coursemanagement.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * The interface Course repository.
 */
public interface CourseRepository extends JpaRepository<Course, Long> {

    /**
     * Find by title optional.
     *
     * @param title the title
     * @return the optional
     */
    Optional<Course> findByTitle(String title);
}
