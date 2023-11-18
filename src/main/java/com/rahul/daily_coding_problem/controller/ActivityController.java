package com.rahul.daily_coding_problem.controller;

import com.rahul.daily_coding_problem.request.SubscribeRequest;
import com.rahul.daily_coding_problem.service.ActivityService;
import com.rahul.daily_coding_problem.service.updateDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class ActivityController {

    @Autowired
    ActivityService activityService;
    @Autowired
    updateDatabaseService updateDatabaseService;

    @PostMapping("/uploadFile")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
//        file = new File("C:\\Users\\Rahul kumar\\Desktop\\problem.txt");
        String response = "";
        try {

            response = updateDatabaseService.saveProblemToDB(file);
        }catch (Exception e) {

            return new ResponseEntity<>("UnSuccessFul", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/topics")
    public ResponseEntity<List<String>> getAllTopics() {
        List<String> response;
        try {
//            System.out.println("getAllTopics");
            response = activityService.getAllTopics();
        }catch (Exception e) {

            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribe(@RequestBody SubscribeRequest subscribeRequest) {
        String response = "";
        try {
//            System.out.println(subscribeRequest);
            response = activityService.subscribe(subscribeRequest);
        }catch (Exception e) {

            return new ResponseEntity<>("UnSuccessFul", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/unsubscribe/{email}")
    public ResponseEntity<String> unsubscribe(@PathVariable("email") String email) {
        String response = "";
        try {
//            System.out.println(email);
            response = activityService.unsubscribe(email);
        }catch (Exception e) {

            return new ResponseEntity<>("UnSuccessFul", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
