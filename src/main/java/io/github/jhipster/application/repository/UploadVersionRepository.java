package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.UploadVersion;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the UploadVersion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UploadVersionRepository extends JpaRepository<UploadVersion, Long>, JpaSpecificationExecutor<UploadVersion> {
    @Query("select distinct upload_version from UploadVersion upload_version left join fetch upload_version.fileEntries")
    List<UploadVersion> findAllWithEagerRelationships();

    @Query("select upload_version from UploadVersion upload_version left join fetch upload_version.fileEntries where upload_version.id =:id")
    UploadVersion findOneWithEagerRelationships(@Param("id") Long id);

}
