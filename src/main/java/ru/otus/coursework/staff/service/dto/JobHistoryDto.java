package ru.otus.coursework.staff.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link ru.otus.coursework.staff.domain.JobHistory} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class JobHistoryDto implements Serializable {

    private Long id;

    @NotNull
    private Instant startDate;

    private Instant endDate;

    private JobDto job;

    private DepartmentDto department;

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

    public JobDto getJob() {
        return job;
    }

    public void setJob(JobDto job) {
        this.job = job;
    }

    public DepartmentDto getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDto department) {
        this.department = department;
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
        if (!(o instanceof JobHistoryDto)) {
            return false;
        }

        JobHistoryDto jobHistoryDto = (JobHistoryDto) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, jobHistoryDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "JobHistoryDto{" +
            "id=" + getId() +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", job=" + getJob() +
            ", department=" + getDepartment() +
            ", employee=" + getEmployee() +
            "}";
    }
}
