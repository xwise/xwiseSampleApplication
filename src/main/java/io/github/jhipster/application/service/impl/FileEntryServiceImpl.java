package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.FileEntryService;
import io.github.jhipster.application.domain.FileEntry;
import io.github.jhipster.application.repository.FileEntryRepository;
import io.github.jhipster.application.service.dto.FileEntryDTO;
import io.github.jhipster.application.service.mapper.FileEntryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing FileEntry.
 */
@Service
@Transactional
public class FileEntryServiceImpl implements FileEntryService {

    private final Logger log = LoggerFactory.getLogger(FileEntryServiceImpl.class);

    private final FileEntryRepository fileEntryRepository;

    private final FileEntryMapper fileEntryMapper;

    public FileEntryServiceImpl(FileEntryRepository fileEntryRepository, FileEntryMapper fileEntryMapper) {
        this.fileEntryRepository = fileEntryRepository;
        this.fileEntryMapper = fileEntryMapper;
    }

    /**
     * Save a fileEntry.
     *
     * @param fileEntryDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FileEntryDTO save(FileEntryDTO fileEntryDTO) {
        log.debug("Request to save FileEntry : {}", fileEntryDTO);
        FileEntry fileEntry = fileEntryMapper.toEntity(fileEntryDTO);
        fileEntry = fileEntryRepository.save(fileEntry);
        return fileEntryMapper.toDto(fileEntry);
    }

    /**
     * Get all the fileEntries.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FileEntryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FileEntries");
        return fileEntryRepository.findAll(pageable)
            .map(fileEntryMapper::toDto);
    }

    /**
     * Get one fileEntry by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public FileEntryDTO findOne(Long id) {
        log.debug("Request to get FileEntry : {}", id);
        FileEntry fileEntry = fileEntryRepository.findOneWithEagerRelationships(id);
        return fileEntryMapper.toDto(fileEntry);
    }

    /**
     * Delete the fileEntry by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FileEntry : {}", id);
        fileEntryRepository.delete(id);
    }
}
