package com.butzevka.driverapi.repository;

import com.butzevka.driverapi.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
