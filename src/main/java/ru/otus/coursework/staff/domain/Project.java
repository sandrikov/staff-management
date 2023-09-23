package ru.otus.coursework.staff.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import ru.otus.coursework.staff.domain.enumeration.ProjectStatus;

/**
 * A Project.
 */
@Entity
@Table(name = "project")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "project_name", nullable = false)
    private String projectName;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ProjectStatus status;

    @JsonIgnoreProperties(value = { "project", "role", "employee" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "project")
    private ProjectMember projectMember;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Project id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public Project projectName(String projectName) {
        this.setProjectName(projectName);
        return this;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public ProjectStatus getStatus() {
        return this.status;
    }

    public Project status(ProjectStatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public ProjectMember getProjectMember() {
        return this.projectMember;
    }

    public void setProjectMember(ProjectMember projectMember) {
        if (this.projectMember != null) {
            this.projectMember.setProject(null);
        }
        if (projectMember != null) {
            projectMember.setProject(this);
        }
        this.projectMember = projectMember;
    }

    public Project projectMember(ProjectMember projectMember) {
        this.setProjectMember(projectMember);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Project)) {
            return false;
        }
        return id != null && id.equals(((Project) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Project{" +
            "id=" + getId() +
            ", projectName='" + getProjectName() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
