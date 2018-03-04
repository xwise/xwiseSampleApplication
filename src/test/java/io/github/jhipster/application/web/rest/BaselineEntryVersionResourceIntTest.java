package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.XwiseSampleApplicationApp;

import io.github.jhipster.application.domain.BaselineEntryVersion;
import io.github.jhipster.application.repository.BaselineEntryVersionRepository;
import io.github.jhipster.application.service.BaselineEntryVersionService;
import io.github.jhipster.application.service.dto.BaselineEntryVersionDTO;
import io.github.jhipster.application.service.mapper.BaselineEntryVersionMapper;
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
 * Test class for the BaselineEntryVersionResource REST controller.
 *
 * @see BaselineEntryVersionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XwiseSampleApplicationApp.class)
public class BaselineEntryVersionResourceIntTest {

    private static final Instant DEFAULT_CREATION_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATION_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_VERSION = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FILE_NAME = "BBBBBBBBBB";

    private static final byte[] DEFAULT_BLOB = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BLOB = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_BLOB_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BLOB_CONTENT_TYPE = "image/png";

    private static final UploadStatus DEFAULT_UPLOAD_STATUS = UploadStatus.NO;
    private static final UploadStatus UPDATED_UPLOAD_STATUS = UploadStatus.OK;

    @Autowired
    private BaselineEntryVersionRepository baselineEntryVersionRepository;

    @Autowired
    private BaselineEntryVersionMapper baselineEntryVersionMapper;

    @Autowired
    private BaselineEntryVersionService baselineEntryVersionService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBaselineEntryVersionMockMvc;

    private BaselineEntryVersion baselineEntryVersion;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BaselineEntryVersionResource baselineEntryVersionResource = new BaselineEntryVersionResource(baselineEntryVersionService);
        this.restBaselineEntryVersionMockMvc = MockMvcBuilders.standaloneSetup(baselineEntryVersionResource)
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
    public static BaselineEntryVersion createEntity(EntityManager em) {
        BaselineEntryVersion baselineEntryVersion = new BaselineEntryVersion()
            .creationTime(DEFAULT_CREATION_TIME)
            .version(DEFAULT_VERSION)
            .fileName(DEFAULT_FILE_NAME)
            .blob(DEFAULT_BLOB)
            .blobContentType(DEFAULT_BLOB_CONTENT_TYPE)
            .uploadStatus(DEFAULT_UPLOAD_STATUS);
        return baselineEntryVersion;
    }

    @Before
    public void initTest() {
        baselineEntryVersion = createEntity(em);
    }

