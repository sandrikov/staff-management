package ru.otus.coursework.staff.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.otus.coursework.staff.service.dto.EmployeeDto;

/**
 * Service Interface for managing {@link ru.otus.coursework.staff.domain.Employee}.
 */
public interface EmployeeService {
    /**
     * Save a employee.
     *
     * @param employeeDto the entity to save.
     * @return the persisted entity.
     */
    EmployeeDto save(EmployeeDto employeeDto);

    /**
     * Updates a employee.
     *
     * @param employeeDto the entity to update.
     * @return the persisted entity.
     */
    EmployeeDto update(EmployeeDto employeeDto);

    /**
     * Partially updates a employee.
     *
     * @param employeeDto the entity to update partially.
     * @return the persisted entity.
     */
    Optional<EmployeeDto> partialUpdate(EmployeeDto employeeDto);

    /**
     * Get all the employees.
     *
     * @return the list of entities.
     */
    List<EmployeeDto> findAll();

    /**
     * Get all the EmployeeDto where JobHistory is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<EmployeeDto> findAllWhereJobHistoryIsNull();
    /**
     * Get all the EmployeeDto where ProjectMember is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<EmployeeDto> findAllWhereProjectMemberIsNull();

    /**
     * Get all the employees with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EmployeeDto> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" employee.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EmployeeDto> findOne(Long id);

    /**
     * Delete the "id" employee.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
