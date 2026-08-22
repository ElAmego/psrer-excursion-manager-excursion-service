package org.zapovednik.groupservice.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zapovednik.groupservice.model.entity.Driver;

public interface DriverRepository extends JpaRepository<Driver, Long> {

}