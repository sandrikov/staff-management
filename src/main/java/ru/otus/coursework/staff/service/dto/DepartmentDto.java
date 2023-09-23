package ru.otus.coursework.staff.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ru.otus.coursework.staff.domain.Department} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DepartmentDto implements Serializable {

    private Long id;

    @NotNull
    private String departmentName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DepartmentDto)) {
            return false;
        }

        DepartmentDto departmentDto = (DepartmentDto) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, departmentDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DepartmentDto{" +
            "id=" + getId() +
            ", departmentName='" + getDepartmentName() + "'" +
            "}";
    }
}
