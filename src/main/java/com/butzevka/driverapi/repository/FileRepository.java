package com.butzevka.driverapi.repository;

import com.butzevka.driverapi.model.DBFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<DBFile, Long> {
}
