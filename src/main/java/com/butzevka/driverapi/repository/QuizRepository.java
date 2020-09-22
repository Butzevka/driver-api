package com.butzevka.driverapi.repository;

import com.butzevka.driverapi.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
