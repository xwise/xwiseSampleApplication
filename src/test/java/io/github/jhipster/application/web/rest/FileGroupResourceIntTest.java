package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.XwiseSampleApplicationApp;

import io.github.jhipster.application.domain.FileGroup;
import io.github.jhipster.application.domain.Baseline;
import io.github.jhipster.application.domain.FileEntry;
import io.github.jhipster.application.repository.FileGroupRepository;
import io.github.jhipster.application.service.FileGroupService;
import io.github.jhipster.application.service.dto.FileGroupDTO;
import io.github.jhipster.application.service.mapper.FileGroupMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.FileGroupCriteria;
import io.github.jhipster.application.service.FileGroupQueryService;

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
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the FileGroupResource REST controller.
 *
 * @see FileGroupResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XwiseSampleApplicationApp.class)
public class FileGroupResourceIntTest {

    private static final String DEFAULT_GROUP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_GROUP_NAME = "BBBBBBBBBB";

    @Autowired
    private FileGroupRepository fileGroupRepository;

    @Autowired
    private FileGroupMapper fileGroupMapper;

    @Autowired
    private FileGroupService fileGroupService;

    @Autowired
    private FileGroupQueryService fileGroupQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFileGroupMockMvc;

    private FileGroup fileGroup;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FileGroupResource fileGroupResource = new FileGroupResource(fileGroupService, fileGroupQueryService);
        this.restFileGroupMockMvc = MockMvcBuilders.standaloneSetup(fileGroupResource)
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
    public static FileGroup createEntity(EntityManager em) {
        FileGroup fileGroup = new FileGroup()
            .groupName(DEFAULT_GROUP_NAME);
        return fileGroup;
    }

