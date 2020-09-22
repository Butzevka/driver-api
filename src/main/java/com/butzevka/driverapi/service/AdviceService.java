package com.butzevka.driverapi.service;

import com.butzevka.driverapi.model.Advice;
import com.butzevka.driverapi.repository.AdviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdviceService {

    private final AdviceRepository adviceRepository;

    public Optional<Advice> findById(Long id) {return adviceRepository.findById(id);}
    public List<Advice> findAllAdvices() {return adviceRepository.findAll();}
    public void addAdvice(Advice advice) {adviceRepository.save(advice);}
    public void editAdvice(Advice advice) {adviceRepository.save(advice);}
    public void deleteAdvice(Advice advice) {adviceRepository.delete(advice);}
      void deleteAdviceById(Long id) {adviceRepository.deleteById(id);}
}
