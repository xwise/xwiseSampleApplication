package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.BaselineEntryVersion;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the BaselineEntryVersion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BaselineEntryVersionRepository extends JpaRepository<BaselineEntryVersion, Long> {

}
