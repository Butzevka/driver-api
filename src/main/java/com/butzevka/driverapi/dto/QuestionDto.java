package com.butzevka.driverapi.dto;

import lombok.Data;

import java.util.Set;

@Data
public class QuestionDto {
    private Long id;
    private String questionBody;
    private Set<AnswerDto> answers;
}
