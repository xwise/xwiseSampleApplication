package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.service.BaselineService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.application.service.dto.BaselineDTO;
import io.github.jhipster.application.service.dto.BaselineCriteria;
import io.github.jhipster.application.service.BaselineQueryService;
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
 * REST controller for managing Baseline.
 */
@RestController
@RequestMapping("/api")
public class BaselineResource {

    private final Logger log = LoggerFactory.getLogger(BaselineResource.class);

    private static final String ENTITY_NAME = "baseline";

    private final BaselineService baselineService;

    private final BaselineQueryService baselineQueryService;

    public BaselineResource(BaselineService baselineService, BaselineQueryService baselineQueryService) {
        this.baselineService = baselineService;
        this.baselineQueryService = baselineQueryService;
    }

    /**
     * POST  /baselines : Create a new baseline.
     *
     * @param baselineDTO the baselineDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new baselineDTO, or with status 400 (Bad Request) if the baseline has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/baselines")
    @Timed
    public ResponseEntity<BaselineDTO> createBaseline(@RequestBody BaselineDTO baselineDTO) throws URISyntaxException {
        log.debug("REST request to save Baseline : {}", baselineDTO);
        if (baselineDTO.getId() != null) {
            throw new BadRequestAlertException("A new baseline cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BaselineDTO result = baselineService.save(baselineDTO);
        return ResponseEntity.created(new URI("/api/baselines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /baselines : Updates an existing baseline.
     *
     * @param baselineDTO the baselineDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated baselineDTO,
     * or with status 400 (Bad Request) if the baselineDTO is not valid,
     * or with status 500 (Internal Server Error) if the baselineDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/baselines")
    @Timed
    public ResponseEntity<BaselineDTO> updateBaseline(@RequestBody BaselineDTO baselineDTO) throws URISyntaxException {
        log.debug("REST request to update Baseline : {}", baselineDTO);
        if (baselineDTO.getId() == null) {
            return createBaseline(baselineDTO);
        }
        BaselineDTO result = baselineService.save(baselineDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, baselineDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /baselines : get all the baselines.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of baselines in body
     */
    @GetMapping("/baselines")
    @Timed
    public ResponseEntity<List<BaselineDTO>> getAllBaselines(BaselineCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Baselines by criteria: {}", criteria);
        Page<BaselineDTO> page = baselineQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/baselines");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /baselines/:id : get the "id" baseline.
     *
     * @param id the id of the baselineDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the baselineDTO, or with status 404 (Not Found)
     */
    @GetMapping("/baselines/{id}")
    @Timed
    public ResponseEntity<BaselineDTO> getBaseline(@PathVariable Long id) {
        log.debug("REST request to get Baseline : {}", id);
        BaselineDTO baselineDTO = baselineService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(baselineDTO));
    }

    /**
     * DELETE  /baselines/:id : delete the "id" baseline.
     *
     * @param id the id of the baselineDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/baselines/{id}")
    @Timed
    public ResponseEntity<Void> deleteBaseline(@PathVariable Long id) {
        log.debug("REST request to delete Baseline : {}", id);
        baselineService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
