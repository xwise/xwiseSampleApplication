package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.FileGroup;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the FileGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FileGroupRepository extends JpaRepository<FileGroup, Long>, JpaSpecificationExecutor<FileGroup> {

}
