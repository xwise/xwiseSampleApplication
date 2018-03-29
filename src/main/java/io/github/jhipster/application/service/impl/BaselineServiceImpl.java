package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.BaselineService;
import io.github.jhipster.application.domain.Baseline;
import io.github.jhipster.application.repository.BaselineRepository;
import io.github.jhipster.application.service.dto.BaselineDTO;
import io.github.jhipster.application.service.mapper.BaselineMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Baseline.
 */
@Service
@Transactional
public class BaselineServiceImpl implements BaselineService {

    private final Logger log = LoggerFactory.getLogger(BaselineServiceImpl.class);

    private final BaselineRepository baselineRepository;

    private final BaselineMapper baselineMapper;

    public BaselineServiceImpl(BaselineRepository baselineRepository, BaselineMapper baselineMapper) {
        this.baselineRepository = baselineRepository;
        this.baselineMapper = baselineMapper;
    }

    /**
     * Save a baseline.
     *
     * @param baselineDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BaselineDTO save(BaselineDTO baselineDTO) {
        log.debug("Request to save Baseline : {}", baselineDTO);
        Baseline baseline = baselineMapper.toEntity(baselineDTO);
        baseline = baselineRepository.save(baseline);
        return baselineMapper.toDto(baseline);
    }

    /**
     * Get all the baselines.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BaselineDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Baselines");
        return baselineRepository.findAll(pageable)
            .map(baselineMapper::toDto);
    }

    /**
     * Get one baseline by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public BaselineDTO findOne(Long id) {
        log.debug("Request to get Baseline : {}", id);
        Baseline baseline = baselineRepository.findOne(id);
        return baselineMapper.toDto(baseline);
    }

    /**
     * Delete the baseline by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Baseline : {}", id);
        baselineRepository.delete(id);
    }
}
