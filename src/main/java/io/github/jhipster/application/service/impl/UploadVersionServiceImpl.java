package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.UploadVersionService;
import io.github.jhipster.application.domain.UploadVersion;
import io.github.jhipster.application.repository.UploadVersionRepository;
import io.github.jhipster.application.service.dto.UploadVersionDTO;
import io.github.jhipster.application.service.mapper.UploadVersionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing UploadVersion.
 */
@Service
@Transactional
public class UploadVersionServiceImpl implements UploadVersionService {

    private final Logger log = LoggerFactory.getLogger(UploadVersionServiceImpl.class);

    private final UploadVersionRepository uploadVersionRepository;

    private final UploadVersionMapper uploadVersionMapper;

    public UploadVersionServiceImpl(UploadVersionRepository uploadVersionRepository, UploadVersionMapper uploadVersionMapper) {
        this.uploadVersionRepository = uploadVersionRepository;
        this.uploadVersionMapper = uploadVersionMapper;
    }

    /**
     * Save a uploadVersion.
     *
     * @param uploadVersionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public UploadVersionDTO save(UploadVersionDTO uploadVersionDTO) {
        log.debug("Request to save UploadVersion : {}", uploadVersionDTO);
        UploadVersion uploadVersion = uploadVersionMapper.toEntity(uploadVersionDTO);
        uploadVersion = uploadVersionRepository.save(uploadVersion);
        return uploadVersionMapper.toDto(uploadVersion);
    }

    /**
     * Get all the uploadVersions.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<UploadVersionDTO> findAll() {
        log.debug("Request to get all UploadVersions");
        return uploadVersionRepository.findAll().stream()
            .map(uploadVersionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one uploadVersion by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public UploadVersionDTO findOne(Long id) {
        log.debug("Request to get UploadVersion : {}", id);
        UploadVersion uploadVersion = uploadVersionRepository.findOne(id);
        return uploadVersionMapper.toDto(uploadVersion);
    }

    /**
     * Delete the uploadVersion by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UploadVersion : {}", id);
        uploadVersionRepository.delete(id);
    }
}
