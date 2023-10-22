package com.rahul.daily_coding_problem.service;

import com.rahul.daily_coding_problem.request.SubscribeRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActivityService {

    String subscribe(SubscribeRequest subscribeRequest);

    String unsubscribe(String email);

    List<String> getAllTopics();
}
