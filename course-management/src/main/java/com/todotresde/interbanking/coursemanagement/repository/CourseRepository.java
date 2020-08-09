package com.todotresde.interbanking.coursemanagement.repository;

import com.todotresde.interbanking.coursemanagement.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByTitle(String title);
}
