package org.anik.mcq_c.controller;

import org.anik.mcq_c.model.Answer;
import org.anik.mcq_c.model.MCQ;
import org.anik.mcq_c.model.QuestionPage;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MCQController {

    private List<MCQ> mcqList = new ArrayList<>();

    public MCQController(){
        mcqList.add(new MCQ(1, "What is the size of int in C?", new String[]{"2 bytes", "4 bytes", "8 bytes", "Depends on compiler"}, "Depends on compiler"));
        mcqList.add(new MCQ(2, "Which of the following is a logical operator?", new String[]{"&&", "||", "!", "All of the above"}, "All of the above"));
        mcqList.add(new MCQ(3, "What is the size of int in C?", new String[]{"2 bytes", "4 bytes", "8 bytes", "Depends on compiler"}, "Depends on compiler"));
        mcqList.add(new MCQ(4, "Which of the following is a logical operator?", new String[]{"&&", "||", "!", "All of the above"}, "All of the above"));
        mcqList.add(new MCQ(5, "What is the size of int in C?", new String[]{"2 bytes", "4 bytes", "8 bytes", "Depends on compiler"}, "Depends on compiler"));
        mcqList.add(new MCQ(6, "Which of the following is a logical operator?", new String[]{"&&", "||", "!", "All of the above"}, "All of the above"));
        mcqList.add(new MCQ(7, "What is the size of int in C?", new String[]{"2 bytes", "4 bytes", "8 bytes", "Depends on compiler"}, "Depends on compiler"));
        mcqList.add(new MCQ(8, "Which of the following is a logical operator?", new String[]{"&&", "||", "!", "All of the above"}, "All of the above"));
        mcqList.add(new MCQ(9, "What is the size of int in C?", new String[]{"2 bytes", "4 bytes", "8 bytes", "Depends on compiler"}, "Depends on compiler"));
        mcqList.add(new MCQ(10, "Which of the following is a logical operator?", new String[]{"&&", "||", "!", "All of the above"}, "All of the above"));
        mcqList.add(new MCQ(11, "What is the size of int in C?", new String[]{"2 bytes", "4 bytes", "8 bytes", "Depends on compiler"}, "Depends on compiler"));
//        mcqList.add(new MCQ(12, "Which of the following is a logical operator?", new String[]{"&&", "||", "!", "All of the above"}, "All of the above"));
//        mcqList.add(new MCQ(13, "What is the size of int in C?", new String[]{"2 bytes", "4 bytes", "8 bytes", "Depends on compiler"}, "Depends on compiler"));
//        mcqList.add(new MCQ(14, "Which of the following is a logical operator?", new String[]{"&&", "||", "!", "All of the above"}, "All of the above"));
//
//        mcqList.add(new MCQ(15, "What is the size of int in C?", new String[]{"2 bytes", "4 bytes", "8 bytes", "Depends on compiler"}, "Depends on compiler"));
//        mcqList.add(new MCQ(16, "Which of the following is a logical operator?", new String[]{"&&", "||", "!", "All of the above"}, "All of the above"));
//        mcqList.add(new MCQ(17, "What is the size of int in C?", new String[]{"2 bytes", "4 bytes", "8 bytes", "Depends on compiler"}, "Depends on compiler"));
//        mcqList.add(new MCQ(18, "Which of the following is a logical operator?", new String[]{"&&", "||", "!", "All of the above"}, "All of the above"));
//        mcqList.add(new MCQ(19, "What is the size of int in C?", new String[]{"2 bytes", "4 bytes", "8 bytes", "Depends on compiler"}, "Depends on compiler"));
//        mcqList.add(new MCQ(20, "Which of the following is a logical operator?", new String[]{"&&", "||", "!", "All of the above"}, "All of the above"));
//
//        mcqList.add(new MCQ(21, "What is the size of int in C?", new String[]{"2 bytes", "4 bytes", "8 bytes", "Depends on compiler"}, "Depends on compiler"));
//        mcqList.add(new MCQ(22, "Which of the following is a logical operator?", new String[]{"&&", "||", "!", "All of the above"}, "All of the above"));
//        mcqList.add(new MCQ(23, "What is the size of int in C?", new String[]{"2 bytes", "4 bytes", "8 bytes", "Depends on compiler"}, "Depends on compiler"));
//        mcqList.add(new MCQ(24, "Which of the following is a logical operator?", new String[]{"&&", "||", "!", "All of the above"}, "All of the above"));
//        mcqList.add(new MCQ(25, "What is the size of int in C?", new String[]{"2 bytes", "4 bytes", "8 bytes", "Depends on compiler"}, "Depends on compiler"));
//        mcqList.add(new MCQ(26, "Which of the following is a logical operator?", new String[]{"&&", "||", "!", "All of the above"}, "All of the above"));
//
//        mcqList.add(new MCQ(27, "What is the size of int in C?", new String[]{"2 bytes", "4 bytes", "8 bytes", "Depends on compiler"}, "Depends on compiler"));
//        mcqList.add(new MCQ(28, "Which of the following is a logical operator?", new String[]{"&&", "||", "!", "All of the above"}, "All of the above"));
//        mcqList.add(new MCQ(29, "What is the size of int in C?", new String[]{"2 bytes", "4 bytes", "8 bytes", "Depends on compiler"}, "Depends on compiler"));
//        mcqList.add(new MCQ(30, "Which of the following is a logical operator?", new String[]{"&&", "||", "!", "All of the above"}, "All of the above"));
//        mcqList.add(new MCQ(31, "What is the size of int in C?", new String[]{"2 bytes", "4 bytes", "8 bytes", "Depends on compiler"}, "Depends on compiler"));
//        mcqList.add(new MCQ(32, "Which of the following is a logical operator?", new String[]{"&&", "||", "!", "All of the above"}, "All of the above"));

    }

    @GetMapping("/questions")
    public QuestionPage getQuestions(@RequestParam int count, @RequestParam int page){
        int start = (page - 1) * count;
        int end = Math.min(start+count, mcqList.size());

        List<MCQ> questions = mcqList.subList(start, end);
        return new QuestionPage(questions, mcqList.size());
    }

    @PostMapping("/submit")
    public int submitAnswers(@RequestBody List<Answer> answers){
        int score = 0;

        for(Answer answer : answers){
            MCQ question = mcqList.get(answer.getId()-1);
            if(question.getAnswer().equals(answer.getSelectedOption())){
                score++;
            }
        }

        return score;
    }
}
