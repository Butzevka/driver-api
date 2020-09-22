package com.butzevka.driverapi.dto;

import com.butzevka.driverapi.model.Question;
import com.butzevka.driverapi.model.Tag;
import lombok.Data;

import java.util.Set;

@Data
public class AdviceDto {

    private Long adviceId;
    private String adviceTitle;
    private String multimediaSrc;
    private String adviceText;
    private Set<Tag> tags;
    private Long likes;
    private Long shares;
    private String trainingTitle;
    private Set<Question> trainingQuestions;





}
