package ru.otus.coursework.staff.service;

import java.util.List;
import java.util.Optional;
import ru.otus.coursework.staff.service.dto.JobDto;

/**
 * Service Interface for managing {@link ru.otus.coursework.staff.domain.Job}.
 */
public interface JobService {
    /**
     * Save a job.
     *
     * @param jobDto the entity to save.
     * @return the persisted entity.
     */
    JobDto save(JobDto jobDto);

    /**
     * Updates a job.
     *
     * @param jobDto the entity to update.
     * @return the persisted entity.
     */
    JobDto update(JobDto jobDto);

    /**
     * Partially updates a job.
     *
     * @param jobDto the entity to update partially.
     * @return the persisted entity.
     */
    Optional<JobDto> partialUpdate(JobDto jobDto);

    /**
     * Get all the jobs.
     *
     * @return the list of entities.
     */
    List<JobDto> findAll();

    /**
     * Get all the JobDto where JobHistory is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<JobDto> findAllWhereJobHistoryIsNull();

    /**
     * Get the "id" job.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<JobDto> findOne(Long id);

    /**
     * Delete the "id" job.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
