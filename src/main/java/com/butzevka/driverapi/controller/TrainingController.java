package com.butzevka.driverapi.controller;

import com.butzevka.driverapi.dto.AdviceDto;
import com.butzevka.driverapi.dto.TrainingDto;
import com.butzevka.driverapi.model.Advice;
import com.butzevka.driverapi.model.Training;
import com.butzevka.driverapi.service.TrainingService;
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
public class TrainingController {

private final TrainingService trainingService;
private final ModelMapper modelMapper;

    public TrainingDto convertToTrainingDto(Training training) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        TrainingDto trainingDto = modelMapper.map(training, TrainingDto.class);
        return trainingDto;
    }

    public Training convertToTrainingEntity(TrainingDto trainingDto) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        Training training = modelMapper.map(trainingDto, Training.class);
        return training;
    }

    @GetMapping("/training/list")
    public ResponseEntity<List<TrainingDto>> viewAllTrainings() {
        List<TrainingDto> trainings = (trainingService.findAllTrainings())
                .stream()
                .map(this::convertToTrainingDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(trainings, HttpStatus.OK);
    }

    @PostMapping("/training/list")
    public ResponseEntity<TrainingDto> addNewTraining(@Valid @RequestBody TrainingDto newTraining) {
        Training trainingEntity = convertToTrainingEntity(newTraining);
        trainingService.addTraining(trainingEntity);
        return new ResponseEntity<>(newTraining, HttpStatus.CREATED);
    }

    @GetMapping("/training/{id}")
    public ResponseEntity<TrainingDto> viewTrainingById(@PathVariable Long id) {
        Optional<TrainingDto> trainingDto = trainingService.findById(id).map(this::convertToTrainingDto);
        return trainingDto.map(t -> new ResponseEntity<>(t, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/training/{id}")
    public ResponseEntity<HttpStatus> deleteTrainingById(@PathVariable Long id) {
        try {
            trainingService.deleteTrainingById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/training/{id}")
    public ResponseEntity<TrainingDto> updateTraining(@PathVariable Long id, @Valid @RequestBody TrainingDto trainingDto) {
        Optional<Training> training = trainingService.findById(id);
        if (training.isEmpty()) {
            log.error("Trening o id:" + id + " nie istnieje!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Training _training = training.get();
            trainingService.addTraining(_training);
            return new ResponseEntity<>(trainingDto, HttpStatus.OK);
        }
    }
}
