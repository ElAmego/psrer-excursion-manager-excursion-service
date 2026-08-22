package org.zapovednik.groupservice.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zapovednik.groupservice.model.entity.Route;

public interface RouteRepository extends JpaRepository<Route, Long> {

}