package ru.otus.coursework.staff.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import ru.otus.coursework.staff.domain.JobHistory;

/**
 * Spring Data JPA repository for the JobHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JobHistoryRepository extends JpaRepository<JobHistory, Long> {}
