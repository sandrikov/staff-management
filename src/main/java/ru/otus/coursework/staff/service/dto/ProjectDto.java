package ru.otus.coursework.staff.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import ru.otus.coursework.staff.domain.enumeration.ProjectStatus;

/**
 * A DTO for the {@link ru.otus.coursework.staff.domain.Project} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProjectDto implements Serializable {

    private Long id;

    @NotNull
    private String projectName;

    @NotNull
    private ProjectStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProjectDto)) {
            return false;
        }

        ProjectDto projectDto = (ProjectDto) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, projectDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProjectDto{" +
            "id=" + getId() +
            ", projectName='" + getProjectName() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
