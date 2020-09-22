package com.butzevka.driverapi.repository;

import com.butzevka.driverapi.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<Training, Long> {
}
