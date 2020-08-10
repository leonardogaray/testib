package com.todotresde.interbanking.coursemanagement.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * The type Course.
 */
@Data
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "category")
    private String category;

    @Column(name = "publish_date")
    private LocalDate pusblishDate;
}
