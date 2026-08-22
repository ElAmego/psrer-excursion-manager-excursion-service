package org.zapovednik.groupservice.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zapovednik.groupservice.model.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {

}