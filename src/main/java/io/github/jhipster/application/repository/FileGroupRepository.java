package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.FileGroup;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the FileGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FileGroupRepository extends JpaRepository<FileGroup, Long>, JpaSpecificationExecutor<FileGroup> {
    @Query("select distinct file_group from FileGroup file_group left join fetch file_group.fileEntries")
    List<FileGroup> findAllWithEagerRelationships();

    @Query("select file_group from FileGroup file_group left join fetch file_group.fileEntries where file_group.id =:id")
    FileGroup findOneWithEagerRelationships(@Param("id") Long id);

}
