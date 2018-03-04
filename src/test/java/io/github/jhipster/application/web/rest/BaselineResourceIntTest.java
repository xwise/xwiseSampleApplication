package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.XwiseSampleApplicationApp;

import io.github.jhipster.application.domain.Baseline;
import io.github.jhipster.application.repository.BaselineRepository;
import io.github.jhipster.application.service.BaselineService;
import io.github.jhipster.application.service.dto.BaselineDTO;
import io.github.jhipster.application.service.mapper.BaselineMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.github.jhipster.application.domain.enumeration.BaselineStatus;
/**
 * Test class for the BaselineResource REST controller.
 *
 * @see BaselineResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XwiseSampleApplicationApp.class)
public class BaselineResourceIntTest {

    private static final String DEFAULT_BASELINE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BASELINE_NAME = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATION_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATION_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MILESTONE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MILESTONE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final BaselineStatus DEFAULT_STATUS = BaselineStatus.ACTIVE;
    private static final BaselineStatus UPDATED_STATUS = BaselineStatus.CLOSED;

    @Autowired
    private BaselineRepository baselineRepository;

    @Autowired
    private BaselineMapper baselineMapper;

    @Autowired
    private BaselineService baselineService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBaselineMockMvc;

    private Baseline baseline;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BaselineResource baselineResource = new BaselineResource(baselineService);
        this.restBaselineMockMvc = MockMvcBuilders.standaloneSetup(baselineResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Baseline createEntity(EntityManager em) {
        Baseline baseline = new Baseline()
            .baselineName(DEFAULT_BASELINE_NAME)
            .creationTime(DEFAULT_CREATION_TIME)
            .milestone(DEFAULT_MILESTONE)
            .status(DEFAULT_STATUS);
        return baseline;
    }

    @Before
    public void initTest() {
        baseline = createEntity(em);
    }

    @Test
    @Transactional
    public void createBaseline() throws Exception {
        int databaseSizeBeforeCreate = baselineRepository.findAll().size();

        // Create the Baseline
        BaselineDTO baselineDTO = baselineMapper.toDto(baseline);
        restBaselineMockMvc.perform(post("/api/baselines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(baselineDTO)))
            .andExpect(status().isCreated());

        // Validate the Baseline in the database
        List<Baseline> baselineList = baselineRepository.findAll();
        assertThat(baselineList).hasSize(databaseSizeBeforeCreate + 1);
        Baseline testBaseline = baselineList.get(baselineList.size() - 1);
        assertThat(testBaseline.getBaselineName()).isEqualTo(DEFAULT_BASELINE_NAME);
        assertThat(testBaseline.getCreationTime()).isEqualTo(DEFAULT_CREATION_TIME);
        assertThat(testBaseline.getMilestone()).isEqualTo(DEFAULT_MILESTONE);
        assertThat(testBaseline.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createBaselineWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = baselineRepository.findAll().size();

        // Create the Baseline with an existing ID
        baseline.setId(1L);
        BaselineDTO baselineDTO = baselineMapper.toDto(baseline);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBaselineMockMvc.perform(post("/api/baselines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(baselineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Baseline in the database
        List<Baseline> baselineList = baselineRepository.findAll();
        assertThat(baselineList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllBaselines() throws Exception {
        // Initialize the database
        baselineRepository.saveAndFlush(baseline);

        // Get all the baselineList
        restBaselineMockMvc.perform(get("/api/baselines?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(baseline.getId().intValue())))
            .andExpect(jsonPath("$.[*].baselineName").value(hasItem(DEFAULT_BASELINE_NAME.toString())))
            .andExpect(jsonPath("$.[*].creationTime").value(hasItem(DEFAULT_CREATION_TIME.toString())))
            .andExpect(jsonPath("$.[*].milestone").value(hasItem(DEFAULT_MILESTONE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    public void getBaseline() throws Exception {
        // Initialize the database
        baselineRepository.saveAndFlush(baseline);

        // Get the baseline
        restBaselineMockMvc.perform(get("/api/baselines/{id}", baseline.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(baseline.getId().intValue()))
            .andExpect(jsonPath("$.baselineName").value(DEFAULT_BASELINE_NAME.toString()))
            .andExpect(jsonPath("$.creationTime").value(DEFAULT_CREATION_TIME.toString()))
            .andExpect(jsonPath("$.milestone").value(DEFAULT_MILESTONE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBaseline() throws Exception {
        // Get the baseline
        restBaselineMockMvc.perform(get("/api/baselines/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBaseline() throws Exception {
        // Initialize the database
        baselineRepository.saveAndFlush(baseline);
        int databaseSizeBeforeUpdate = baselineRepository.findAll().size();

        // Update the baseline
        Baseline updatedBaseline = baselineRepository.findOne(baseline.getId());
        // Disconnect from session so that the updates on updatedBaseline are not directly saved in db
        em.detach(updatedBaseline);
        updatedBaseline
            .baselineName(UPDATED_BASELINE_NAME)
            .creationTime(UPDATED_CREATION_TIME)
            .milestone(UPDATED_MILESTONE)
            .status(UPDATED_STATUS);
        BaselineDTO baselineDTO = baselineMapper.toDto(updatedBaseline);

        restBaselineMockMvc.perform(put("/api/baselines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(baselineDTO)))
            .andExpect(status().isOk());

        // Validate the Baseline in the database
        List<Baseline> baselineList = baselineRepository.findAll();
        assertThat(baselineList).hasSize(databaseSizeBeforeUpdate);
        Baseline testBaseline = baselineList.get(baselineList.size() - 1);
        assertThat(testBaseline.getBaselineName()).isEqualTo(UPDATED_BASELINE_NAME);
        assertThat(testBaseline.getCreationTime()).isEqualTo(UPDATED_CREATION_TIME);
        assertThat(testBaseline.getMilestone()).isEqualTo(UPDATED_MILESTONE);
        assertThat(testBaseline.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingBaseline() throws Exception {
        int databaseSizeBeforeUpdate = baselineRepository.findAll().size();

        // Create the Baseline
        BaselineDTO baselineDTO = baselineMapper.toDto(baseline);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBaselineMockMvc.perform(put("/api/baselines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(baselineDTO)))
            .andExpect(status().isCreated());

        // Validate the Baseline in the database
        List<Baseline> baselineList = baselineRepository.findAll();
        assertThat(baselineList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteBaseline() throws Exception {
        // Initialize the database
        baselineRepository.saveAndFlush(baseline);
        int databaseSizeBeforeDelete = baselineRepository.findAll().size();

        // Get the baseline
        restBaselineMockMvc.perform(delete("/api/baselines/{id}", baseline.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Baseline> baselineList = baselineRepository.findAll();
        assertThat(baselineList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Baseline.class);
        Baseline baseline1 = new Baseline();
        baseline1.setId(1L);
        Baseline baseline2 = new Baseline();
        baseline2.setId(baseline1.getId());
        assertThat(baseline1).isEqualTo(baseline2);
        baseline2.setId(2L);
        assertThat(baseline1).isNotEqualTo(baseline2);
        baseline1.setId(null);
        assertThat(baseline1).isNotEqualTo(baseline2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BaselineDTO.class);
        BaselineDTO baselineDTO1 = new BaselineDTO();
        baselineDTO1.setId(1L);
        BaselineDTO baselineDTO2 = new BaselineDTO();
        assertThat(baselineDTO1).isNotEqualTo(baselineDTO2);
        baselineDTO2.setId(baselineDTO1.getId());
        assertThat(baselineDTO1).isEqualTo(baselineDTO2);
        baselineDTO2.setId(2L);
        assertThat(baselineDTO1).isNotEqualTo(baselineDTO2);
        baselineDTO1.setId(null);
        assertThat(baselineDTO1).isNotEqualTo(baselineDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(baselineMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(baselineMapper.fromId(null)).isNull();
    }
}
