package ru.otus.coursework.staff.web.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.coursework.staff.repository.JobHistoryRepository;
import ru.otus.coursework.staff.service.JobHistoryService;
import ru.otus.coursework.staff.service.dto.JobHistoryDto;
import ru.otus.coursework.staff.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ru.otus.coursework.staff.domain.JobHistory}.
 */
@RestController
@RequestMapping("/api")
public class JobHistoryResource {

    private final Logger log = LoggerFactory.getLogger(JobHistoryResource.class);

    private static final String ENTITY_NAME = "jobHistory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final JobHistoryService jobHistoryService;

    private final JobHistoryRepository jobHistoryRepository;

    public JobHistoryResource(JobHistoryService jobHistoryService, JobHistoryRepository jobHistoryRepository) {
        this.jobHistoryService = jobHistoryService;
        this.jobHistoryRepository = jobHistoryRepository;
    }

    /**
     * {@code POST  /job-histories} : Create a new jobHistory.
     *
     * @param jobHistoryDto the jobHistoryDto to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new jobHistoryDto, or with status {@code 400 (Bad Request)} if the jobHistory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/job-histories")
    public ResponseEntity<JobHistoryDto> createJobHistory(@Valid @RequestBody JobHistoryDto jobHistoryDto) throws URISyntaxException {
        log.debug("REST request to save JobHistory : {}", jobHistoryDto);
        if (jobHistoryDto.getId() != null) {
            throw new BadRequestAlertException("A new jobHistory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        JobHistoryDto result = jobHistoryService.save(jobHistoryDto);
        return ResponseEntity
            .created(new URI("/api/job-histories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /job-histories/:id} : Updates an existing jobHistory.
     *
     * @param id the id of the jobHistoryDto to save.
     * @param jobHistoryDto the jobHistoryDto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jobHistoryDto,
     * or with status {@code 400 (Bad Request)} if the jobHistoryDto is not valid,
     * or with status {@code 500 (Internal Server Error)} if the jobHistoryDto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/job-histories/{id}")
    public ResponseEntity<JobHistoryDto> updateJobHistory(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody JobHistoryDto jobHistoryDto
    ) throws URISyntaxException {
        log.debug("REST request to update JobHistory : {}, {}", id, jobHistoryDto);
        if (jobHistoryDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, jobHistoryDto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!jobHistoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        JobHistoryDto result = jobHistoryService.update(jobHistoryDto);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, jobHistoryDto.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /job-histories/:id} : Partial updates given fields of an existing jobHistory, field will ignore if it is null
     *
     * @param id the id of the jobHistoryDto to save.
     * @param jobHistoryDto the jobHistoryDto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jobHistoryDto,
     * or with status {@code 400 (Bad Request)} if the jobHistoryDto is not valid,
     * or with status {@code 404 (Not Found)} if the jobHistoryDto is not found,
     * or with status {@code 500 (Internal Server Error)} if the jobHistoryDto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/job-histories/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<JobHistoryDto> partialUpdateJobHistory(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody JobHistoryDto jobHistoryDto
    ) throws URISyntaxException {
        log.debug("REST request to partial update JobHistory partially : {}, {}", id, jobHistoryDto);
        if (jobHistoryDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, jobHistoryDto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!jobHistoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<JobHistoryDto> result = jobHistoryService.partialUpdate(jobHistoryDto);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, jobHistoryDto.getId().toString())
        );
    }

    /**
     * {@code GET  /job-histories} : get all the jobHistories.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of jobHistories in body.
     */
    @GetMapping("/job-histories")
    public List<JobHistoryDto> getAllJobHistories() {
        log.debug("REST request to get all JobHistories");
        return jobHistoryService.findAll();
    }

    /**
     * {@code GET  /job-histories/:id} : get the "id" jobHistory.
     *
     * @param id the id of the jobHistoryDto to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the jobHistoryDto, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/job-histories/{id}")
    public ResponseEntity<JobHistoryDto> getJobHistory(@PathVariable Long id) {
        log.debug("REST request to get JobHistory : {}", id);
        Optional<JobHistoryDto> jobHistoryDto = jobHistoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(jobHistoryDto);
    }

    /**
     * {@code DELETE  /job-histories/:id} : delete the "id" jobHistory.
     *
     * @param id the id of the jobHistoryDto to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/job-histories/{id}")
    public ResponseEntity<Void> deleteJobHistory(@PathVariable Long id) {
        log.debug("REST request to delete JobHistory : {}", id);
        jobHistoryService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
