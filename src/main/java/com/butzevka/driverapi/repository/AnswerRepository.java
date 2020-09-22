package com.butzevka.driverapi.repository;

import com.butzevka.driverapi.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
