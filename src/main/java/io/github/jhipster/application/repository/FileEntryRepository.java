package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.FileEntry;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the FileEntry entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FileEntryRepository extends JpaRepository<FileEntry, Long>, JpaSpecificationExecutor<FileEntry> {

}
