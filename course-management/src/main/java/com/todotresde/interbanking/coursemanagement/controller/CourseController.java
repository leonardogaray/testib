package com.todotresde.interbanking.coursemanagement.controller;

import com.todotresde.interbanking.coursemanagement.intercomm.UserClient;
import com.todotresde.interbanking.coursemanagement.model.Transaction;
import com.todotresde.interbanking.coursemanagement.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Course controller.
 */
@RestController
public class CourseController {
    @Autowired
    private UserClient userClient;

    @Autowired
    private CourseService courseService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private Environment env;

    @Value("$(spring.application.name)")
    private String serviceId;

    /**
     * Get port string.
     *
     * @return the string
     */
    @GetMapping("/service/port")
    public String getPort(){
        return "Port: " + env.getProperty("local.server.port");
    }

    /**
     * Get instances response entity.
     *
     * @return the response entity
     */
    @GetMapping("/service/instances")
    public ResponseEntity<?> getInstances(){
        return new ResponseEntity<>(discoveryClient.getInstances(serviceId), HttpStatus.OK);
    }

    /**
     * Find transactions of users response entity.
     *
     * @param userId the user id
     * @return the response entity
     */
    @GetMapping("/service/user/{userId}")
    public ResponseEntity<?> findTransactionsOfUsers(@PathVariable Long userId){
        return ResponseEntity.ok(courseService.findTransactionOfUser(userId));
    }

    /**
     * Find all courses response entity.
     *
     * @return the response entity
     */
    @GetMapping("/service/all")
    public ResponseEntity<?> findAllCourses(){
        return ResponseEntity.ok(courseService.allCourses());
    }

    /**
     * Save transaction response entity.
     *
     * @param transaction the transaction
     * @return the response entity
     */
    @PostMapping("/service/enroll")
    public ResponseEntity<?> saveTransaction(@RequestBody Transaction transaction){
        transaction.setDateOfIssue(LocalDateTime.now());
        transaction.setCourse(courseService.findCourseById(transaction.getCourse().getId()));
        return new ResponseEntity<>(courseService.save(transaction), HttpStatus.CREATED);
    }

    /**
     * Find transactions of course response entity.
     *
     * @param courseId the course id
     * @return the response entity
     */
    @GetMapping("/service/course/{courseId}")
    public ResponseEntity<?> findTransactionsOfCourse(@PathVariable Long courseId){
        List<Transaction> transactionList = courseService.findTransactionOfCourse(courseId);
        if(CollectionUtils.isEmpty(transactionList)){
            return ResponseEntity.notFound().build();
        }
        List<Long> userIdList = transactionList.parallelStream().map(t -> t.getUserId()).collect(Collectors.toList());
        List<String> students = userClient.getUserNames(userIdList);
        return ResponseEntity.ok(students);
    }
}
