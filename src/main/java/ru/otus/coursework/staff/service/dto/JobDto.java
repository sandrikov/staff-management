package ru.otus.coursework.staff.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ru.otus.coursework.staff.domain.Job} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class JobDto implements Serializable {

    private Long id;

    @NotNull
    private String jobTitle;

    private EmployeeDto employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
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
        if (!(o instanceof JobDto)) {
            return false;
        }

        JobDto jobDto = (JobDto) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, jobDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "JobDto{" +
            "id=" + getId() +
            ", jobTitle='" + getJobTitle() + "'" +
            ", employee=" + getEmployee() +
            "}";
    }
}
