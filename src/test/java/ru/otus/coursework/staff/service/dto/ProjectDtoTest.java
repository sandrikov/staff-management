package ru.otus.coursework.staff.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import ru.otus.coursework.staff.web.rest.TestUtil;

class ProjectDtoTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjectDto.class);
        ProjectDto projectDto1 = new ProjectDto();
        projectDto1.setId(1L);
        ProjectDto projectDto2 = new ProjectDto();
        assertThat(projectDto1).isNotEqualTo(projectDto2);
        projectDto2.setId(projectDto1.getId());
        assertThat(projectDto1).isEqualTo(projectDto2);
        projectDto2.setId(2L);
        assertThat(projectDto1).isNotEqualTo(projectDto2);
        projectDto1.setId(null);
        assertThat(projectDto1).isNotEqualTo(projectDto2);
    }
}
