package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.BaselineEntryService;
import io.github.jhipster.application.domain.BaselineEntry;
import io.github.jhipster.application.repository.BaselineEntryRepository;
import io.github.jhipster.application.service.dto.BaselineEntryDTO;
import io.github.jhipster.application.service.mapper.BaselineEntryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing BaselineEntry.
 */
@Service
@Transactional
public class BaselineEntryServiceImpl implements BaselineEntryService {

    private final Logger log = LoggerFactory.getLogger(BaselineEntryServiceImpl.class);

    private final BaselineEntryRepository baselineEntryRepository;

    private final BaselineEntryMapper baselineEntryMapper;

    public BaselineEntryServiceImpl(BaselineEntryRepository baselineEntryRepository, BaselineEntryMapper baselineEntryMapper) {
        this.baselineEntryRepository = baselineEntryRepository;
        this.baselineEntryMapper = baselineEntryMapper;
    }

    /**
     * Save a baselineEntry.
     *
     * @param baselineEntryDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BaselineEntryDTO save(BaselineEntryDTO baselineEntryDTO) {
        log.debug("Request to save BaselineEntry : {}", baselineEntryDTO);
        BaselineEntry baselineEntry = baselineEntryMapper.toEntity(baselineEntryDTO);
        baselineEntry = baselineEntryRepository.save(baselineEntry);
        return baselineEntryMapper.toDto(baselineEntry);
    }

    /**
     * Get all the baselineEntries.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BaselineEntryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BaselineEntries");
        return baselineEntryRepository.findAll(pageable)
            .map(baselineEntryMapper::toDto);
    }

    /**
     * Get one baselineEntry by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public BaselineEntryDTO findOne(Long id) {
        log.debug("Request to get BaselineEntry : {}", id);
        BaselineEntry baselineEntry = baselineEntryRepository.findOneWithEagerRelationships(id);
        return baselineEntryMapper.toDto(baselineEntry);
    }

    /**
     * Delete the baselineEntry by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BaselineEntry : {}", id);
        baselineEntryRepository.delete(id);
    }
}
