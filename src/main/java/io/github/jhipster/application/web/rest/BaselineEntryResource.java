package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.service.BaselineEntryService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.application.service.dto.BaselineEntryDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing BaselineEntry.
 */
@RestController
@RequestMapping("/api")
public class BaselineEntryResource {

    private final Logger log = LoggerFactory.getLogger(BaselineEntryResource.class);

    private static final String ENTITY_NAME = "baselineEntry";

    private final BaselineEntryService baselineEntryService;

    public BaselineEntryResource(BaselineEntryService baselineEntryService) {
        this.baselineEntryService = baselineEntryService;
    }

    /**
     * POST  /baseline-entries : Create a new baselineEntry.
     *
     * @param baselineEntryDTO the baselineEntryDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new baselineEntryDTO, or with status 400 (Bad Request) if the baselineEntry has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/baseline-entries")
    @Timed
    public ResponseEntity<BaselineEntryDTO> createBaselineEntry(@RequestBody BaselineEntryDTO baselineEntryDTO) throws URISyntaxException {
        log.debug("REST request to save BaselineEntry : {}", baselineEntryDTO);
        if (baselineEntryDTO.getId() != null) {
            throw new BadRequestAlertException("A new baselineEntry cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BaselineEntryDTO result = baselineEntryService.save(baselineEntryDTO);
        return ResponseEntity.created(new URI("/api/baseline-entries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /baseline-entries : Updates an existing baselineEntry.
     *
     * @param baselineEntryDTO the baselineEntryDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated baselineEntryDTO,
     * or with status 400 (Bad Request) if the baselineEntryDTO is not valid,
     * or with status 500 (Internal Server Error) if the baselineEntryDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/baseline-entries")
    @Timed
    public ResponseEntity<BaselineEntryDTO> updateBaselineEntry(@RequestBody BaselineEntryDTO baselineEntryDTO) throws URISyntaxException {
        log.debug("REST request to update BaselineEntry : {}", baselineEntryDTO);
        if (baselineEntryDTO.getId() == null) {
            return createBaselineEntry(baselineEntryDTO);
        }
        BaselineEntryDTO result = baselineEntryService.save(baselineEntryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, baselineEntryDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /baseline-entries : get all the baselineEntries.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of baselineEntries in body
     */
    @GetMapping("/baseline-entries")
    @Timed
    public ResponseEntity<List<BaselineEntryDTO>> getAllBaselineEntries(Pageable pageable) {
        log.debug("REST request to get a page of BaselineEntries");
        Page<BaselineEntryDTO> page = baselineEntryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/baseline-entries");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /baseline-entries/:id : get the "id" baselineEntry.
     *
     * @param id the id of the baselineEntryDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the baselineEntryDTO, or with status 404 (Not Found)
     */
    @GetMapping("/baseline-entries/{id}")
    @Timed
    public ResponseEntity<BaselineEntryDTO> getBaselineEntry(@PathVariable Long id) {
        log.debug("REST request to get BaselineEntry : {}", id);
        BaselineEntryDTO baselineEntryDTO = baselineEntryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(baselineEntryDTO));
    }

    /**
     * DELETE  /baseline-entries/:id : delete the "id" baselineEntry.
     *
     * @param id the id of the baselineEntryDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/baseline-entries/{id}")
    @Timed
    public ResponseEntity<Void> deleteBaselineEntry(@PathVariable Long id) {
        log.debug("REST request to delete BaselineEntry : {}", id);
        baselineEntryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
