package ru.otus.coursework.staff.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.coursework.staff.domain.JobHistory;

/**
 * Spring Data JPA repository for the JobHistory entity.
 */
@Repository
public interface JobHistoryRepository extends JpaRepository<JobHistory, Long> {
    default Optional<JobHistory> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<JobHistory> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<JobHistory> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select jobHistory from JobHistory jobHistory left join fetch jobHistory.job left join fetch jobHistory.department left join fetch jobHistory.employee",
        countQuery = "select count(jobHistory) from JobHistory jobHistory"
    )
    Page<JobHistory> findAllWithToOneRelationships(Pageable pageable);

    @Query(
        "select jobHistory from JobHistory jobHistory left join fetch jobHistory.job left join fetch jobHistory.department left join fetch jobHistory.employee"
    )
    List<JobHistory> findAllWithToOneRelationships();

    @Query(
        "select jobHistory from JobHistory jobHistory left join fetch jobHistory.job left join fetch jobHistory.department left join fetch jobHistory.employee where jobHistory.id =:id"
    )
    Optional<JobHistory> findOneWithToOneRelationships(@Param("id") Long id);
}
