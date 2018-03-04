package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.FileEntry;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the FileEntry entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FileEntryRepository extends JpaRepository<FileEntry, Long>, JpaSpecificationExecutor<FileEntry> {
    @Query("select distinct file_entry from FileEntry file_entry left join fetch file_entry.baselineEntryVersions")
    List<FileEntry> findAllWithEagerRelationships();

    @Query("select file_entry from FileEntry file_entry left join fetch file_entry.baselineEntryVersions where file_entry.id =:id")
    FileEntry findOneWithEagerRelationships(@Param("id") Long id);

}
