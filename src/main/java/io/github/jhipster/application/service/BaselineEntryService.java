package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.BaselineEntryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing BaselineEntry.
 */
public interface BaselineEntryService {

    /**
     * Save a baselineEntry.
     *
     * @param baselineEntryDTO the entity to save
     * @return the persisted entity
     */
    BaselineEntryDTO save(BaselineEntryDTO baselineEntryDTO);

    /**
     * Get all the baselineEntries.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<BaselineEntryDTO> findAll(Pageable pageable);

    /**
     * Get the "id" baselineEntry.
     *
     * @param id the id of the entity
     * @return the entity
     */
    BaselineEntryDTO findOne(Long id);

    /**
     * Delete the "id" baselineEntry.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
