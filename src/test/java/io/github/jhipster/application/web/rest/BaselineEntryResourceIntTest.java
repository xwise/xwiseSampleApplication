package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.XwiseSampleApplicationApp;

import io.github.jhipster.application.domain.BaselineEntry;
import io.github.jhipster.application.repository.BaselineEntryRepository;
import io.github.jhipster.application.service.BaselineEntryService;
import io.github.jhipster.application.service.dto.BaselineEntryDTO;
import io.github.jhipster.application.service.mapper.BaselineEntryMapper;
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
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.github.jhipster.application.domain.enumeration.UploadStatus;
/**
 * Test class for the BaselineEntryResource REST controller.
 *
 * @see BaselineEntryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XwiseSampleApplicationApp.class)
public class BaselineEntryResourceIntTest {

    private static final String DEFAULT_ENTRY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ENTRY_NAME = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATION_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATION_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FILE_NAME = "BBBBBBBBBB";

    private static final byte[] DEFAULT_BLOB = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BLOB = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_BLOB_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BLOB_CONTENT_TYPE = "image/png";

    private static final UploadStatus DEFAULT_UPLOAD_STATUS = UploadStatus.NO;
    private static final UploadStatus UPDATED_UPLOAD_STATUS = UploadStatus.OK;

    @Autowired
    private BaselineEntryRepository baselineEntryRepository;

    @Autowired
    private BaselineEntryMapper baselineEntryMapper;

    @Autowired
    private BaselineEntryService baselineEntryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBaselineEntryMockMvc;

    private BaselineEntry baselineEntry;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BaselineEntryResource baselineEntryResource = new BaselineEntryResource(baselineEntryService);
        this.restBaselineEntryMockMvc = MockMvcBuilders.standaloneSetup(baselineEntryResource)
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
    public static BaselineEntry createEntity(EntityManager em) {
        BaselineEntry baselineEntry = new BaselineEntry()
            .entryName(DEFAULT_ENTRY_NAME)
            .creationTime(DEFAULT_CREATION_TIME)
            .fileName(DEFAULT_FILE_NAME)
            .blob(DEFAULT_BLOB)
            .blobContentType(DEFAULT_BLOB_CONTENT_TYPE)
            .uploadStatus(DEFAULT_UPLOAD_STATUS);
        return baselineEntry;
    }

    @Before
    public void initTest() {
        baselineEntry = createEntity(em);
    }

    @Test
    @Transactional
    public void createBaselineEntry() throws Exception {
        int databaseSizeBeforeCreate = baselineEntryRepository.findAll().size();

        // Create the BaselineEntry
        BaselineEntryDTO baselineEntryDTO = baselineEntryMapper.toDto(baselineEntry);
        restBaselineEntryMockMvc.perform(post("/api/baseline-entries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(baselineEntryDTO)))
            .andExpect(status().isCreated());

        // Validate the BaselineEntry in the database
        List<BaselineEntry> baselineEntryList = baselineEntryRepository.findAll();
        assertThat(baselineEntryList).hasSize(databaseSizeBeforeCreate + 1);
        BaselineEntry testBaselineEntry = baselineEntryList.get(baselineEntryList.size() - 1);
        assertThat(testBaselineEntry.getEntryName()).isEqualTo(DEFAULT_ENTRY_NAME);
        assertThat(testBaselineEntry.getCreationTime()).isEqualTo(DEFAULT_CREATION_TIME);
        assertThat(testBaselineEntry.getFileName()).isEqualTo(DEFAULT_FILE_NAME);
        assertThat(testBaselineEntry.getBlob()).isEqualTo(DEFAULT_BLOB);
        assertThat(testBaselineEntry.getBlobContentType()).isEqualTo(DEFAULT_BLOB_CONTENT_TYPE);
        assertThat(testBaselineEntry.getUploadStatus()).isEqualTo(DEFAULT_UPLOAD_STATUS);
    }

    @Test
    @Transactional
    public void createBaselineEntryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = baselineEntryRepository.findAll().size();

        // Create the BaselineEntry with an existing ID
        baselineEntry.setId(1L);
        BaselineEntryDTO baselineEntryDTO = baselineEntryMapper.toDto(baselineEntry);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBaselineEntryMockMvc.perform(post("/api/baseline-entries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(baselineEntryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BaselineEntry in the database
        List<BaselineEntry> baselineEntryList = baselineEntryRepository.findAll();
        assertThat(baselineEntryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllBaselineEntries() throws Exception {
        // Initialize the database
        baselineEntryRepository.saveAndFlush(baselineEntry);

        // Get all the baselineEntryList
        restBaselineEntryMockMvc.perform(get("/api/baseline-entries?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(baselineEntry.getId().intValue())))
            .andExpect(jsonPath("$.[*].entryName").value(hasItem(DEFAULT_ENTRY_NAME.toString())))
            .andExpect(jsonPath("$.[*].creationTime").value(hasItem(DEFAULT_CREATION_TIME.toString())))
            .andExpect(jsonPath("$.[*].fileName").value(hasItem(DEFAULT_FILE_NAME.toString())))
            .andExpect(jsonPath("$.[*].blobContentType").value(hasItem(DEFAULT_BLOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].blob").value(hasItem(Base64Utils.encodeToString(DEFAULT_BLOB))))
            .andExpect(jsonPath("$.[*].uploadStatus").value(hasItem(DEFAULT_UPLOAD_STATUS.toString())));
    }

    @Test
    @Transactional
    public void getBaselineEntry() throws Exception {
        // Initialize the database
        baselineEntryRepository.saveAndFlush(baselineEntry);

        // Get the baselineEntry
        restBaselineEntryMockMvc.perform(get("/api/baseline-entries/{id}", baselineEntry.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(baselineEntry.getId().intValue()))
            .andExpect(jsonPath("$.entryName").value(DEFAULT_ENTRY_NAME.toString()))
            .andExpect(jsonPath("$.creationTime").value(DEFAULT_CREATION_TIME.toString()))
            .andExpect(jsonPath("$.fileName").value(DEFAULT_FILE_NAME.toString()))
            .andExpect(jsonPath("$.blobContentType").value(DEFAULT_BLOB_CONTENT_TYPE))
            .andExpect(jsonPath("$.blob").value(Base64Utils.encodeToString(DEFAULT_BLOB)))
            .andExpect(jsonPath("$.uploadStatus").value(DEFAULT_UPLOAD_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBaselineEntry() throws Exception {
        // Get the baselineEntry
        restBaselineEntryMockMvc.perform(get("/api/baseline-entries/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBaselineEntry() throws Exception {
        // Initialize the database
        baselineEntryRepository.saveAndFlush(baselineEntry);
        int databaseSizeBeforeUpdate = baselineEntryRepository.findAll().size();

        // Update the baselineEntry
        BaselineEntry updatedBaselineEntry = baselineEntryRepository.findOne(baselineEntry.getId());
        // Disconnect from session so that the updates on updatedBaselineEntry are not directly saved in db
        em.detach(updatedBaselineEntry);
        updatedBaselineEntry
            .entryName(UPDATED_ENTRY_NAME)
            .creationTime(UPDATED_CREATION_TIME)
            .fileName(UPDATED_FILE_NAME)
            .blob(UPDATED_BLOB)
            .blobContentType(UPDATED_BLOB_CONTENT_TYPE)
            .uploadStatus(UPDATED_UPLOAD_STATUS);
        BaselineEntryDTO baselineEntryDTO = baselineEntryMapper.toDto(updatedBaselineEntry);

        restBaselineEntryMockMvc.perform(put("/api/baseline-entries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(baselineEntryDTO)))
            .andExpect(status().isOk());

        // Validate the BaselineEntry in the database
        List<BaselineEntry> baselineEntryList = baselineEntryRepository.findAll();
        assertThat(baselineEntryList).hasSize(databaseSizeBeforeUpdate);
        BaselineEntry testBaselineEntry = baselineEntryList.get(baselineEntryList.size() - 1);
        assertThat(testBaselineEntry.getEntryName()).isEqualTo(UPDATED_ENTRY_NAME);
        assertThat(testBaselineEntry.getCreationTime()).isEqualTo(UPDATED_CREATION_TIME);
        assertThat(testBaselineEntry.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testBaselineEntry.getBlob()).isEqualTo(UPDATED_BLOB);
        assertThat(testBaselineEntry.getBlobContentType()).isEqualTo(UPDATED_BLOB_CONTENT_TYPE);
        assertThat(testBaselineEntry.getUploadStatus()).isEqualTo(UPDATED_UPLOAD_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingBaselineEntry() throws Exception {
        int databaseSizeBeforeUpdate = baselineEntryRepository.findAll().size();

        // Create the BaselineEntry
        BaselineEntryDTO baselineEntryDTO = baselineEntryMapper.toDto(baselineEntry);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBaselineEntryMockMvc.perform(put("/api/baseline-entries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(baselineEntryDTO)))
            .andExpect(status().isCreated());

        // Validate the BaselineEntry in the database
        List<BaselineEntry> baselineEntryList = baselineEntryRepository.findAll();
        assertThat(baselineEntryList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteBaselineEntry() throws Exception {
        // Initialize the database
        baselineEntryRepository.saveAndFlush(baselineEntry);
        int databaseSizeBeforeDelete = baselineEntryRepository.findAll().size();

        // Get the baselineEntry
        restBaselineEntryMockMvc.perform(delete("/api/baseline-entries/{id}", baselineEntry.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<BaselineEntry> baselineEntryList = baselineEntryRepository.findAll();
        assertThat(baselineEntryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BaselineEntry.class);
        BaselineEntry baselineEntry1 = new BaselineEntry();
        baselineEntry1.setId(1L);
        BaselineEntry baselineEntry2 = new BaselineEntry();
        baselineEntry2.setId(baselineEntry1.getId());
        assertThat(baselineEntry1).isEqualTo(baselineEntry2);
        baselineEntry2.setId(2L);
        assertThat(baselineEntry1).isNotEqualTo(baselineEntry2);
        baselineEntry1.setId(null);
        assertThat(baselineEntry1).isNotEqualTo(baselineEntry2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BaselineEntryDTO.class);
        BaselineEntryDTO baselineEntryDTO1 = new BaselineEntryDTO();
        baselineEntryDTO1.setId(1L);
        BaselineEntryDTO baselineEntryDTO2 = new BaselineEntryDTO();
        assertThat(baselineEntryDTO1).isNotEqualTo(baselineEntryDTO2);
        baselineEntryDTO2.setId(baselineEntryDTO1.getId());
        assertThat(baselineEntryDTO1).isEqualTo(baselineEntryDTO2);
        baselineEntryDTO2.setId(2L);
        assertThat(baselineEntryDTO1).isNotEqualTo(baselineEntryDTO2);
        baselineEntryDTO1.setId(null);
        assertThat(baselineEntryDTO1).isNotEqualTo(baselineEntryDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(baselineEntryMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(baselineEntryMapper.fromId(null)).isNull();
    }
}
