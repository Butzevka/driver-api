package com.butzevka.driverapi.dto;

import lombok.Data;

import java.util.Set;

@Data
public class TrainingDto {
    private Long id;
    private String trainingTitle;
    private Set<QuestionDto> trainingQuestions;

}
