package ru.otus.coursework.staff.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.otus.coursework.staff.service.dto.JobHistoryDto;

/**
 * Service Interface for managing {@link ru.otus.coursework.staff.domain.JobHistory}.
 */
public interface JobHistoryService {
    /**
     * Save a jobHistory.
     *
     * @param jobHistoryDto the entity to save.
     * @return the persisted entity.
     */
    JobHistoryDto save(JobHistoryDto jobHistoryDto);

    /**
     * Updates a jobHistory.
     *
     * @param jobHistoryDto the entity to update.
     * @return the persisted entity.
     */
    JobHistoryDto update(JobHistoryDto jobHistoryDto);

    /**
     * Partially updates a jobHistory.
     *
     * @param jobHistoryDto the entity to update partially.
     * @return the persisted entity.
     */
    Optional<JobHistoryDto> partialUpdate(JobHistoryDto jobHistoryDto);

    /**
     * Get all the jobHistories.
     *
     * @return the list of entities.
     */
    List<JobHistoryDto> findAll();

    /**
     * Get all the jobHistories with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<JobHistoryDto> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" jobHistory.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<JobHistoryDto> findOne(Long id);

    /**
     * Delete the "id" jobHistory.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
