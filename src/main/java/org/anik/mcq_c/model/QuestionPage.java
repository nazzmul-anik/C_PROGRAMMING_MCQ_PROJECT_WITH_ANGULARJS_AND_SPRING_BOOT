package org.anik.mcq_c.model;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class QuestionPage {

    private List<MCQ> questions;
    private int totalQuestions;
}
