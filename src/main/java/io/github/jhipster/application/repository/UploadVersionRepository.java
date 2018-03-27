package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.UploadVersion;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the UploadVersion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UploadVersionRepository extends JpaRepository<UploadVersion, Long>, JpaSpecificationExecutor<UploadVersion> {

}
