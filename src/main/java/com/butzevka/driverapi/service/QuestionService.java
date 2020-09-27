package com.butzevka.driverapi.service;

import com.butzevka.driverapi.model.Question;
import com.butzevka.driverapi.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> findAllQuestions() { return questionRepository.findAll();}
    public Optional<Question> findQuestionById(Long id) {return  questionRepository.findById(id);}
    public Question addNewQuestion(Question question) {return questionRepository.save(question);}
    public Question updateQuestion(Question question) {return questionRepository.save(question);}
    public void deleteQuestionById(Long id) {questionRepository.deleteById(id);}
}
