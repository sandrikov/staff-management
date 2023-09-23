package ru.otus.coursework.staff.service;

import java.util.List;
import java.util.Optional;
import ru.otus.coursework.staff.service.dto.DepartmentDto;

/**
 * Service Interface for managing {@link ru.otus.coursework.staff.domain.Department}.
 */
public interface DepartmentService {
    /**
     * Save a department.
     *
     * @param departmentDto the entity to save.
     * @return the persisted entity.
     */
    DepartmentDto save(DepartmentDto departmentDto);

    /**
     * Updates a department.
     *
     * @param departmentDto the entity to update.
     * @return the persisted entity.
     */
    DepartmentDto update(DepartmentDto departmentDto);

    /**
     * Partially updates a department.
     *
     * @param departmentDto the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DepartmentDto> partialUpdate(DepartmentDto departmentDto);

    /**
     * Get all the departments.
     *
     * @return the list of entities.
     */
    List<DepartmentDto> findAll();

    /**
     * Get all the DepartmentDto where JobHistory is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<DepartmentDto> findAllWhereJobHistoryIsNull();

    /**
     * Get the "id" department.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DepartmentDto> findOne(Long id);

    /**
     * Delete the "id" department.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
