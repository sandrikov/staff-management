package ru.otus.coursework.staff.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import ru.otus.coursework.staff.web.rest.TestUtil;

class ProjectMemberDtoTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjectMemberDto.class);
        ProjectMemberDto projectMemberDto1 = new ProjectMemberDto();
        projectMemberDto1.setId(1L);
        ProjectMemberDto projectMemberDto2 = new ProjectMemberDto();
        assertThat(projectMemberDto1).isNotEqualTo(projectMemberDto2);
        projectMemberDto2.setId(projectMemberDto1.getId());
        assertThat(projectMemberDto1).isEqualTo(projectMemberDto2);
        projectMemberDto2.setId(2L);
        assertThat(projectMemberDto1).isNotEqualTo(projectMemberDto2);
        projectMemberDto1.setId(null);
        assertThat(projectMemberDto1).isNotEqualTo(projectMemberDto2);
    }
}
