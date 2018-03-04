package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.MyFirstComponentDTO;
import java.util.List;

/**
 * Service Interface for managing MyFirstComponent.
 */
public interface MyFirstComponentService {

    /**
     * Save a myFirstComponent.
     *
     * @param myFirstComponentDTO the entity to save
     * @return the persisted entity
     */
    MyFirstComponentDTO save(MyFirstComponentDTO myFirstComponentDTO);

    /**
     * Get all the myFirstComponents.
     *
     * @return the list of entities
     */
    List<MyFirstComponentDTO> findAll();

    /**
     * Get the "id" myFirstComponent.
     *
     * @param id the id of the entity
     * @return the entity
     */
    MyFirstComponentDTO findOne(Long id);

    /**
     * Delete the "id" myFirstComponent.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
