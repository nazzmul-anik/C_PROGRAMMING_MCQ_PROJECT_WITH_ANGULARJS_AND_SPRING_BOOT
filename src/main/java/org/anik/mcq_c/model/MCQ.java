package org.anik.mcq_c.model;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class MCQ {

    private int id;
    private String questions;
    private String[] options;
    private String answer;
}
