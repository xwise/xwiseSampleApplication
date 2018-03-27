package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Baseline;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the Baseline entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BaselineRepository extends JpaRepository<Baseline, Long>, JpaSpecificationExecutor<Baseline> {
    @Query("select distinct baseline from Baseline baseline left join fetch baseline.fileEntries")
    List<Baseline> findAllWithEagerRelationships();

    @Query("select baseline from Baseline baseline left join fetch baseline.fileEntries where baseline.id =:id")
    Baseline findOneWithEagerRelationships(@Param("id") Long id);

}
