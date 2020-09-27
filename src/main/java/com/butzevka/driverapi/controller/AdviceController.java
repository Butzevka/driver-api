package com.butzevka.driverapi.controller;

import com.butzevka.driverapi.dto.AdviceDto;
import com.butzevka.driverapi.model.Advice;
import com.butzevka.driverapi.service.AdviceService;
import com.butzevka.driverapi.converter.AdviceConverter;
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

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/advice")
public class AdviceController {

    private final AdviceService adviceService;
    private final ModelMapper modelMapper;


    public AdviceDto convertToAdviceDto(Advice advice) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        AdviceDto adviceDto = modelMapper.map(advice, AdviceDto.class);
        return adviceDto;
    }

    public Advice convertToAdviceEntity(AdviceDto adviceDto) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        Advice advice = modelMapper.map(adviceDto, Advice.class);
        return advice;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdviceDto> viewAdvice(@PathVariable Long id) {
        Optional<AdviceDto> advice = adviceService.findById(id).map(this::convertToAdviceDto);
        return advice.map(a -> new ResponseEntity<>(a, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/list")
    public ResponseEntity<List<AdviceDto>> viewAllAdvices() {
        List<AdviceDto> advices = (adviceService.findAllAdvices())
                .stream()
                .map(this::convertToAdviceDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(advices, HttpStatus.OK);
    }

    @PostMapping("/list")
    public ResponseEntity<AdviceDto> addNewAdvice(@Valid @RequestBody AdviceDto newAdvice) {
        Advice adviceEntity = convertToAdviceEntity(newAdvice);
        adviceService.addAdvice(adviceEntity);
        return new ResponseEntity<>(newAdvice, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAdvice(@PathVariable Long id) {
        try {
            adviceService.deleteAdviceById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdviceDto> updateAdvice(@PathVariable Long id, @Valid @RequestBody AdviceDto adviceEdit) {
        Optional<Advice> advice = adviceService.findById(id);
        if (advice.isEmpty()) {
            log.error("Porada o id:" + id + " nie istnieje!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Advice _advice = advice.get();
            adviceService.editAdvice(_advice);
            return new ResponseEntity<>(adviceEdit, HttpStatus.OK);
        }
    }

    @GetMapping("/{tagName}")
    public ResponseEntity<List<Advice>> findAdviceListByTag(@PathVariable String tagName) {
        return new ResponseEntity<>(adviceService.findAdvicesByTag(tagName), HttpStatus.OK);

    }
}