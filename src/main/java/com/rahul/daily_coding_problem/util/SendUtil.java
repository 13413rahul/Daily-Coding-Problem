package com.rahul.daily_coding_problem.util;

import com.rahul.daily_coding_problem.model.*;
import com.rahul.daily_coding_problem.repository.PreferenceRepository;
import com.rahul.daily_coding_problem.repository.ProblemRepository;
import com.rahul.daily_coding_problem.repository.UserRepository;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Component
public class SendUtil {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PreferenceRepository preferenceRepository;

    @Autowired
    ProblemRepository problemRepository;

    @Autowired
    MessengerUtil messengerUtil;

    @Scheduled(cron = "${com.scheduled.cron}", zone = "${com.scheduled.zone}")
    public void sendNotificationToAllUser() throws MessagingException, TemplateException, IOException {
//        System.out.println("*********************** sendNotificationToAllUser **************************");
        Map<String, Problem> problemMapping = new HashMap<>();
        List<User> users = getAllUsers();
        for(User user : users) {
            String email = user.getEmail();
            Long userId = user.getId();
            Long days = (user.getDays() + 1) % countNoOfDays(userId);

            Preference preference = getPreferencesByUserIdAndDays(userId, days + 1);
            List<Problem> problems;
            String keyMap = "";
            if(preference == null) {
//                System.out.println("null");
                problems = getAllProblems();
            }else {
//                System.out.println(preference);
                String topic = preference.getTopic();
                Level difficultyLevel = preference.getDifficultyLevel();

                if(difficultyLevel == null) {
                    problems = getAllProblemsByTopic(topic);
                }else if(topic == null){
                    problems = getAllProblemsByDifficultyLevel(difficultyLevel);
                }else {
                    keyMap = topic +"$" + difficultyLevel.toString();
                    if(problemMapping.containsKey(keyMap)) {
                        Model model = getModel(email, problemMapping.get(keyMap));
                        messengerUtil.sendEmail(model);
                        user.setDays(user.getDays() + 1);
                        userRepository.save(user);
                        return;
                    }
                    problems = getAllProblemsByTopicAndDifficultyLevel(topic, difficultyLevel);
                }
            }

            Problem problem = getRandomProblem(problems);
            problemMapping.put(keyMap, problem);
//            System.out.println(problem);
            Model model = getModel(email, problem);
//            System.out.println(model);
            messengerUtil.sendEmail(model);
            user.setDays(user.getDays() + 1);
            userRepository.save(user);
        }
        problemMapping.clear();
    }

    @Scheduled(cron = "@weekly", zone = "${com.scheduled.zone}")
    public void sendReport() throws MessagingException, TemplateException, IOException {
//        System.out.println("sendReport");
        List<User> users = getAllUsers();
        Model model = new Model();
        model.setTo("rahul13413kr@gmail.com");
        model.setSubject("Report of subscribed User");
        model.setTemplate("report.ftl");

        Map<String, String> binder = new HashMap<>();
        binder.put("users", users.toString());

        model.setBinder(binder);
        messengerUtil.sendEmail(model);
    }

    private Model getModel(String email, Problem problem) {
//        System.out.println("email " + email + "problem " + problem);
        Model model = new Model();
        model.setTo(email);
        model.setSubject("Daily Coding Problem[" + problem.getDifficultyLevel() + "]");
        model.setTemplate("email.ftl");

        Map<String, String> binder = new HashMap<>();
        binder.put("problemName", problem.getProblemName());
        binder.put("problemLink", problem.getProblemLink());

        model.setBinder(binder);
        return model;
    }

    private Problem getRandomProblem(List<Problem> problems) {
//        System.out.println(problems);
        Random rand = new Random();
        int index = rand.nextInt(problems.size());

        return problems.get(index);
    }

    private List<Problem> getAllProblemsByDifficultyLevel(Level difficultyLevel) {
//        System.out.println("difficultyLevel " + difficultyLevel);
        return problemRepository.findAllByDifficultyLevel(difficultyLevel);
    }

    private List<Problem> getAllProblemsByTopic(String topic) {
//        System.out.println("topic " + topic);
        return problemRepository.findAllByTopic(topic);
    }

    private List<Problem> getAllProblemsByTopicAndDifficultyLevel(String topic, Level difficultyLevel) {
//        System.out.println("topic " + topic + "difficultyLevel " + difficultyLevel);
        return problemRepository.findAllByTopicAndDifficultyLevel(topic, difficultyLevel);
    }

    private List<Problem> getAllProblems() {
        return problemRepository.findAll();
    }

    private Preference getPreferencesByUserIdAndDays(Long userId, Long days) {
//        System.out.println("userId " + userId + "days " + days);
        return preferenceRepository.findByUserIdAndDays(userId, days);
    }

    private List<User> getAllUsers() {
        return userRepository.findAll();
    }

    private int countNoOfDays(Long userId) {
//        System.out.println("userId " + userId);
        return  Math.max(preferenceRepository.countByUserId(userId), 1);
    }

}
