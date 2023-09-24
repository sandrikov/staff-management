package ru.otus.coursework.staff.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link ru.otus.coursework.staff.domain.ProjectMember} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProjectMemberDto implements Serializable {

    private Long id;

    @NotNull
    private Instant startDate;

    private Instant endDate;

    private ProjectDto project;

    private RoleDto role;

    private EmployeeDto employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public ProjectDto getProject() {
        return project;
    }

    public void setProject(ProjectDto project) {
        this.project = project;
    }

    public RoleDto getRole() {
        return role;
    }

    public void setRole(RoleDto role) {
        this.role = role;
    }

    public EmployeeDto getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDto employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProjectMemberDto)) {
            return false;
        }

        ProjectMemberDto projectMemberDto = (ProjectMemberDto) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, projectMemberDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProjectMemberDto{" +
            "id=" + getId() +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", project=" + getProject() +
            ", role=" + getRole() +
            ", employee=" + getEmployee() +
            "}";
    }
}
