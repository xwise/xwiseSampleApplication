package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.service.BaselineEntryVersionService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.service.dto.BaselineEntryVersionDTO;
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
 * REST controller for managing BaselineEntryVersion.
 */
@RestController
@RequestMapping("/api")
public class BaselineEntryVersionResource {

    private final Logger log = LoggerFactory.getLogger(BaselineEntryVersionResource.class);

    private static final String ENTITY_NAME = "baselineEntryVersion";

    private final BaselineEntryVersionService baselineEntryVersionService;

    public BaselineEntryVersionResource(BaselineEntryVersionService baselineEntryVersionService) {
        this.baselineEntryVersionService = baselineEntryVersionService;
    }

    /**
     * POST  /baseline-entry-versions : Create a new baselineEntryVersion.
     *
     * @param baselineEntryVersionDTO the baselineEntryVersionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new baselineEntryVersionDTO, or with status 400 (Bad Request) if the baselineEntryVersion has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/baseline-entry-versions")
    @Timed
    public ResponseEntity<BaselineEntryVersionDTO> createBaselineEntryVersion(@RequestBody BaselineEntryVersionDTO baselineEntryVersionDTO) throws URISyntaxException {
        log.debug("REST request to save BaselineEntryVersion : {}", baselineEntryVersionDTO);
        if (baselineEntryVersionDTO.getId() != null) {
            throw new BadRequestAlertException("A new baselineEntryVersion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BaselineEntryVersionDTO result = baselineEntryVersionService.save(baselineEntryVersionDTO);
        return ResponseEntity.created(new URI("/api/baseline-entry-versions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /baseline-entry-versions : Updates an existing baselineEntryVersion.
     *
     * @param baselineEntryVersionDTO the baselineEntryVersionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated baselineEntryVersionDTO,
     * or with status 400 (Bad Request) if the baselineEntryVersionDTO is not valid,
     * or with status 500 (Internal Server Error) if the baselineEntryVersionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/baseline-entry-versions")
    @Timed
    public ResponseEntity<BaselineEntryVersionDTO> updateBaselineEntryVersion(@RequestBody BaselineEntryVersionDTO baselineEntryVersionDTO) throws URISyntaxException {
        log.debug("REST request to update BaselineEntryVersion : {}", baselineEntryVersionDTO);
        if (baselineEntryVersionDTO.getId() == null) {
            return createBaselineEntryVersion(baselineEntryVersionDTO);
        }
        BaselineEntryVersionDTO result = baselineEntryVersionService.save(baselineEntryVersionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, baselineEntryVersionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /baseline-entry-versions : get all the baselineEntryVersions.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of baselineEntryVersions in body
     */
    @GetMapping("/baseline-entry-versions")
    @Timed
    public List<BaselineEntryVersionDTO> getAllBaselineEntryVersions() {
        log.debug("REST request to get all BaselineEntryVersions");
        return baselineEntryVersionService.findAll();
        }

    /**
     * GET  /baseline-entry-versions/:id : get the "id" baselineEntryVersion.
     *
     * @param id the id of the baselineEntryVersionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the baselineEntryVersionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/baseline-entry-versions/{id}")
    @Timed
    public ResponseEntity<BaselineEntryVersionDTO> getBaselineEntryVersion(@PathVariable Long id) {
        log.debug("REST request to get BaselineEntryVersion : {}", id);
        BaselineEntryVersionDTO baselineEntryVersionDTO = baselineEntryVersionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(baselineEntryVersionDTO));
    }

    /**
     * DELETE  /baseline-entry-versions/:id : delete the "id" baselineEntryVersion.
     *
     * @param id the id of the baselineEntryVersionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/baseline-entry-versions/{id}")
    @Timed
    public ResponseEntity<Void> deleteBaselineEntryVersion(@PathVariable Long id) {
        log.debug("REST request to delete BaselineEntryVersion : {}", id);
        baselineEntryVersionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
