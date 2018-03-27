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

import io.github.jhipster.application.domain.MyFirstComponent;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.MyFirstComponentRepository;
import io.github.jhipster.application.service.dto.MyFirstComponentCriteria;

import io.github.jhipster.application.service.dto.MyFirstComponentDTO;
import io.github.jhipster.application.service.mapper.MyFirstComponentMapper;

/**
 * Service for executing complex queries for MyFirstComponent entities in the database.
 * The main input is a {@link MyFirstComponentCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MyFirstComponentDTO} or a {@link Page} of {@link MyFirstComponentDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MyFirstComponentQueryService extends QueryService<MyFirstComponent> {

    private final Logger log = LoggerFactory.getLogger(MyFirstComponentQueryService.class);


    private final MyFirstComponentRepository myFirstComponentRepository;

    private final MyFirstComponentMapper myFirstComponentMapper;

    public MyFirstComponentQueryService(MyFirstComponentRepository myFirstComponentRepository, MyFirstComponentMapper myFirstComponentMapper) {
        this.myFirstComponentRepository = myFirstComponentRepository;
        this.myFirstComponentMapper = myFirstComponentMapper;
    }

    /**
     * Return a {@link List} of {@link MyFirstComponentDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MyFirstComponentDTO> findByCriteria(MyFirstComponentCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<MyFirstComponent> specification = createSpecification(criteria);
        return myFirstComponentMapper.toDto(myFirstComponentRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MyFirstComponentDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MyFirstComponentDTO> findByCriteria(MyFirstComponentCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<MyFirstComponent> specification = createSpecification(criteria);
        final Page<MyFirstComponent> result = myFirstComponentRepository.findAll(specification, page);
        return result.map(myFirstComponentMapper::toDto);
    }

    /**
     * Function to convert MyFirstComponentCriteria to a {@link Specifications}
     */
    private Specifications<MyFirstComponent> createSpecification(MyFirstComponentCriteria criteria) {
        Specifications<MyFirstComponent> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MyFirstComponent_.id));
            }
            if (criteria.getCreationTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreationTime(), MyFirstComponent_.creationTime));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), MyFirstComponent_.name));
            }
            if (criteria.getAttribute1() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAttribute1(), MyFirstComponent_.attribute1));
            }
            if (criteria.getAttribute2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAttribute2(), MyFirstComponent_.attribute2));
            }
            if (criteria.getAttribute3() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAttribute3(), MyFirstComponent_.attribute3));
            }
            if (criteria.getAttribute4() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAttribute4(), MyFirstComponent_.attribute4));
            }
            if (criteria.getAttribute5() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAttribute5(), MyFirstComponent_.attribute5));
            }
            if (criteria.getAttribute6() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAttribute6(), MyFirstComponent_.attribute6));
            }
            if (criteria.getAttribute7() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAttribute7(), MyFirstComponent_.attribute7));
            }
        }
        return specification;
    }

}
