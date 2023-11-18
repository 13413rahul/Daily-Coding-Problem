package com.rahul.daily_coding_problem.service;

import com.rahul.daily_coding_problem.model.Level;
import com.rahul.daily_coding_problem.model.Problem;
import com.rahul.daily_coding_problem.repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
public class updateDatabaseService {

    @Autowired
    ProblemRepository problemRepository;

    public String saveProblemToDB(MultipartFile file) throws IOException {

        try {

            String line;
            InputStream is = file.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                Problem problem = getProblem(line);
                problemRepository.save(problem);
//                System.out.println(problem);

            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

//        System.out.println(1);

        return "file uploaded SuccessFully";
    }

    private Problem getProblem(String line) {
        String[] str = line.split("\\*");
        Problem problem = new Problem();
        problem.setTopic(str[0]);
        problem.setPlatform(str[1]);
        if (str[2].equals("EASY")) {
            problem.setDifficultyLevel(Level.EASY);
        } else if (str[2].equals("MEDIUM")) {
            problem.setDifficultyLevel(Level.MEDIUM);
        } else {
            problem.setDifficultyLevel(Level.HARD);
        }
        problem.setProblemName(str[3]);
        problem.setProblemLink(str[4]);

        return problem;
    }

}
