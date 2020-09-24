package com.butzevka.driverapi.controller;

import com.butzevka.driverapi.dto.AdviceDto;
import com.butzevka.driverapi.model.Advice;
import com.butzevka.driverapi.service.AdviceService;
import com.butzevka.driverapi.converter.AdviceConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AdviceController {

    private final AdviceConverter adviceConverter;
    private final AdviceService adviceService;

    @GetMapping("/advice/{id}")
    public ResponseEntity<AdviceDto> viewAdvice(@PathVariable Long id) {
    Optional<AdviceDto> advice = adviceConverter.findAdviceArticleById(id);
        return advice.map(a -> new ResponseEntity<>(a, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/advice/list")
    public ResponseEntity<List<AdviceDto>> viewAllAdvices() {
        List<AdviceDto> advices = adviceConverter.findAllAdviceArticles();
        return new ResponseEntity<>(advices, HttpStatus.OK);
    }

    @PostMapping("advice/list")
    public ResponseEntity<AdviceDto> addNewAdvice(@Valid @RequestBody AdviceDto newAdvice) {
        adviceService.addAdvice(adviceConverter.convertToAdviceEntity(newAdvice));
        return new ResponseEntity<>(newAdvice, HttpStatus.CREATED);
    }

    @DeleteMapping("advice/{id}")
    public ResponseEntity<HttpStatus> deleteAdvice(@PathVariable Long id) {
        try {
            adviceService.deleteAdvice(adviceService.findById(id).get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/advice/{id}")
    public ResponseEntity<AdviceDto> updateAdvice(@PathVariable Long id, @Valid @RequestBody AdviceDto adviceEdit) {

        Optional<Advice> advice = adviceService.findById(id);

        if (!advice.isPresent()) {
            log.error("Porada o id:" +id+ " nie istnieje!");
            return ResponseEntity.badRequest().build();
        }
        Advice adviceToBeEdit = advice.get();

        return new ResponseEntity<>(adviceConverter.convertToAdviceDto(adviceService.editAdvice(adviceToBeEdit)), HttpStatus.OK);
    }



}
