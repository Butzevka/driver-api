package com.butzevka.driverapi.dto;

import lombok.Data;

@Data
public class AnswerDto {
    private Long id;
    private String answerImgSrc;
    private String answerBody;
    private boolean correctAnswer;
}
