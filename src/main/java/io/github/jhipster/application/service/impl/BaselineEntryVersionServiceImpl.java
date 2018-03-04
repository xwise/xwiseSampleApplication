package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.BaselineEntryVersionService;
import io.github.jhipster.application.domain.BaselineEntryVersion;
import io.github.jhipster.application.repository.BaselineEntryVersionRepository;
import io.github.jhipster.application.service.dto.BaselineEntryVersionDTO;
import io.github.jhipster.application.service.mapper.BaselineEntryVersionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing BaselineEntryVersion.
 */
@Service
@Transactional
public class BaselineEntryVersionServiceImpl implements BaselineEntryVersionService {

    private final Logger log = LoggerFactory.getLogger(BaselineEntryVersionServiceImpl.class);

    private final BaselineEntryVersionRepository baselineEntryVersionRepository;

    private final BaselineEntryVersionMapper baselineEntryVersionMapper;

    public BaselineEntryVersionServiceImpl(BaselineEntryVersionRepository baselineEntryVersionRepository, BaselineEntryVersionMapper baselineEntryVersionMapper) {
        this.baselineEntryVersionRepository = baselineEntryVersionRepository;
        this.baselineEntryVersionMapper = baselineEntryVersionMapper;
    }

    /**
     * Save a baselineEntryVersion.
     *
     * @param baselineEntryVersionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BaselineEntryVersionDTO save(BaselineEntryVersionDTO baselineEntryVersionDTO) {
        log.debug("Request to save BaselineEntryVersion : {}", baselineEntryVersionDTO);
        BaselineEntryVersion baselineEntryVersion = baselineEntryVersionMapper.toEntity(baselineEntryVersionDTO);
        baselineEntryVersion = baselineEntryVersionRepository.save(baselineEntryVersion);
        return baselineEntryVersionMapper.toDto(baselineEntryVersion);
    }

    /**
     * Get all the baselineEntryVersions.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<BaselineEntryVersionDTO> findAll() {
        log.debug("Request to get all BaselineEntryVersions");
        return baselineEntryVersionRepository.findAll().stream()
            .map(baselineEntryVersionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one baselineEntryVersion by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public BaselineEntryVersionDTO findOne(Long id) {
        log.debug("Request to get BaselineEntryVersion : {}", id);
        BaselineEntryVersion baselineEntryVersion = baselineEntryVersionRepository.findOne(id);
        return baselineEntryVersionMapper.toDto(baselineEntryVersion);
    }

    /**
     * Delete the baselineEntryVersion by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BaselineEntryVersion : {}", id);
        baselineEntryVersionRepository.delete(id);
    }
}
