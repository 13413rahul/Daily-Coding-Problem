package com.rahul.daily_coding_problem.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="problem")
public class Problem {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String topic;
    @Enumerated(EnumType.STRING)
    private Level difficultyLevel;
    private String problemName;
    private String problemLink;
    private String platform;

}
