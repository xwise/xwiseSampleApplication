package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.BaselineEntry;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the BaselineEntry entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BaselineEntryRepository extends JpaRepository<BaselineEntry, Long> {
    @Query("select distinct baseline_entry from BaselineEntry baseline_entry left join fetch baseline_entry.baselineEntries")
    List<BaselineEntry> findAllWithEagerRelationships();

    @Query("select baseline_entry from BaselineEntry baseline_entry left join fetch baseline_entry.baselineEntries where baseline_entry.id =:id")
    BaselineEntry findOneWithEagerRelationships(@Param("id") Long id);

}
