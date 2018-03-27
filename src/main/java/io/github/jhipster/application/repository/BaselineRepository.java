package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Baseline;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Baseline entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BaselineRepository extends JpaRepository<Baseline, Long>, JpaSpecificationExecutor<Baseline> {

}
