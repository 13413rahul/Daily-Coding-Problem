package com.rahul.daily_coding_problem.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="preference")
@Data
public class Preference {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;

    private Long userId;
    private Long days;
    private String topic;
    @Enumerated(EnumType.STRING)
    private Level difficultyLevel;
}
