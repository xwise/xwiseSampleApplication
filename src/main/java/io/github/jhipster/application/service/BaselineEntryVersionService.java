package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.BaselineEntryVersionDTO;
import java.util.List;

/**
 * Service Interface for managing BaselineEntryVersion.
 */
public interface BaselineEntryVersionService {

    /**
     * Save a baselineEntryVersion.
     *
     * @param baselineEntryVersionDTO the entity to save
     * @return the persisted entity
     */
    BaselineEntryVersionDTO save(BaselineEntryVersionDTO baselineEntryVersionDTO);

    /**
     * Get all the baselineEntryVersions.
     *
     * @return the list of entities
     */
    List<BaselineEntryVersionDTO> findAll();

    /**
     * Get the "id" baselineEntryVersion.
     *
     * @param id the id of the entity
     * @return the entity
     */
    BaselineEntryVersionDTO findOne(Long id);

    /**
     * Delete the "id" baselineEntryVersion.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
