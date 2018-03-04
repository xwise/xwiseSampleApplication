package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.FileGroupService;
import io.github.jhipster.application.domain.FileGroup;
import io.github.jhipster.application.repository.FileGroupRepository;
import io.github.jhipster.application.service.dto.FileGroupDTO;
import io.github.jhipster.application.service.mapper.FileGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing FileGroup.
 */
@Service
@Transactional
public class FileGroupServiceImpl implements FileGroupService {

    private final Logger log = LoggerFactory.getLogger(FileGroupServiceImpl.class);

    private final FileGroupRepository fileGroupRepository;

    private final FileGroupMapper fileGroupMapper;

    public FileGroupServiceImpl(FileGroupRepository fileGroupRepository, FileGroupMapper fileGroupMapper) {
        this.fileGroupRepository = fileGroupRepository;
        this.fileGroupMapper = fileGroupMapper;
    }

    /**
     * Save a fileGroup.
     *
     * @param fileGroupDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FileGroupDTO save(FileGroupDTO fileGroupDTO) {
        log.debug("Request to save FileGroup : {}", fileGroupDTO);
        FileGroup fileGroup = fileGroupMapper.toEntity(fileGroupDTO);
        fileGroup = fileGroupRepository.save(fileGroup);
        return fileGroupMapper.toDto(fileGroup);
    }

    /**
     * Get all the fileGroups.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<FileGroupDTO> findAll() {
        log.debug("Request to get all FileGroups");
        return fileGroupRepository.findAll().stream()
            .map(fileGroupMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one fileGroup by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public FileGroupDTO findOne(Long id) {
        log.debug("Request to get FileGroup : {}", id);
        FileGroup fileGroup = fileGroupRepository.findOne(id);
        return fileGroupMapper.toDto(fileGroup);
    }

    /**
     * Delete the fileGroup by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FileGroup : {}", id);
        fileGroupRepository.delete(id);
    }
}
