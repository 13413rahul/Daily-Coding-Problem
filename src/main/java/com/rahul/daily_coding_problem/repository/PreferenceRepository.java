package com.rahul.daily_coding_problem.repository;

import com.rahul.daily_coding_problem.model.Preference;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferenceRepository extends JpaRepository<Preference, Long> {
    @Transactional
    void deleteAllByUserId(Long userId);

    int countByUserId(Long userId);

    Preference findByUserIdAndDays(Long userId, Long days);
}
