package ru.otus.coursework.staff.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import ru.otus.coursework.staff.web.rest.TestUtil;

class JobHistoryDtoTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(JobHistoryDto.class);
        JobHistoryDto jobHistoryDto1 = new JobHistoryDto();
        jobHistoryDto1.setId(1L);
        JobHistoryDto jobHistoryDto2 = new JobHistoryDto();
        assertThat(jobHistoryDto1).isNotEqualTo(jobHistoryDto2);
        jobHistoryDto2.setId(jobHistoryDto1.getId());
        assertThat(jobHistoryDto1).isEqualTo(jobHistoryDto2);
        jobHistoryDto2.setId(2L);
        assertThat(jobHistoryDto1).isNotEqualTo(jobHistoryDto2);
        jobHistoryDto1.setId(null);
        assertThat(jobHistoryDto1).isNotEqualTo(jobHistoryDto2);
    }
}
