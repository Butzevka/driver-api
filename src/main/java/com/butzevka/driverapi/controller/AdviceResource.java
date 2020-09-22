package com.butzevka.driverapi.controller;

import com.butzevka.driverapi.dto.AdviceDto;
import com.butzevka.driverapi.model.Advice;
import com.butzevka.driverapi.service.AdviceService;
import com.butzevka.driverapi.service.MapService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AdviceResource {

    private final AdviceService adviceService;
    private final MapService mapService;

    @GetMapping("/advice/{id}")
    public ResponseEntity<AdviceDto> viewAdvice(@PathVariable Long id) {
    Optional<AdviceDto> advice = mapService.findAdviceArticleById(id);
        return advice.map(a -> new ResponseEntity<>(a, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/advice/list")
    public ResponseEntity<List<AdviceDto>> viewAllAdvices() {
        List<AdviceDto> advices = mapService.findAllAdviceArticles();
        return new ResponseEntity<>(advices, HttpStatus.OK);
    }
//
//
//    @PostMapping("advice/list")
//    public ResponseEntity<> addNewAdvice(@Valid Advice newAdvice) {
//
//
//    }



}
