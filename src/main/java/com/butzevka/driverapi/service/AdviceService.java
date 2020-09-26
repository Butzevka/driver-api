package com.butzevka.driverapi.service;

import com.butzevka.driverapi.model.Advice;
import com.butzevka.driverapi.repository.AdviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdviceService {

    private final AdviceRepository adviceRepository;

    public Optional<Advice> findById(Long id) {return adviceRepository.findById(id);}
    public List<Advice> findAllAdvices() {return adviceRepository.findAll();}
    public Advice addAdvice(Advice advice) {return adviceRepository.save(advice);}
    public Advice editAdvice(Advice advice) {return adviceRepository.save(advice);}
    public void deleteAdvice(Advice advice) {adviceRepository.delete(advice);}
    public void deleteAdviceById(Long id) {adviceRepository.deleteById(id);}
}
