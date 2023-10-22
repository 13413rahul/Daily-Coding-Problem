package com.rahul.daily_coding_problem.model;

import lombok.Data;

import java.util.Map;

@Data
public class Model {
    private String to;
    private String subject;
    private String template;
    private Map<String, String> binder;
}
