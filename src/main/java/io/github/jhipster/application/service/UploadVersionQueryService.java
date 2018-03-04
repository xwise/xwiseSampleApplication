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

import io.github.jhipster.application.domain.UploadVersion;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.UploadVersionRepository;
import io.github.jhipster.application.service.dto.UploadVersionCriteria;

import io.github.jhipster.application.service.dto.UploadVersionDTO;
import io.github.jhipster.application.service.mapper.UploadVersionMapper;

/**
 * Service for executing complex queries for UploadVersion entities in the database.
 * The main input is a {@link UploadVersionCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link UploadVersionDTO} or a {@link Page} of {@link UploadVersionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class UploadVersionQueryService extends QueryService<UploadVersion> {

    private final Logger log = LoggerFactory.getLogger(UploadVersionQueryService.class);


    private final UploadVersionRepository uploadVersionRepository;

    private final UploadVersionMapper uploadVersionMapper;

    public UploadVersionQueryService(UploadVersionRepository uploadVersionRepository, UploadVersionMapper uploadVersionMapper) {
        this.uploadVersionRepository = uploadVersionRepository;
        this.uploadVersionMapper = uploadVersionMapper;
    }

    /**
     * Return a {@link List} of {@link UploadVersionDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<UploadVersionDTO> findByCriteria(UploadVersionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<UploadVersion> specification = createSpecification(criteria);
        return uploadVersionMapper.toDto(uploadVersionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link UploadVersionDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<UploadVersionDTO> findByCriteria(UploadVersionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<UploadVersion> specification = createSpecification(criteria);
        final Page<UploadVersion> result = uploadVersionRepository.findAll(specification, page);
        return result.map(uploadVersionMapper::toDto);
    }

    /**
     * Function to convert UploadVersionCriteria to a {@link Specifications}
     */
    private Specifications<UploadVersion> createSpecification(UploadVersionCriteria criteria) {
        Specifications<UploadVersion> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), UploadVersion_.id));
            }
            if (criteria.getVersion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVersion(), UploadVersion_.version));
            }
            if (criteria.getFileEntryId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getFileEntryId(), UploadVersion_.fileEntry, FileEntry_.id));
            }
        }
        return specification;
    }

}
