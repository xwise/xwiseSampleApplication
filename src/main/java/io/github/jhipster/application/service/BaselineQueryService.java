package io.github.jhipster.application.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import io.github.jhipster.application.domain.Baseline;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.BaselineRepository;
import io.github.jhipster.application.service.dto.BaselineCriteria;

import io.github.jhipster.application.service.dto.BaselineDTO;
import io.github.jhipster.application.service.mapper.BaselineMapper;
import io.github.jhipster.application.domain.enumeration.BaselineStatus;

/**
 * Service for executing complex queries for Baseline entities in the database.
 * The main input is a {@link BaselineCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link BaselineDTO} or a {@link Page} of {@link BaselineDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class BaselineQueryService extends QueryService<Baseline> {

    private final Logger log = LoggerFactory.getLogger(BaselineQueryService.class);


    private final BaselineRepository baselineRepository;

    private final BaselineMapper baselineMapper;

    public BaselineQueryService(BaselineRepository baselineRepository, BaselineMapper baselineMapper) {
        this.baselineRepository = baselineRepository;
        this.baselineMapper = baselineMapper;
    }

    /**
     * Return a {@link List} of {@link BaselineDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<BaselineDTO> findByCriteria(BaselineCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<Baseline> specification = createSpecification(criteria);
        return baselineMapper.toDto(baselineRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link BaselineDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<BaselineDTO> findByCriteria(BaselineCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Baseline> specification = createSpecification(criteria);
        final Page<Baseline> result = baselineRepository.findAll(specification, page);
        return result.map(baselineMapper::toDto);
    }

    /**
     * Function to convert BaselineCriteria to a {@link Specifications}
     */
    private Specifications<Baseline> createSpecification(BaselineCriteria criteria) {
        Specifications<Baseline> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Baseline_.id));
            }
            if (criteria.getCreationTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreationTime(), Baseline_.creationTime));
            }
            if (criteria.getBaselineName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBaselineName(), Baseline_.baselineName));
            }
            if (criteria.getMilestone() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMilestone(), Baseline_.milestone));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getStatus(), Baseline_.status));
            }
            if (criteria.getFileGroupId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getFileGroupId(), Baseline_.fileGroups, FileEntry_.id));
            }
        }
        return specification;
    }

}
