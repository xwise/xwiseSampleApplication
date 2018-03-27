package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.BaselineDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Baseline.
 */
public interface BaselineService {

    /**
     * Save a baseline.
     *
     * @param baselineDTO the entity to save
     * @return the persisted entity
     */
    BaselineDTO save(BaselineDTO baselineDTO);

    /**
     * Get all the baselines.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<BaselineDTO> findAll(Pageable pageable);

    /**
     * Get the "id" baseline.
     *
     * @param id the id of the entity
     * @return the entity
     */
    BaselineDTO findOne(Long id);

    /**
     * Delete the "id" baseline.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
