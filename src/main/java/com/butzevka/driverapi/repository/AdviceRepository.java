package com.butzevka.driverapi.repository;

import com.butzevka.driverapi.model.Advice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdviceRepository extends JpaRepository<Advice, Long> {
}