    @Before
    public void initTest() {
        fileGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createFileGroup() throws Exception {
        int databaseSizeBeforeCreate = fileGroupRepository.findAll().size();

        // Create the FileGroup
        FileGroupDTO fileGroupDTO = fileGroupMapper.toDto(fileGroup);
        restFileGroupMockMvc.perform(post("/api/file-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fileGroupDTO)))
            .andExpect(status().isCreated());

        // Validate the FileGroup in the database
        List<FileGroup> fileGroupList = fileGroupRepository.findAll();
        assertThat(fileGroupList).hasSize(databaseSizeBeforeCreate + 1);
        FileGroup testFileGroup = fileGroupList.get(fileGroupList.size() - 1);
        assertThat(testFileGroup.getGroupName()).isEqualTo(DEFAULT_GROUP_NAME);
    }

    @Test
    @Transactional
    public void createFileGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fileGroupRepository.findAll().size();

        // Create the FileGroup with an existing ID
        fileGroup.setId(1L);
        FileGroupDTO fileGroupDTO = fileGroupMapper.toDto(fileGroup);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFileGroupMockMvc.perform(post("/api/file-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fileGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FileGroup in the database
        List<FileGroup> fileGroupList = fileGroupRepository.findAll();
        assertThat(fileGroupList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFileGroups() throws Exception {
        // Initialize the database
        fileGroupRepository.saveAndFlush(fileGroup);

        // Get all the fileGroupList
        restFileGroupMockMvc.perform(get("/api/file-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fileGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupName").value(hasItem(DEFAULT_GROUP_NAME.toString())));
    }

    @Test
    @Transactional
    public void getFileGroup() throws Exception {
        // Initialize the database
        fileGroupRepository.saveAndFlush(fileGroup);

        // Get the fileGroup
        restFileGroupMockMvc.perform(get("/api/file-groups/{id}", fileGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fileGroup.getId().intValue()))
            .andExpect(jsonPath("$.groupName").value(DEFAULT_GROUP_NAME.toString()));
    }

    @Test
    @Transactional
    public void getAllFileGroupsByGroupNameIsEqualToSomething() throws Exception {
        // Initialize the database
        fileGroupRepository.saveAndFlush(fileGroup);

        // Get all the fileGroupList where groupName equals to DEFAULT_GROUP_NAME
        defaultFileGroupShouldBeFound("groupName.equals=" + DEFAULT_GROUP_NAME);

        // Get all the fileGroupList where groupName equals to UPDATED_GROUP_NAME
        defaultFileGroupShouldNotBeFound("groupName.equals=" + UPDATED_GROUP_NAME);
    }

    @Test
    @Transactional
    public void getAllFileGroupsByGroupNameIsInShouldWork() throws Exception {
        // Initialize the database
        fileGroupRepository.saveAndFlush(fileGroup);

        // Get all the fileGroupList where groupName in DEFAULT_GROUP_NAME or UPDATED_GROUP_NAME
        defaultFileGroupShouldBeFound("groupName.in=" + DEFAULT_GROUP_NAME + "," + UPDATED_GROUP_NAME);

        // Get all the fileGroupList where groupName equals to UPDATED_GROUP_NAME
        defaultFileGroupShouldNotBeFound("groupName.in=" + UPDATED_GROUP_NAME);
    }

    @Test
    @Transactional
    public void getAllFileGroupsByGroupNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        fileGroupRepository.saveAndFlush(fileGroup);

        // Get all the fileGroupList where groupName is not null
        defaultFileGroupShouldBeFound("groupName.specified=true");

        // Get all the fileGroupList where groupName is null
        defaultFileGroupShouldNotBeFound("groupName.specified=false");
    }

    @Test
    @Transactional
    public void getAllFileGroupsByBaselineIsEqualToSomething() throws Exception {
        // Initialize the database
        Baseline baseline = BaselineResourceIntTest.createEntity(em);
        em.persist(baseline);
        em.flush();
        fileGroup.addBaseline(baseline);
        fileGroupRepository.saveAndFlush(fileGroup);
        Long baselineId = baseline.getId();

        // Get all the fileGroupList where baseline equals to baselineId
        defaultFileGroupShouldBeFound("baselineId.equals=" + baselineId);

        // Get all the fileGroupList where baseline equals to baselineId + 1
        defaultFileGroupShouldNotBeFound("baselineId.equals=" + (baselineId + 1));
    }


    @Test
    @Transactional
    public void getAllFileGroupsByFileEntryIsEqualToSomething() throws Exception {
        // Initialize the database
        FileEntry fileEntry = FileEntryResourceIntTest.createEntity(em);
        em.persist(fileEntry);
        em.flush();
        fileGroup.addFileEntry(fileEntry);
        fileGroupRepository.saveAndFlush(fileGroup);
        Long fileEntryId = fileEntry.getId();

        // Get all the fileGroupList where fileEntry equals to fileEntryId
        defaultFileGroupShouldBeFound("fileEntryId.equals=" + fileEntryId);

        // Get all the fileGroupList where fileEntry equals to fileEntryId + 1
        defaultFileGroupShouldNotBeFound("fileEntryId.equals=" + (fileEntryId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultFileGroupShouldBeFound(String filter) throws Exception {
        restFileGroupMockMvc.perform(get("/api/file-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fileGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].groupName").value(hasItem(DEFAULT_GROUP_NAME.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultFileGroupShouldNotBeFound(String filter) throws Exception {
        restFileGroupMockMvc.perform(get("/api/file-groups?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingFileGroup() throws Exception {
        // Get the fileGroup
        restFileGroupMockMvc.perform(get("/api/file-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFileGroup() throws Exception {
        // Initialize the database
        fileGroupRepository.saveAndFlush(fileGroup);
        int databaseSizeBeforeUpdate = fileGroupRepository.findAll().size();

        // Update the fileGroup
        FileGroup updatedFileGroup = fileGroupRepository.findOne(fileGroup.getId());
        // Disconnect from session so that the updates on updatedFileGroup are not directly saved in db
        em.detach(updatedFileGroup);
        updatedFileGroup
            .groupName(UPDATED_GROUP_NAME);
        FileGroupDTO fileGroupDTO = fileGroupMapper.toDto(updatedFileGroup);

        restFileGroupMockMvc.perform(put("/api/file-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fileGroupDTO)))
            .andExpect(status().isOk());

        // Validate the FileGroup in the database
        List<FileGroup> fileGroupList = fileGroupRepository.findAll();
        assertThat(fileGroupList).hasSize(databaseSizeBeforeUpdate);
        FileGroup testFileGroup = fileGroupList.get(fileGroupList.size() - 1);
        assertThat(testFileGroup.getGroupName()).isEqualTo(UPDATED_GROUP_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingFileGroup() throws Exception {
        int databaseSizeBeforeUpdate = fileGroupRepository.findAll().size();

        // Create the FileGroup
        FileGroupDTO fileGroupDTO = fileGroupMapper.toDto(fileGroup);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFileGroupMockMvc.perform(put("/api/file-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fileGroupDTO)))
            .andExpect(status().isCreated());

        // Validate the FileGroup in the database
        List<FileGroup> fileGroupList = fileGroupRepository.findAll();
        assertThat(fileGroupList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteFileGroup() throws Exception {
        // Initialize the database
        fileGroupRepository.saveAndFlush(fileGroup);
        int databaseSizeBeforeDelete = fileGroupRepository.findAll().size();

        // Get the fileGroup
        restFileGroupMockMvc.perform(delete("/api/file-groups/{id}", fileGroup.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<FileGroup> fileGroupList = fileGroupRepository.findAll();
        assertThat(fileGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FileGroup.class);
        FileGroup fileGroup1 = new FileGroup();
        fileGroup1.setId(1L);
        FileGroup fileGroup2 = new FileGroup();
        fileGroup2.setId(fileGroup1.getId());
        assertThat(fileGroup1).isEqualTo(fileGroup2);
        fileGroup2.setId(2L);
        assertThat(fileGroup1).isNotEqualTo(fileGroup2);
        fileGroup1.setId(null);
        assertThat(fileGroup1).isNotEqualTo(fileGroup2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FileGroupDTO.class);
        FileGroupDTO fileGroupDTO1 = new FileGroupDTO();
        fileGroupDTO1.setId(1L);
        FileGroupDTO fileGroupDTO2 = new FileGroupDTO();
        assertThat(fileGroupDTO1).isNotEqualTo(fileGroupDTO2);
        fileGroupDTO2.setId(fileGroupDTO1.getId());
        assertThat(fileGroupDTO1).isEqualTo(fileGroupDTO2);
        fileGroupDTO2.setId(2L);
        assertThat(fileGroupDTO1).isNotEqualTo(fileGroupDTO2);
        fileGroupDTO1.setId(null);
        assertThat(fileGroupDTO1).isNotEqualTo(fileGroupDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(fileGroupMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(fileGroupMapper.fromId(null)).isNull();
    }
}
