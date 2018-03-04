package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.XwiseSampleApplicationApp;

import io.github.jhipster.application.domain.MyFirstComponent;
import io.github.jhipster.application.repository.MyFirstComponentRepository;
import io.github.jhipster.application.service.MyFirstComponentService;
import io.github.jhipster.application.service.dto.MyFirstComponentDTO;
import io.github.jhipster.application.service.mapper.MyFirstComponentMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.MyFirstComponentCriteria;
import io.github.jhipster.application.service.MyFirstComponentQueryService;

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

/**
 * Test class for the MyFirstComponentResource REST controller.
 *
 * @see MyFirstComponentResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XwiseSampleApplicationApp.class)
public class MyFirstComponentResourceIntTest {

    private static final Instant DEFAULT_CREATION_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATION_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ATTRIBUTE_1 = "AAAAAAAAAA";
    private static final String UPDATED_ATTRIBUTE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ATTRIBUTE_2 = "AAAAAAAAAA";
    private static final String UPDATED_ATTRIBUTE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_ATTRIBUTE_3 = "AAAAAAAAAA";
    private static final String UPDATED_ATTRIBUTE_3 = "BBBBBBBBBB";

    private static final String DEFAULT_ATTRIBUTE_4 = "AAAAAAAAAA";
    private static final String UPDATED_ATTRIBUTE_4 = "BBBBBBBBBB";

    private static final String DEFAULT_ATTRIBUTE_5 = "AAAAAAAAAA";
    private static final String UPDATED_ATTRIBUTE_5 = "BBBBBBBBBB";

    private static final String DEFAULT_ATTRIBUTE_6 = "AAAAAAAAAA";
    private static final String UPDATED_ATTRIBUTE_6 = "BBBBBBBBBB";

    private static final String DEFAULT_ATTRIBUTE_7 = "AAAAAAAAAA";
    private static final String UPDATED_ATTRIBUTE_7 = "BBBBBBBBBB";

    @Autowired
    private MyFirstComponentRepository myFirstComponentRepository;

    @Autowired
    private MyFirstComponentMapper myFirstComponentMapper;

    @Autowired
    private MyFirstComponentService myFirstComponentService;

    @Autowired
    private MyFirstComponentQueryService myFirstComponentQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMyFirstComponentMockMvc;

    private MyFirstComponent myFirstComponent;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MyFirstComponentResource myFirstComponentResource = new MyFirstComponentResource(myFirstComponentService, myFirstComponentQueryService);
        this.restMyFirstComponentMockMvc = MockMvcBuilders.standaloneSetup(myFirstComponentResource)
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
    public static MyFirstComponent createEntity(EntityManager em) {
        MyFirstComponent myFirstComponent = new MyFirstComponent()
            .creationTime(DEFAULT_CREATION_TIME)
            .name(DEFAULT_NAME)
            .attribute1(DEFAULT_ATTRIBUTE_1)
            .attribute2(DEFAULT_ATTRIBUTE_2)
            .attribute3(DEFAULT_ATTRIBUTE_3)
            .attribute4(DEFAULT_ATTRIBUTE_4)
            .attribute5(DEFAULT_ATTRIBUTE_5)
            .attribute6(DEFAULT_ATTRIBUTE_6)
            .attribute7(DEFAULT_ATTRIBUTE_7);
        return myFirstComponent;
    }

    @Before
    public void initTest() {
        myFirstComponent = createEntity(em);
    }

    @Test
    @Transactional
    public void createMyFirstComponent() throws Exception {
        int databaseSizeBeforeCreate = myFirstComponentRepository.findAll().size();

        // Create the MyFirstComponent
        MyFirstComponentDTO myFirstComponentDTO = myFirstComponentMapper.toDto(myFirstComponent);
        restMyFirstComponentMockMvc.perform(post("/api/my-first-components")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(myFirstComponentDTO)))
            .andExpect(status().isCreated());

        // Validate the MyFirstComponent in the database
        List<MyFirstComponent> myFirstComponentList = myFirstComponentRepository.findAll();
        assertThat(myFirstComponentList).hasSize(databaseSizeBeforeCreate + 1);
        MyFirstComponent testMyFirstComponent = myFirstComponentList.get(myFirstComponentList.size() - 1);
        assertThat(testMyFirstComponent.getCreationTime()).isEqualTo(DEFAULT_CREATION_TIME);
        assertThat(testMyFirstComponent.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMyFirstComponent.getAttribute1()).isEqualTo(DEFAULT_ATTRIBUTE_1);
        assertThat(testMyFirstComponent.getAttribute2()).isEqualTo(DEFAULT_ATTRIBUTE_2);
        assertThat(testMyFirstComponent.getAttribute3()).isEqualTo(DEFAULT_ATTRIBUTE_3);
        assertThat(testMyFirstComponent.getAttribute4()).isEqualTo(DEFAULT_ATTRIBUTE_4);
        assertThat(testMyFirstComponent.getAttribute5()).isEqualTo(DEFAULT_ATTRIBUTE_5);
        assertThat(testMyFirstComponent.getAttribute6()).isEqualTo(DEFAULT_ATTRIBUTE_6);
        assertThat(testMyFirstComponent.getAttribute7()).isEqualTo(DEFAULT_ATTRIBUTE_7);
    }

    @Test
    @Transactional
    public void createMyFirstComponentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = myFirstComponentRepository.findAll().size();

        // Create the MyFirstComponent with an existing ID
        myFirstComponent.setId(1L);
        MyFirstComponentDTO myFirstComponentDTO = myFirstComponentMapper.toDto(myFirstComponent);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMyFirstComponentMockMvc.perform(post("/api/my-first-components")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(myFirstComponentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MyFirstComponent in the database
        List<MyFirstComponent> myFirstComponentList = myFirstComponentRepository.findAll();
        assertThat(myFirstComponentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMyFirstComponents() throws Exception {
        // Initialize the database
        myFirstComponentRepository.saveAndFlush(myFirstComponent);

        // Get all the myFirstComponentList
        restMyFirstComponentMockMvc.perform(get("/api/my-first-components?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(myFirstComponent.getId().intValue())))
            .andExpect(jsonPath("$.[*].creationTime").value(hasItem(DEFAULT_CREATION_TIME.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].attribute1").value(hasItem(DEFAULT_ATTRIBUTE_1.toString())))
            .andExpect(jsonPath("$.[*].attribute2").value(hasItem(DEFAULT_ATTRIBUTE_2.toString())))
            .andExpect(jsonPath("$.[*].attribute3").value(hasItem(DEFAULT_ATTRIBUTE_3.toString())))
            .andExpect(jsonPath("$.[*].attribute4").value(hasItem(DEFAULT_ATTRIBUTE_4.toString())))
            .andExpect(jsonPath("$.[*].attribute5").value(hasItem(DEFAULT_ATTRIBUTE_5.toString())))
            .andExpect(jsonPath("$.[*].attribute6").value(hasItem(DEFAULT_ATTRIBUTE_6.toString())))
            .andExpect(jsonPath("$.[*].attribute7").value(hasItem(DEFAULT_ATTRIBUTE_7.toString())));
    }

    @Test
    @Transactional
    public void getMyFirstComponent() throws Exception {
        // Initialize the database
        myFirstComponentRepository.saveAndFlush(myFirstComponent);

        // Get the myFirstComponent
        restMyFirstComponentMockMvc.perform(get("/api/my-first-components/{id}", myFirstComponent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(myFirstComponent.getId().intValue()))
            .andExpect(jsonPath("$.creationTime").value(DEFAULT_CREATION_TIME.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.attribute1").value(DEFAULT_ATTRIBUTE_1.toString()))
            .andExpect(jsonPath("$.attribute2").value(DEFAULT_ATTRIBUTE_2.toString()))
            .andExpect(jsonPath("$.attribute3").value(DEFAULT_ATTRIBUTE_3.toString()))
            .andExpect(jsonPath("$.attribute4").value(DEFAULT_ATTRIBUTE_4.toString()))
            .andExpect(jsonPath("$.attribute5").value(DEFAULT_ATTRIBUTE_5.toString()))
            .andExpect(jsonPath("$.attribute6").value(DEFAULT_ATTRIBUTE_6.toString()))
            .andExpect(jsonPath("$.attribute7").value(DEFAULT_ATTRIBUTE_7.toString()));
    }

    @Test
    @Transactional
    public void getAllMyFirstComponentsByCreationTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        myFirstComponentRepository.saveAndFlush(myFirstComponent);

        // Get all the myFirstComponentList where creationTime equals to DEFAULT_CREATION_TIME
        defaultMyFirstComponentShouldBeFound("creationTime.equals=" + DEFAULT_CREATION_TIME);

        // Get all the myFirstComponentList where creationTime equals to UPDATED_CREATION_TIME
        defaultMyFirstComponentShouldNotBeFound("creationTime.equals=" + UPDATED_CREATION_TIME);
    }

    @Test
    @Transactional
    public void getAllMyFirstComponentsByCreationTimeIsInShouldWork() throws Exception {
        // Initialize the database
        myFirstComponentRepository.saveAndFlush(myFirstComponent);

        // Get all the myFirstComponentList where creationTime in DEFAULT_CREATION_TIME or UPDATED_CREATION_TIME
        defaultMyFirstComponentShouldBeFound("creationTime.in=" + DEFAULT_CREATION_TIME + "," + UPDATED_CREATION_TIME);

        // Get all the myFirstComponentList where creationTime equals to UPDATED_CREATION_TIME
        defaultMyFirstComponentShouldNotBeFound("creationTime.in=" + UPDATED_CREATION_TIME);
    }

    @Test
    @Transactional
    public void getAllMyFirstComponentsByCreationTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        myFirstComponentRepository.saveAndFlush(myFirstComponent);

        // Get all the myFirstComponentList where creationTime is not null
        defaultMyFirstComponentShouldBeFound("creationTime.specified=true");

        // Get all the myFirstComponentList where creationTime is null
        defaultMyFirstComponentShouldNotBeFound("creationTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllMyFirstComponentsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        myFirstComponentRepository.saveAndFlush(myFirstComponent);

        // Get all the myFirstComponentList where name equals to DEFAULT_NAME
        defaultMyFirstComponentShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the myFirstComponentList where name equals to UPDATED_NAME
        defaultMyFirstComponentShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllMyFirstComponentsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        myFirstComponentRepository.saveAndFlush(myFirstComponent);

        // Get all the myFirstComponentList where name in DEFAULT_NAME or UPDATED_NAME
        defaultMyFirstComponentShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the myFirstComponentList where name equals to UPDATED_NAME
        defaultMyFirstComponentShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllMyFirstComponentsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        myFirstComponentRepository.saveAndFlush(myFirstComponent);

        // Get all the myFirstComponentList where name is not null
        defaultMyFirstComponentShouldBeFound("name.specified=true");

        // Get all the myFirstComponentList where name is null
        defaultMyFirstComponentShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllMyFirstComponentsByAttribute1IsEqualToSomething() throws Exception {
        // Initialize the database
        myFirstComponentRepository.saveAndFlush(myFirstComponent);

        // Get all the myFirstComponentList where attribute1 equals to DEFAULT_ATTRIBUTE_1
        defaultMyFirstComponentShouldBeFound("attribute1.equals=" + DEFAULT_ATTRIBUTE_1);

        // Get all the myFirstComponentList where attribute1 equals to UPDATED_ATTRIBUTE_1
        defaultMyFirstComponentShouldNotBeFound("attribute1.equals=" + UPDATED_ATTRIBUTE_1);
    }

    @Test
    @Transactional
    public void getAllMyFirstComponentsByAttribute1IsInShouldWork() throws Exception {
        // Initialize the database
        myFirstComponentRepository.saveAndFlush(myFirstComponent);

        // Get all the myFirstComponentList where attribute1 in DEFAULT_ATTRIBUTE_1 or UPDATED_ATTRIBUTE_1
        defaultMyFirstComponentShouldBeFound("attribute1.in=" + DEFAULT_ATTRIBUTE_1 + "," + UPDATED_ATTRIBUTE_1);

        // Get all the myFirstComponentList where attribute1 equals to UPDATED_ATTRIBUTE_1
        defaultMyFirstComponentShouldNotBeFound("attribute1.in=" + UPDATED_ATTRIBUTE_1);
    }

    @Test
    @Transactional
    public void getAllMyFirstComponentsByAttribute1IsNullOrNotNull() throws Exception {
        // Initialize the database
        myFirstComponentRepository.saveAndFlush(myFirstComponent);

        // Get all the myFirstComponentList where attribute1 is not null
        defaultMyFirstComponentShouldBeFound("attribute1.specified=true");

        // Get all the myFirstComponentList where attribute1 is null
        defaultMyFirstComponentShouldNotBeFound("attribute1.specified=false");
    }

    @Test
    @Transactional
    public void getAllMyFirstComponentsByAttribute2IsEqualToSomething() throws Exception {
        // Initialize the database
        myFirstComponentRepository.saveAndFlush(myFirstComponent);

        // Get all the myFirstComponentList where attribute2 equals to DEFAULT_ATTRIBUTE_2
        defaultMyFirstComponentShouldBeFound("attribute2.equals=" + DEFAULT_ATTRIBUTE_2);

        // Get all the myFirstComponentList where attribute2 equals to UPDATED_ATTRIBUTE_2
        defaultMyFirstComponentShouldNotBeFound("attribute2.equals=" + UPDATED_ATTRIBUTE_2);
    }

    @Test
    @Transactional
    public void getAllMyFirstComponentsByAttribute2IsInShouldWork() throws Exception {
        // Initialize the database
        myFirstComponentRepository.saveAndFlush(myFirstComponent);

        // Get all the myFirstComponentList where attribute2 in DEFAULT_ATTRIBUTE_2 or UPDATED_ATTRIBUTE_2
        defaultMyFirstComponentShouldBeFound("attribute2.in=" + DEFAULT_ATTRIBUTE_2 + "," + UPDATED_ATTRIBUTE_2);

        // Get all the myFirstComponentList where attribute2 equals to UPDATED_ATTRIBUTE_2
        defaultMyFirstComponentShouldNotBeFound("attribute2.in=" + UPDATED_ATTRIBUTE_2);
    }

    @Test
    @Transactional
    public void getAllMyFirstComponentsByAttribute2IsNullOrNotNull() throws Exception {
        // Initialize the database
        myFirstComponentRepository.saveAndFlush(myFirstComponent);

        // Get all the myFirstComponentList where attribute2 is not null
        defaultMyFirstComponentShouldBeFound("attribute2.specified=true");

        // Get all the myFirstComponentList where attribute2 is null
        defaultMyFirstComponentShouldNotBeFound("attribute2.specified=false");
    }

    @Test
    @Transactional
    public void getAllMyFirstComponentsByAttribute3IsEqualToSomething() throws Exception {
        // Initialize the database
        myFirstComponentRepository.saveAndFlush(myFirstComponent);

        // Get all the myFirstComponentList where attribute3 equals to DEFAULT_ATTRIBUTE_3
        defaultMyFirstComponentShouldBeFound("attribute3.equals=" + DEFAULT_ATTRIBUTE_3);

        // Get all the myFirstComponentList where attribute3 equals to UPDATED_ATTRIBUTE_3
        defaultMyFirstComponentShouldNotBeFound("attribute3.equals=" + UPDATED_ATTRIBUTE_3);
    }

    @Test
    @Transactional
    public void getAllMyFirstComponentsByAttribute3IsInShouldWork() throws Exception {
        // Initialize the database
        myFirstComponentRepository.saveAndFlush(myFirstComponent);

        // Get all the myFirstComponentList where attribute3 in DEFAULT_ATTRIBUTE_3 or UPDATED_ATTRIBUTE_3
        defaultMyFirstComponentShouldBeFound("attribute3.in=" + DEFAULT_ATTRIBUTE_3 + "," + UPDATED_ATTRIBUTE_3);

        // Get all the myFirstComponentList where attribute3 equals to UPDATED_ATTRIBUTE_3
        defaultMyFirstComponentShouldNotBeFound("attribute3.in=" + UPDATED_ATTRIBUTE_3);
    }

    @Test
    @Transactional
    public void getAllMyFirstComponentsByAttribute3IsNullOrNotNull() throws Exception {
        // Initialize the database
        myFirstComponentRepository.saveAndFlush(myFirstComponent);

        // Get all the myFirstComponentList where attribute3 is not null
        defaultMyFirstComponentShouldBeFound("attribute3.specified=true");

        // Get all the myFirstComponentList where attribute3 is null
        defaultMyFirstComponentShouldNotBeFound("attribute3.specified=false");
    }

    @Test
    @Transactional
    public void getAllMyFirstComponentsByAttribute4IsEqualToSomething() throws Exception {
        // Initialize the database
        myFirstComponentRepository.saveAndFlush(myFirstComponent);

        // Get all the myFirstComponentList where attribute4 equals to DEFAULT_ATTRIBUTE_4
        defaultMyFirstComponentShouldBeFound("attribute4.equals=" + DEFAULT_ATTRIBUTE_4);

        // Get all the myFirstComponentList where attribute4 equals to UPDATED_ATTRIBUTE_4
        defaultMyFirstComponentShouldNotBeFound("attribute4.equals=" + UPDATED_ATTRIBUTE_4);
    }

    @Test
    @Transactional
    public void getAllMyFirstComponentsByAttribute4IsInShouldWork() throws Exception {
        // Initialize the database
        myFirstComponentRepository.saveAndFlush(myFirstComponent);

        // Get all the myFirstComponentList where attribute4 in DEFAULT_ATTRIBUTE_4 or UPDATED_ATTRIBUTE_4
        defaultMyFirstComponentShouldBeFound("attribute4.in=" + DEFAULT_ATTRIBUTE_4 + "," + UPDATED_ATTRIBUTE_4);

        // Get all the myFirstComponentList where attribute4 equals to UPDATED_ATTRIBUTE_4
        defaultMyFirstComponentShouldNotBeFound("attribute4.in=" + UPDATED_ATTRIBUTE_4);
    }

    @Test
    @Transactional
    public void getAllMyFirstComponentsByAttribute4IsNullOrNotNull() throws Exception {
        // Initialize the database
        myFirstComponentRepository.saveAndFlush(myFirstComponent);

        // Get all the myFirstComponentList where attribute4 is not null
        defaultMyFirstComponentShouldBeFound("attribute4.specified=true");

        // Get all the myFirstComponentList where attribute4 is null
        defaultMyFirstComponentShouldNotBeFound("attribute4.specified=false");
    }

    @Test
    @Transactional
    public void getAllMyFirstComponentsByAttribute5IsEqualToSomething() throws Exception {
        // Initialize the database
        myFirstComponentRepository.saveAndFlush(myFirstComponent);

        // Get all the myFirstComponentList where attribute5 equals to DEFAULT_ATTRIBUTE_5
        defaultMyFirstComponentShouldBeFound("attribute5.equals=" + DEFAULT_ATTRIBUTE_5);

        // Get all the myFirstComponentList where attribute5 equals to UPDATED_ATTRIBUTE_5
        defaultMyFirstComponentShouldNotBeFound("attribute5.equals=" + UPDATED_ATTRIBUTE_5);
    }

    @Test
    @Transactional
    public void getAllMyFirstComponentsByAttribute5IsInShouldWork() throws Exception {
        // Initialize the database
        myFirstComponentRepository.saveAndFlush(myFirstComponent);

        // Get all the myFirstComponentList where attribute5 in DEFAULT_ATTRIBUTE_5 or UPDATED_ATTRIBUTE_5
        defaultMyFirstComponentShouldBeFound("attribute5.in=" + DEFAULT_ATTRIBUTE_5 + "," + UPDATED_ATTRIBUTE_5);

        // Get all the myFirstComponentList where attribute5 equals to UPDATED_ATTRIBUTE_5
        defaultMyFirstComponentShouldNotBeFound("attribute5.in=" + UPDATED_ATTRIBUTE_5);
    }

    @Test
    @Transactional
    public void getAllMyFirstComponentsByAttribute5IsNullOrNotNull() throws Exception {
        // Initialize the database
        myFirstComponentRepository.saveAndFlush(myFirstComponent);

        // Get all the myFirstComponentList where attribute5 is not null
        defaultMyFirstComponentShouldBeFound("attribute5.specified=true");

        // Get all the myFirstComponentList where attribute5 is null
        defaultMyFirstComponentShouldNotBeFound("attribute5.specified=false");
    }

    @Test
    @Transactional
    public void getAllMyFirstComponentsByAttribute6IsEqualToSomething() throws Exception {
        // Initialize the database
        myFirstComponentRepository.saveAndFlush(myFirstComponent);

        // Get all the myFirstComponentList where attribute6 equals to DEFAULT_ATTRIBUTE_6
        defaultMyFirstComponentShouldBeFound("attribute6.equals=" + DEFAULT_ATTRIBUTE_6);

        // Get all the myFirstComponentList where attribute6 equals to UPDATED_ATTRIBUTE_6
        defaultMyFirstComponentShouldNotBeFound("attribute6.equals=" + UPDATED_ATTRIBUTE_6);
    }

    @Test
    @Transactional
    public void getAllMyFirstComponentsByAttribute6IsInShouldWork() throws Exception {
        // Initialize the database
        myFirstComponentRepository.saveAndFlush(myFirstComponent);

        // Get all the myFirstComponentList where attribute6 in DEFAULT_ATTRIBUTE_6 or UPDATED_ATTRIBUTE_6
        defaultMyFirstComponentShouldBeFound("attribute6.in=" + DEFAULT_ATTRIBUTE_6 + "," + UPDATED_ATTRIBUTE_6);

        // Get all the myFirstComponentList where attribute6 equals to UPDATED_ATTRIBUTE_6
        defaultMyFirstComponentShouldNotBeFound("attribute6.in=" + UPDATED_ATTRIBUTE_6);
    }

    @Test
    @Transactional
    public void getAllMyFirstComponentsByAttribute6IsNullOrNotNull() throws Exception {
        // Initialize the database
        myFirstComponentRepository.saveAndFlush(myFirstComponent);

        // Get all the myFirstComponentList where attribute6 is not null
        defaultMyFirstComponentShouldBeFound("attribute6.specified=true");

        // Get all the myFirstComponentList where attribute6 is null
        defaultMyFirstComponentShouldNotBeFound("attribute6.specified=false");
    }

    @Test
    @Transactional
    public void getAllMyFirstComponentsByAttribute7IsEqualToSomething() throws Exception {
        // Initialize the database
        myFirstComponentRepository.saveAndFlush(myFirstComponent);

        // Get all the myFirstComponentList where attribute7 equals to DEFAULT_ATTRIBUTE_7
        defaultMyFirstComponentShouldBeFound("attribute7.equals=" + DEFAULT_ATTRIBUTE_7);

        // Get all the myFirstComponentList where attribute7 equals to UPDATED_ATTRIBUTE_7
        defaultMyFirstComponentShouldNotBeFound("attribute7.equals=" + UPDATED_ATTRIBUTE_7);
    }

    @Test
    @Transactional
    public void getAllMyFirstComponentsByAttribute7IsInShouldWork() throws Exception {
        // Initialize the database
        myFirstComponentRepository.saveAndFlush(myFirstComponent);

        // Get all the myFirstComponentList where attribute7 in DEFAULT_ATTRIBUTE_7 or UPDATED_ATTRIBUTE_7
        defaultMyFirstComponentShouldBeFound("attribute7.in=" + DEFAULT_ATTRIBUTE_7 + "," + UPDATED_ATTRIBUTE_7);

        // Get all the myFirstComponentList where attribute7 equals to UPDATED_ATTRIBUTE_7
        defaultMyFirstComponentShouldNotBeFound("attribute7.in=" + UPDATED_ATTRIBUTE_7);
    }

    @Test
    @Transactional
    public void getAllMyFirstComponentsByAttribute7IsNullOrNotNull() throws Exception {
        // Initialize the database
        myFirstComponentRepository.saveAndFlush(myFirstComponent);

        // Get all the myFirstComponentList where attribute7 is not null
        defaultMyFirstComponentShouldBeFound("attribute7.specified=true");

        // Get all the myFirstComponentList where attribute7 is null
        defaultMyFirstComponentShouldNotBeFound("attribute7.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultMyFirstComponentShouldBeFound(String filter) throws Exception {
        restMyFirstComponentMockMvc.perform(get("/api/my-first-components?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(myFirstComponent.getId().intValue())))
            .andExpect(jsonPath("$.[*].creationTime").value(hasItem(DEFAULT_CREATION_TIME.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].attribute1").value(hasItem(DEFAULT_ATTRIBUTE_1.toString())))
            .andExpect(jsonPath("$.[*].attribute2").value(hasItem(DEFAULT_ATTRIBUTE_2.toString())))
            .andExpect(jsonPath("$.[*].attribute3").value(hasItem(DEFAULT_ATTRIBUTE_3.toString())))
            .andExpect(jsonPath("$.[*].attribute4").value(hasItem(DEFAULT_ATTRIBUTE_4.toString())))
            .andExpect(jsonPath("$.[*].attribute5").value(hasItem(DEFAULT_ATTRIBUTE_5.toString())))
            .andExpect(jsonPath("$.[*].attribute6").value(hasItem(DEFAULT_ATTRIBUTE_6.toString())))
            .andExpect(jsonPath("$.[*].attribute7").value(hasItem(DEFAULT_ATTRIBUTE_7.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultMyFirstComponentShouldNotBeFound(String filter) throws Exception {
        restMyFirstComponentMockMvc.perform(get("/api/my-first-components?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingMyFirstComponent() throws Exception {
        // Get the myFirstComponent
        restMyFirstComponentMockMvc.perform(get("/api/my-first-components/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMyFirstComponent() throws Exception {
        // Initialize the database
        myFirstComponentRepository.saveAndFlush(myFirstComponent);
        int databaseSizeBeforeUpdate = myFirstComponentRepository.findAll().size();

        // Update the myFirstComponent
        MyFirstComponent updatedMyFirstComponent = myFirstComponentRepository.findOne(myFirstComponent.getId());
        // Disconnect from session so that the updates on updatedMyFirstComponent are not directly saved in db
        em.detach(updatedMyFirstComponent);
        updatedMyFirstComponent
            .creationTime(UPDATED_CREATION_TIME)
            .name(UPDATED_NAME)
            .attribute1(UPDATED_ATTRIBUTE_1)
            .attribute2(UPDATED_ATTRIBUTE_2)
            .attribute3(UPDATED_ATTRIBUTE_3)
            .attribute4(UPDATED_ATTRIBUTE_4)
            .attribute5(UPDATED_ATTRIBUTE_5)
            .attribute6(UPDATED_ATTRIBUTE_6)
            .attribute7(UPDATED_ATTRIBUTE_7);
        MyFirstComponentDTO myFirstComponentDTO = myFirstComponentMapper.toDto(updatedMyFirstComponent);

        restMyFirstComponentMockMvc.perform(put("/api/my-first-components")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(myFirstComponentDTO)))
            .andExpect(status().isOk());

        // Validate the MyFirstComponent in the database
        List<MyFirstComponent> myFirstComponentList = myFirstComponentRepository.findAll();
        assertThat(myFirstComponentList).hasSize(databaseSizeBeforeUpdate);
        MyFirstComponent testMyFirstComponent = myFirstComponentList.get(myFirstComponentList.size() - 1);
        assertThat(testMyFirstComponent.getCreationTime()).isEqualTo(UPDATED_CREATION_TIME);
        assertThat(testMyFirstComponent.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMyFirstComponent.getAttribute1()).isEqualTo(UPDATED_ATTRIBUTE_1);
        assertThat(testMyFirstComponent.getAttribute2()).isEqualTo(UPDATED_ATTRIBUTE_2);
        assertThat(testMyFirstComponent.getAttribute3()).isEqualTo(UPDATED_ATTRIBUTE_3);
        assertThat(testMyFirstComponent.getAttribute4()).isEqualTo(UPDATED_ATTRIBUTE_4);
        assertThat(testMyFirstComponent.getAttribute5()).isEqualTo(UPDATED_ATTRIBUTE_5);
        assertThat(testMyFirstComponent.getAttribute6()).isEqualTo(UPDATED_ATTRIBUTE_6);
        assertThat(testMyFirstComponent.getAttribute7()).isEqualTo(UPDATED_ATTRIBUTE_7);
    }

    @Test
    @Transactional
    public void updateNonExistingMyFirstComponent() throws Exception {
        int databaseSizeBeforeUpdate = myFirstComponentRepository.findAll().size();

        // Create the MyFirstComponent
        MyFirstComponentDTO myFirstComponentDTO = myFirstComponentMapper.toDto(myFirstComponent);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMyFirstComponentMockMvc.perform(put("/api/my-first-components")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(myFirstComponentDTO)))
            .andExpect(status().isCreated());

        // Validate the MyFirstComponent in the database
        List<MyFirstComponent> myFirstComponentList = myFirstComponentRepository.findAll();
        assertThat(myFirstComponentList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteMyFirstComponent() throws Exception {
        // Initialize the database
        myFirstComponentRepository.saveAndFlush(myFirstComponent);
        int databaseSizeBeforeDelete = myFirstComponentRepository.findAll().size();

        // Get the myFirstComponent
        restMyFirstComponentMockMvc.perform(delete("/api/my-first-components/{id}", myFirstComponent.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MyFirstComponent> myFirstComponentList = myFirstComponentRepository.findAll();
        assertThat(myFirstComponentList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MyFirstComponent.class);
        MyFirstComponent myFirstComponent1 = new MyFirstComponent();
        myFirstComponent1.setId(1L);
        MyFirstComponent myFirstComponent2 = new MyFirstComponent();
        myFirstComponent2.setId(myFirstComponent1.getId());
        assertThat(myFirstComponent1).isEqualTo(myFirstComponent2);
        myFirstComponent2.setId(2L);
        assertThat(myFirstComponent1).isNotEqualTo(myFirstComponent2);
        myFirstComponent1.setId(null);
        assertThat(myFirstComponent1).isNotEqualTo(myFirstComponent2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MyFirstComponentDTO.class);
        MyFirstComponentDTO myFirstComponentDTO1 = new MyFirstComponentDTO();
        myFirstComponentDTO1.setId(1L);
        MyFirstComponentDTO myFirstComponentDTO2 = new MyFirstComponentDTO();
        assertThat(myFirstComponentDTO1).isNotEqualTo(myFirstComponentDTO2);
        myFirstComponentDTO2.setId(myFirstComponentDTO1.getId());
        assertThat(myFirstComponentDTO1).isEqualTo(myFirstComponentDTO2);
        myFirstComponentDTO2.setId(2L);
        assertThat(myFirstComponentDTO1).isNotEqualTo(myFirstComponentDTO2);
        myFirstComponentDTO1.setId(null);
        assertThat(myFirstComponentDTO1).isNotEqualTo(myFirstComponentDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(myFirstComponentMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(myFirstComponentMapper.fromId(null)).isNull();
    }
}
