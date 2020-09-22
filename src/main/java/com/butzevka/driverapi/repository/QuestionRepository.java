package com.butzevka.driverapi.repository;

import com.butzevka.driverapi.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
