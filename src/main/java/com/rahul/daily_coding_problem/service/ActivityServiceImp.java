package com.rahul.daily_coding_problem.service;

import com.rahul.daily_coding_problem.model.Level;
import com.rahul.daily_coding_problem.model.Preference;
import com.rahul.daily_coding_problem.model.User;
import com.rahul.daily_coding_problem.repository.PreferenceRepository;
import com.rahul.daily_coding_problem.repository.ProblemRepository;
import com.rahul.daily_coding_problem.repository.UserRepository;
import com.rahul.daily_coding_problem.request.SubscribeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ActivityServiceImp implements ActivityService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PreferenceRepository preferenceRepository;
    @Autowired
    ProblemRepository problemRepository;

    @Override
    public String subscribe(SubscribeRequest subscribeRequest) {
        User user = userRepository.findByEmail(subscribeRequest.getEmail());
        boolean flag = false;
        if(user == null) {
            user = saveUserInDB(subscribeRequest.getEmail());
            flag = true;
        }else {
            deletePreference(user.getId());
        }
        savePreference(user, subscribeRequest);

        if(flag) {
            return "Subscribed for " +  subscribeRequest.getEmail() + " SuccessFully";
        }else {
            return "Preference Changed for " + subscribeRequest.getEmail() + " SuccessFully";
        }

    }

    @Override
    public String unsubscribe(String email) {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            System.out.println("no User");
            return "No Subscribed User of " + email;
        }else {
            deletePreference(user.getId());
            deleteUser(user.getId());

            return "Unsubscribed for " +  email + " SuccessFully";
        }
    }

    @Override
    public List<String> getAllTopics() {
        List<String> problems = problemRepository.findAllTopics();
        return problems;
    }

    private void deletePreference(Long userId) {
//        preferenceRepository.de
        preferenceRepository.deleteAllByUserId(userId);
    }

    private void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    private User saveUserInDB(String email) {
        User u = new User();
        u.setEmail(email);
        u.setDays(0);
        return userRepository.save(u);
    }

    private void savePreference(User user, SubscribeRequest subscribeRequest) {

        for (int day = 0; day < subscribeRequest.getDaysPreference().size(); day++) {
            Preference preference = new Preference();
            preference.setUserId(user.getId());
            preference.setDays(1l * day + 1);
            preference.setTopic(subscribeRequest.getDaysPreference().get(day).getTopic());
            String level = subscribeRequest.getDaysPreference().get(day).getDifficultyLevel();
            if (level.equals("EASY")) {
                preference.setDifficultyLevel(Level.EASY);
            } else if (level.equals("MEDIUM")) {
                preference.setDifficultyLevel(Level.MEDIUM);
            } else {
                preference.setDifficultyLevel(Level.HARD);
            }

            preferenceRepository.save(preference);
        }

    }
}