    @Test
    @Transactional
    public void createBaselineEntryVersion() throws Exception {
        int databaseSizeBeforeCreate = baselineEntryVersionRepository.findAll().size();

        // Create the BaselineEntryVersion
        BaselineEntryVersionDTO baselineEntryVersionDTO = baselineEntryVersionMapper.toDto(baselineEntryVersion);
        restBaselineEntryVersionMockMvc.perform(post("/api/baseline-entry-versions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(baselineEntryVersionDTO)))
            .andExpect(status().isCreated());

        // Validate the BaselineEntryVersion in the database
        List<BaselineEntryVersion> baselineEntryVersionList = baselineEntryVersionRepository.findAll();
        assertThat(baselineEntryVersionList).hasSize(databaseSizeBeforeCreate + 1);
        BaselineEntryVersion testBaselineEntryVersion = baselineEntryVersionList.get(baselineEntryVersionList.size() - 1);
        assertThat(testBaselineEntryVersion.getCreationTime()).isEqualTo(DEFAULT_CREATION_TIME);
        assertThat(testBaselineEntryVersion.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testBaselineEntryVersion.getFileName()).isEqualTo(DEFAULT_FILE_NAME);
        assertThat(testBaselineEntryVersion.getBlob()).isEqualTo(DEFAULT_BLOB);
        assertThat(testBaselineEntryVersion.getBlobContentType()).isEqualTo(DEFAULT_BLOB_CONTENT_TYPE);
        assertThat(testBaselineEntryVersion.getUploadStatus()).isEqualTo(DEFAULT_UPLOAD_STATUS);
    }

    @Test
    @Transactional
    public void createBaselineEntryVersionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = baselineEntryVersionRepository.findAll().size();

        // Create the BaselineEntryVersion with an existing ID
        baselineEntryVersion.setId(1L);
        BaselineEntryVersionDTO baselineEntryVersionDTO = baselineEntryVersionMapper.toDto(baselineEntryVersion);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBaselineEntryVersionMockMvc.perform(post("/api/baseline-entry-versions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(baselineEntryVersionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BaselineEntryVersion in the database
        List<BaselineEntryVersion> baselineEntryVersionList = baselineEntryVersionRepository.findAll();
        assertThat(baselineEntryVersionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllBaselineEntryVersions() throws Exception {
        // Initialize the database
        baselineEntryVersionRepository.saveAndFlush(baselineEntryVersion);

        // Get all the baselineEntryVersionList
        restBaselineEntryVersionMockMvc.perform(get("/api/baseline-entry-versions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(baselineEntryVersion.getId().intValue())))
            .andExpect(jsonPath("$.[*].creationTime").value(hasItem(DEFAULT_CREATION_TIME.toString())))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION.toString())))
            .andExpect(jsonPath("$.[*].fileName").value(hasItem(DEFAULT_FILE_NAME.toString())))
            .andExpect(jsonPath("$.[*].blobContentType").value(hasItem(DEFAULT_BLOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].blob").value(hasItem(Base64Utils.encodeToString(DEFAULT_BLOB))))
            .andExpect(jsonPath("$.[*].uploadStatus").value(hasItem(DEFAULT_UPLOAD_STATUS.toString())));
    }

    @Test
    @Transactional
    public void getBaselineEntryVersion() throws Exception {
        // Initialize the database
        baselineEntryVersionRepository.saveAndFlush(baselineEntryVersion);

        // Get the baselineEntryVersion
        restBaselineEntryVersionMockMvc.perform(get("/api/baseline-entry-versions/{id}", baselineEntryVersion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(baselineEntryVersion.getId().intValue()))
            .andExpect(jsonPath("$.creationTime").value(DEFAULT_CREATION_TIME.toString()))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION.toString()))
            .andExpect(jsonPath("$.fileName").value(DEFAULT_FILE_NAME.toString()))
            .andExpect(jsonPath("$.blobContentType").value(DEFAULT_BLOB_CONTENT_TYPE))
            .andExpect(jsonPath("$.blob").value(Base64Utils.encodeToString(DEFAULT_BLOB)))
            .andExpect(jsonPath("$.uploadStatus").value(DEFAULT_UPLOAD_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBaselineEntryVersion() throws Exception {
        // Get the baselineEntryVersion
        restBaselineEntryVersionMockMvc.perform(get("/api/baseline-entry-versions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBaselineEntryVersion() throws Exception {
        // Initialize the database
        baselineEntryVersionRepository.saveAndFlush(baselineEntryVersion);
        int databaseSizeBeforeUpdate = baselineEntryVersionRepository.findAll().size();

        // Update the baselineEntryVersion
        BaselineEntryVersion updatedBaselineEntryVersion = baselineEntryVersionRepository.findOne(baselineEntryVersion.getId());
        // Disconnect from session so that the updates on updatedBaselineEntryVersion are not directly saved in db
        em.detach(updatedBaselineEntryVersion);
        updatedBaselineEntryVersion
            .creationTime(UPDATED_CREATION_TIME)
            .version(UPDATED_VERSION)
            .fileName(UPDATED_FILE_NAME)
            .blob(UPDATED_BLOB)
            .blobContentType(UPDATED_BLOB_CONTENT_TYPE)
            .uploadStatus(UPDATED_UPLOAD_STATUS);
        BaselineEntryVersionDTO baselineEntryVersionDTO = baselineEntryVersionMapper.toDto(updatedBaselineEntryVersion);

        restBaselineEntryVersionMockMvc.perform(put("/api/baseline-entry-versions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(baselineEntryVersionDTO)))
            .andExpect(status().isOk());

        // Validate the BaselineEntryVersion in the database
        List<BaselineEntryVersion> baselineEntryVersionList = baselineEntryVersionRepository.findAll();
        assertThat(baselineEntryVersionList).hasSize(databaseSizeBeforeUpdate);
        BaselineEntryVersion testBaselineEntryVersion = baselineEntryVersionList.get(baselineEntryVersionList.size() - 1);
        assertThat(testBaselineEntryVersion.getCreationTime()).isEqualTo(UPDATED_CREATION_TIME);
        assertThat(testBaselineEntryVersion.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testBaselineEntryVersion.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testBaselineEntryVersion.getBlob()).isEqualTo(UPDATED_BLOB);
        assertThat(testBaselineEntryVersion.getBlobContentType()).isEqualTo(UPDATED_BLOB_CONTENT_TYPE);
        assertThat(testBaselineEntryVersion.getUploadStatus()).isEqualTo(UPDATED_UPLOAD_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingBaselineEntryVersion() throws Exception {
        int databaseSizeBeforeUpdate = baselineEntryVersionRepository.findAll().size();

        // Create the BaselineEntryVersion
        BaselineEntryVersionDTO baselineEntryVersionDTO = baselineEntryVersionMapper.toDto(baselineEntryVersion);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBaselineEntryVersionMockMvc.perform(put("/api/baseline-entry-versions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(baselineEntryVersionDTO)))
            .andExpect(status().isCreated());

        // Validate the BaselineEntryVersion in the database
        List<BaselineEntryVersion> baselineEntryVersionList = baselineEntryVersionRepository.findAll();
        assertThat(baselineEntryVersionList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteBaselineEntryVersion() throws Exception {
        // Initialize the database
        baselineEntryVersionRepository.saveAndFlush(baselineEntryVersion);
        int databaseSizeBeforeDelete = baselineEntryVersionRepository.findAll().size();

        // Get the baselineEntryVersion
        restBaselineEntryVersionMockMvc.perform(delete("/api/baseline-entry-versions/{id}", baselineEntryVersion.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<BaselineEntryVersion> baselineEntryVersionList = baselineEntryVersionRepository.findAll();
        assertThat(baselineEntryVersionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BaselineEntryVersion.class);
        BaselineEntryVersion baselineEntryVersion1 = new BaselineEntryVersion();
        baselineEntryVersion1.setId(1L);
        BaselineEntryVersion baselineEntryVersion2 = new BaselineEntryVersion();
        baselineEntryVersion2.setId(baselineEntryVersion1.getId());
        assertThat(baselineEntryVersion1).isEqualTo(baselineEntryVersion2);
        baselineEntryVersion2.setId(2L);
        assertThat(baselineEntryVersion1).isNotEqualTo(baselineEntryVersion2);
        baselineEntryVersion1.setId(null);
        assertThat(baselineEntryVersion1).isNotEqualTo(baselineEntryVersion2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BaselineEntryVersionDTO.class);
        BaselineEntryVersionDTO baselineEntryVersionDTO1 = new BaselineEntryVersionDTO();
        baselineEntryVersionDTO1.setId(1L);
        BaselineEntryVersionDTO baselineEntryVersionDTO2 = new BaselineEntryVersionDTO();
        assertThat(baselineEntryVersionDTO1).isNotEqualTo(baselineEntryVersionDTO2);
        baselineEntryVersionDTO2.setId(baselineEntryVersionDTO1.getId());
        assertThat(baselineEntryVersionDTO1).isEqualTo(baselineEntryVersionDTO2);
        baselineEntryVersionDTO2.setId(2L);
        assertThat(baselineEntryVersionDTO1).isNotEqualTo(baselineEntryVersionDTO2);
        baselineEntryVersionDTO1.setId(null);
        assertThat(baselineEntryVersionDTO1).isNotEqualTo(baselineEntryVersionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(baselineEntryVersionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(baselineEntryVersionMapper.fromId(null)).isNull();
    }
}
