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

import io.github.jhipster.application.domain.FileEntry;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.FileEntryRepository;
import io.github.jhipster.application.service.dto.FileEntryCriteria;

import io.github.jhipster.application.service.dto.FileEntryDTO;
import io.github.jhipster.application.service.mapper.FileEntryMapper;

/**
 * Service for executing complex queries for FileEntry entities in the database.
 * The main input is a {@link FileEntryCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FileEntryDTO} or a {@link Page} of {@link FileEntryDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FileEntryQueryService extends QueryService<FileEntry> {

    private final Logger log = LoggerFactory.getLogger(FileEntryQueryService.class);


    private final FileEntryRepository fileEntryRepository;

    private final FileEntryMapper fileEntryMapper;

    public FileEntryQueryService(FileEntryRepository fileEntryRepository, FileEntryMapper fileEntryMapper) {
        this.fileEntryRepository = fileEntryRepository;
        this.fileEntryMapper = fileEntryMapper;
    }

    /**
     * Return a {@link List} of {@link FileEntryDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FileEntryDTO> findByCriteria(FileEntryCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<FileEntry> specification = createSpecification(criteria);
        return fileEntryMapper.toDto(fileEntryRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FileEntryDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FileEntryDTO> findByCriteria(FileEntryCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<FileEntry> specification = createSpecification(criteria);
        final Page<FileEntry> result = fileEntryRepository.findAll(specification, page);
        return result.map(fileEntryMapper::toDto);
    }

    /**
     * Function to convert FileEntryCriteria to a {@link Specifications}
     */
    private Specifications<FileEntry> createSpecification(FileEntryCriteria criteria) {
        Specifications<FileEntry> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), FileEntry_.id));
            }
            if (criteria.getCreationTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreationTime(), FileEntry_.creationTime));
            }
            if (criteria.getFileName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFileName(), FileEntry_.fileName));
            }
            if (criteria.getFileGroupId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getFileGroupId(), FileEntry_.fileGroups, FileGroup_.id));
            }
            if (criteria.getUploadVersionId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getUploadVersionId(), FileEntry_.uploadVersion, UploadVersion_.id));
            }
        }
        return specification;
    }

}
