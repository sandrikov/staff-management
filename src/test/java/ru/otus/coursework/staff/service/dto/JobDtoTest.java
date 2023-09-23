package ru.otus.coursework.staff.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import ru.otus.coursework.staff.web.rest.TestUtil;

class JobDtoTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(JobDto.class);
        JobDto jobDto1 = new JobDto();
        jobDto1.setId(1L);
        JobDto jobDto2 = new JobDto();
        assertThat(jobDto1).isNotEqualTo(jobDto2);
        jobDto2.setId(jobDto1.getId());
        assertThat(jobDto1).isEqualTo(jobDto2);
        jobDto2.setId(2L);
        assertThat(jobDto1).isNotEqualTo(jobDto2);
        jobDto1.setId(null);
        assertThat(jobDto1).isNotEqualTo(jobDto2);
    }
}
