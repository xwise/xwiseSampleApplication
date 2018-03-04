package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.FileGroupDTO;
import java.util.List;

/**
 * Service Interface for managing FileGroup.
 */
public interface FileGroupService {

    /**
     * Save a fileGroup.
     *
     * @param fileGroupDTO the entity to save
     * @return the persisted entity
     */
    FileGroupDTO save(FileGroupDTO fileGroupDTO);

    /**
     * Get all the fileGroups.
     *
     * @return the list of entities
     */
    List<FileGroupDTO> findAll();

    /**
     * Get the "id" fileGroup.
     *
     * @param id the id of the entity
     * @return the entity
     */
    FileGroupDTO findOne(Long id);

    /**
     * Delete the "id" fileGroup.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
