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
import ru.otus.coursework.staff.repository.JobRepository;
import ru.otus.coursework.staff.service.JobService;
import ru.otus.coursework.staff.service.dto.JobDto;
import ru.otus.coursework.staff.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ru.otus.coursework.staff.domain.Job}.
 */
@RestController
@RequestMapping("/api")
public class JobResource {

    private final Logger log = LoggerFactory.getLogger(JobResource.class);

    private static final String ENTITY_NAME = "job";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final JobService jobService;

    private final JobRepository jobRepository;

    public JobResource(JobService jobService, JobRepository jobRepository) {
        this.jobService = jobService;
        this.jobRepository = jobRepository;
    }

    /**
     * {@code POST  /jobs} : Create a new job.
     *
     * @param jobDto the jobDto to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new jobDto, or with status {@code 400 (Bad Request)} if the job has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/jobs")
    public ResponseEntity<JobDto> createJob(@Valid @RequestBody JobDto jobDto) throws URISyntaxException {
        log.debug("REST request to save Job : {}", jobDto);
        if (jobDto.getId() != null) {
            throw new BadRequestAlertException("A new job cannot already have an ID", ENTITY_NAME, "idexists");
        }
        JobDto result = jobService.save(jobDto);
        return ResponseEntity
            .created(new URI("/api/jobs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /jobs/:id} : Updates an existing job.
     *
     * @param id the id of the jobDto to save.
     * @param jobDto the jobDto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jobDto,
     * or with status {@code 400 (Bad Request)} if the jobDto is not valid,
     * or with status {@code 500 (Internal Server Error)} if the jobDto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/jobs/{id}")
    public ResponseEntity<JobDto> updateJob(@PathVariable(value = "id", required = false) final Long id, @Valid @RequestBody JobDto jobDto)
        throws URISyntaxException {
        log.debug("REST request to update Job : {}, {}", id, jobDto);
        if (jobDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, jobDto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!jobRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        JobDto result = jobService.update(jobDto);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, jobDto.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /jobs/:id} : Partial updates given fields of an existing job, field will ignore if it is null
     *
     * @param id the id of the jobDto to save.
     * @param jobDto the jobDto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jobDto,
     * or with status {@code 400 (Bad Request)} if the jobDto is not valid,
     * or with status {@code 404 (Not Found)} if the jobDto is not found,
     * or with status {@code 500 (Internal Server Error)} if the jobDto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/jobs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<JobDto> partialUpdateJob(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody JobDto jobDto
    ) throws URISyntaxException {
        log.debug("REST request to partial update Job partially : {}, {}", id, jobDto);
        if (jobDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, jobDto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!jobRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<JobDto> result = jobService.partialUpdate(jobDto);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, jobDto.getId().toString())
        );
    }

    /**
     * {@code GET  /jobs} : get all the jobs.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of jobs in body.
     */
    @GetMapping("/jobs")
    public List<JobDto> getAllJobs(@RequestParam(required = false) String filter) {
        if ("jobhistory-is-null".equals(filter)) {
            log.debug("REST request to get all Jobs where jobHistory is null");
            return jobService.findAllWhereJobHistoryIsNull();
        }
        log.debug("REST request to get all Jobs");
        return jobService.findAll();
    }

    /**
     * {@code GET  /jobs/:id} : get the "id" job.
     *
     * @param id the id of the jobDto to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the jobDto, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/jobs/{id}")
    public ResponseEntity<JobDto> getJob(@PathVariable Long id) {
        log.debug("REST request to get Job : {}", id);
        Optional<JobDto> jobDto = jobService.findOne(id);
        return ResponseUtil.wrapOrNotFound(jobDto);
    }

    /**
     * {@code DELETE  /jobs/:id} : delete the "id" job.
     *
     * @param id the id of the jobDto to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        log.debug("REST request to delete Job : {}", id);
        jobService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
