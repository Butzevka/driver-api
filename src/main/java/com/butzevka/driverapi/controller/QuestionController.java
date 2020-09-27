package com.butzevka.driverapi.controller;

import com.butzevka.driverapi.dto.QuestionDto;
import com.butzevka.driverapi.model.Question;
import com.butzevka.driverapi.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final ModelMapper modelMapper;

    public QuestionDto convertToQuestionDto(Question question) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        QuestionDto questionDto = modelMapper.map(question, QuestionDto.class);
        return questionDto;
    }

    public Question convertToQuestionEntity(QuestionDto questionDto) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        Question question = modelMapper.map(questionDto, Question.class);
        return question;
    }

    @GetMapping("/question/list")
    public ResponseEntity<List<QuestionDto>> viewAllQuestions() {
        List<QuestionDto> questions = (questionService.findAllQuestions())
                .stream()
                .map(this::convertToQuestionDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @PostMapping("/question/list")
    public ResponseEntity<QuestionDto> addNewQuestion(@Valid @RequestBody QuestionDto newQuestion) {
        Question question = convertToQuestionEntity(newQuestion);
        questionService.addNewQuestion(question);
        return new ResponseEntity<>(newQuestion, HttpStatus.CREATED);
    }

    @GetMapping("/question/{id}")
    public ResponseEntity<QuestionDto> viewTrainingById(@PathVariable Long id) {
        Optional<QuestionDto> questionDto = questionService.findQuestionById(id).map(this::convertToQuestionDto);
        return questionDto.map(t -> new ResponseEntity<>(t, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/question/{id}")
    public ResponseEntity<HttpStatus> deleteQuestionById(@PathVariable Long id) {
        try {
            questionService.deleteQuestionById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/question/{id}")
    public ResponseEntity<QuestionDto> updateQuestion(@PathVariable Long id, @Valid @RequestBody QuestionDto questionDto) {
        Optional<Question> question = questionService.findQuestionById(id);
        if (question.isEmpty()) {
            log.error("Pytanie o id:" + id + " nie istnieje!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Question _question = question.get();
            questionService.addNewQuestion(_question);
            return new ResponseEntity<>(questionDto, HttpStatus.OK);
        }
    }
}
