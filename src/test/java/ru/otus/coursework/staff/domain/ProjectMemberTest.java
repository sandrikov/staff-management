package ru.otus.coursework.staff.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import ru.otus.coursework.staff.web.rest.TestUtil;

class ProjectMemberTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjectMember.class);
        ProjectMember projectMember1 = new ProjectMember();
        projectMember1.setId(1L);
        ProjectMember projectMember2 = new ProjectMember();
        projectMember2.setId(projectMember1.getId());
        assertThat(projectMember1).isEqualTo(projectMember2);
        projectMember2.setId(2L);
        assertThat(projectMember1).isNotEqualTo(projectMember2);
        projectMember1.setId(null);
        assertThat(projectMember1).isNotEqualTo(projectMember2);
    }
}
