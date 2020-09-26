package com.butzevka.driverapi.service;

import com.butzevka.driverapi.model.Training;
import com.butzevka.driverapi.repository.TrainingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrainingService {

    private final TrainingRepository trainingRepository;

    public Optional<Training> findById(Long id) {return trainingRepository.findById(id);}
    public List<Training> findAllTrainings() {return trainingRepository.findAll();}
    public Training addTraining(Training training) {return trainingRepository.save(training);}
    public Training editTraining(Training training) {return trainingRepository.save(training);}
    public void deleteTraining(Training training) {trainingRepository.delete(training);}
    public void deleteTrainingById(Long id) {trainingRepository.deleteById(id);}

}
