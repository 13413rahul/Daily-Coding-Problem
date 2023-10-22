package com.rahul.daily_coding_problem.request;

import com.rahul.daily_coding_problem.model.Level;
import lombok.Data;

@Data
public class PreferenceRequest {
    private String topic;
    private String difficultyLevel;

}
