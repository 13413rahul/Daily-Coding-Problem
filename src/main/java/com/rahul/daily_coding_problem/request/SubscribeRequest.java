package com.rahul.daily_coding_problem.request;

import lombok.Data;

import java.util.List;

@Data
public class SubscribeRequest {
    private String email;
    private List<PreferenceRequest> daysPreference;
}
