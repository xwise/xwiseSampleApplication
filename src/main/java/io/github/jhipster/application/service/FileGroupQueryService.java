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

import io.github.jhipster.application.domain.FileGroup;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.FileGroupRepository;
import io.github.jhipster.application.service.dto.FileGroupCriteria;

import io.github.jhipster.application.service.dto.FileGroupDTO;
import io.github.jhipster.application.service.mapper.FileGroupMapper;

/**
 * Service for executing complex queries for FileGroup entities in the database.
 * The main input is a {@link FileGroupCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FileGroupDTO} or a {@link Page} of {@link FileGroupDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FileGroupQueryService extends QueryService<FileGroup> {

    private final Logger log = LoggerFactory.getLogger(FileGroupQueryService.class);


    private final FileGroupRepository fileGroupRepository;

    private final FileGroupMapper fileGroupMapper;

    public FileGroupQueryService(FileGroupRepository fileGroupRepository, FileGroupMapper fileGroupMapper) {
        this.fileGroupRepository = fileGroupRepository;
        this.fileGroupMapper = fileGroupMapper;
    }

    /**
     * Return a {@link List} of {@link FileGroupDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FileGroupDTO> findByCriteria(FileGroupCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<FileGroup> specification = createSpecification(criteria);
        return fileGroupMapper.toDto(fileGroupRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FileGroupDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FileGroupDTO> findByCriteria(FileGroupCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<FileGroup> specification = createSpecification(criteria);
        final Page<FileGroup> result = fileGroupRepository.findAll(specification, page);
        return result.map(fileGroupMapper::toDto);
    }

    /**
     * Function to convert FileGroupCriteria to a {@link Specifications}
     */
    private Specifications<FileGroup> createSpecification(FileGroupCriteria criteria) {
        Specifications<FileGroup> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), FileGroup_.id));
            }
            if (criteria.getGroupName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGroupName(), FileGroup_.groupName));
            }
            if (criteria.getFileEntryId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getFileEntryId(), FileGroup_.fileEntries, FileEntry_.id));
            }
            if (criteria.getBaselineId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getBaselineId(), FileGroup_.baselines, Baseline_.id));
            }
        }
        return specification;
    }

}
