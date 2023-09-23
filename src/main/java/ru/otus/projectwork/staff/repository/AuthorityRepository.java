package ru.otus.projectwork.staff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.projectwork.staff.domain.Authority;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {}
