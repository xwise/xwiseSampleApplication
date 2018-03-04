package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.FileEntryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing FileEntry.
 */
public interface FileEntryService {

    /**
     * Save a fileEntry.
     *
     * @param fileEntryDTO the entity to save
     * @return the persisted entity
     */
    FileEntryDTO save(FileEntryDTO fileEntryDTO);

    /**
     * Get all the fileEntries.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FileEntryDTO> findAll(Pageable pageable);

    /**
     * Get the "id" fileEntry.
     *
     * @param id the id of the entity
     * @return the entity
     */
    FileEntryDTO findOne(Long id);

    /**
     * Delete the "id" fileEntry.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
