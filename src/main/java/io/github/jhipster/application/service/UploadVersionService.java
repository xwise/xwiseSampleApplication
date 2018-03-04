package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.UploadVersionDTO;
import java.util.List;

/**
 * Service Interface for managing UploadVersion.
 */
public interface UploadVersionService {

    /**
     * Save a uploadVersion.
     *
     * @param uploadVersionDTO the entity to save
     * @return the persisted entity
     */
    UploadVersionDTO save(UploadVersionDTO uploadVersionDTO);

    /**
     * Get all the uploadVersions.
     *
     * @return the list of entities
     */
    List<UploadVersionDTO> findAll();

    /**
     * Get the "id" uploadVersion.
     *
     * @param id the id of the entity
     * @return the entity
     */
    UploadVersionDTO findOne(Long id);

    /**
     * Delete the "id" uploadVersion.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
