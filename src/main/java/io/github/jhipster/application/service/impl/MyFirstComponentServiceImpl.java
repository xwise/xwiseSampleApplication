package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.MyFirstComponentService;
import io.github.jhipster.application.domain.MyFirstComponent;
import io.github.jhipster.application.repository.MyFirstComponentRepository;
import io.github.jhipster.application.service.dto.MyFirstComponentDTO;
import io.github.jhipster.application.service.mapper.MyFirstComponentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing MyFirstComponent.
 */
@Service
@Transactional
public class MyFirstComponentServiceImpl implements MyFirstComponentService {

    private final Logger log = LoggerFactory.getLogger(MyFirstComponentServiceImpl.class);

    private final MyFirstComponentRepository myFirstComponentRepository;

    private final MyFirstComponentMapper myFirstComponentMapper;

    public MyFirstComponentServiceImpl(MyFirstComponentRepository myFirstComponentRepository, MyFirstComponentMapper myFirstComponentMapper) {
        this.myFirstComponentRepository = myFirstComponentRepository;
        this.myFirstComponentMapper = myFirstComponentMapper;
    }

    /**
     * Save a myFirstComponent.
     *
     * @param myFirstComponentDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MyFirstComponentDTO save(MyFirstComponentDTO myFirstComponentDTO) {
        log.debug("Request to save MyFirstComponent : {}", myFirstComponentDTO);
        MyFirstComponent myFirstComponent = myFirstComponentMapper.toEntity(myFirstComponentDTO);
        myFirstComponent = myFirstComponentRepository.save(myFirstComponent);
        return myFirstComponentMapper.toDto(myFirstComponent);
    }

    /**
     * Get all the myFirstComponents.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<MyFirstComponentDTO> findAll() {
        log.debug("Request to get all MyFirstComponents");
        return myFirstComponentRepository.findAll().stream()
            .map(myFirstComponentMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one myFirstComponent by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public MyFirstComponentDTO findOne(Long id) {
        log.debug("Request to get MyFirstComponent : {}", id);
        MyFirstComponent myFirstComponent = myFirstComponentRepository.findOne(id);
        return myFirstComponentMapper.toDto(myFirstComponent);
    }

    /**
     * Delete the myFirstComponent by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MyFirstComponent : {}", id);
        myFirstComponentRepository.delete(id);
    }
}
