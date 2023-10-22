package com.rahul.daily_coding_problem.repository;

import com.rahul.daily_coding_problem.model.Level;
import com.rahul.daily_coding_problem.model.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Long> {
    List<Problem> findAllByTopicAndDifficultyLevel(String topic, Level difficultyLevel);

    List<Problem> findAllByTopic(String topic);

    List<Problem> findAllByDifficultyLevel(Level difficultyLevel);

    @Query(value = "SELECT distinct topic FROM problem", nativeQuery = true)
    List<String> findAllTopics();
}
