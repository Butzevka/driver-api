package com.butzevka.driverapi.repository;

import com.butzevka.driverapi.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
