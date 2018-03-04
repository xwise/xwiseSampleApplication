package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.service.MyFirstComponentService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.service.dto.MyFirstComponentDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing MyFirstComponent.
 */
@RestController
@RequestMapping("/api")
public class MyFirstComponentResource {

    private final Logger log = LoggerFactory.getLogger(MyFirstComponentResource.class);

    private static final String ENTITY_NAME = "myFirstComponent";

    private final MyFirstComponentService myFirstComponentService;

    public MyFirstComponentResource(MyFirstComponentService myFirstComponentService) {
        this.myFirstComponentService = myFirstComponentService;
    }

    /**
     * POST  /my-first-components : Create a new myFirstComponent.
     *
     * @param myFirstComponentDTO the myFirstComponentDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new myFirstComponentDTO, or with status 400 (Bad Request) if the myFirstComponent has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/my-first-components")
    @Timed
    public ResponseEntity<MyFirstComponentDTO> createMyFirstComponent(@RequestBody MyFirstComponentDTO myFirstComponentDTO) throws URISyntaxException {
        log.debug("REST request to save MyFirstComponent : {}", myFirstComponentDTO);
        if (myFirstComponentDTO.getId() != null) {
            throw new BadRequestAlertException("A new myFirstComponent cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MyFirstComponentDTO result = myFirstComponentService.save(myFirstComponentDTO);
        return ResponseEntity.created(new URI("/api/my-first-components/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /my-first-components : Updates an existing myFirstComponent.
     *
     * @param myFirstComponentDTO the myFirstComponentDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated myFirstComponentDTO,
     * or with status 400 (Bad Request) if the myFirstComponentDTO is not valid,
     * or with status 500 (Internal Server Error) if the myFirstComponentDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/my-first-components")
    @Timed
    public ResponseEntity<MyFirstComponentDTO> updateMyFirstComponent(@RequestBody MyFirstComponentDTO myFirstComponentDTO) throws URISyntaxException {
        log.debug("REST request to update MyFirstComponent : {}", myFirstComponentDTO);
        if (myFirstComponentDTO.getId() == null) {
            return createMyFirstComponent(myFirstComponentDTO);
        }
        MyFirstComponentDTO result = myFirstComponentService.save(myFirstComponentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, myFirstComponentDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /my-first-components : get all the myFirstComponents.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of myFirstComponents in body
     */
    @GetMapping("/my-first-components")
    @Timed
    public List<MyFirstComponentDTO> getAllMyFirstComponents() {
        log.debug("REST request to get all MyFirstComponents");
        return myFirstComponentService.findAll();
        }

    /**
     * GET  /my-first-components/:id : get the "id" myFirstComponent.
     *
     * @param id the id of the myFirstComponentDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the myFirstComponentDTO, or with status 404 (Not Found)
     */
    @GetMapping("/my-first-components/{id}")
    @Timed
    public ResponseEntity<MyFirstComponentDTO> getMyFirstComponent(@PathVariable Long id) {
        log.debug("REST request to get MyFirstComponent : {}", id);
        MyFirstComponentDTO myFirstComponentDTO = myFirstComponentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(myFirstComponentDTO));
    }

    /**
     * DELETE  /my-first-components/:id : delete the "id" myFirstComponent.
     *
     * @param id the id of the myFirstComponentDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/my-first-components/{id}")
    @Timed
    public ResponseEntity<Void> deleteMyFirstComponent(@PathVariable Long id) {
        log.debug("REST request to delete MyFirstComponent : {}", id);
        myFirstComponentService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
