package ru.otus.coursework.staff.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.coursework.staff.domain.JobHistory;
import ru.otus.coursework.staff.repository.JobHistoryRepository;
import ru.otus.coursework.staff.service.JobHistoryService;
import ru.otus.coursework.staff.service.dto.JobHistoryDto;
import ru.otus.coursework.staff.service.mapper.JobHistoryMapper;

/**
 * Service Implementation for managing {@link ru.otus.coursework.staff.domain.JobHistory}.
 */
@Service
@Transactional
public class JobHistoryServiceImpl implements JobHistoryService {

    private final Logger log = LoggerFactory.getLogger(JobHistoryServiceImpl.class);

    private final JobHistoryRepository jobHistoryRepository;

    private final JobHistoryMapper jobHistoryMapper;

    public JobHistoryServiceImpl(JobHistoryRepository jobHistoryRepository, JobHistoryMapper jobHistoryMapper) {
        this.jobHistoryRepository = jobHistoryRepository;
        this.jobHistoryMapper = jobHistoryMapper;
    }

    @Override
    public JobHistoryDto save(JobHistoryDto jobHistoryDto) {
        log.debug("Request to save JobHistory : {}", jobHistoryDto);
        JobHistory jobHistory = jobHistoryMapper.toEntity(jobHistoryDto);
        jobHistory = jobHistoryRepository.save(jobHistory);
        return jobHistoryMapper.toDto(jobHistory);
    }

    @Override
    public JobHistoryDto update(JobHistoryDto jobHistoryDto) {
        log.debug("Request to update JobHistory : {}", jobHistoryDto);
        JobHistory jobHistory = jobHistoryMapper.toEntity(jobHistoryDto);
        jobHistory = jobHistoryRepository.save(jobHistory);
        return jobHistoryMapper.toDto(jobHistory);
    }

    @Override
    public Optional<JobHistoryDto> partialUpdate(JobHistoryDto jobHistoryDto) {
        log.debug("Request to partially update JobHistory : {}", jobHistoryDto);

        return jobHistoryRepository
            .findById(jobHistoryDto.getId())
            .map(existingJobHistory -> {
                jobHistoryMapper.partialUpdate(existingJobHistory, jobHistoryDto);

                return existingJobHistory;
            })
            .map(jobHistoryRepository::save)
            .map(jobHistoryMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<JobHistoryDto> findAll() {
        log.debug("Request to get all JobHistories");
        return jobHistoryRepository.findAll().stream().map(jobHistoryMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    public Page<JobHistoryDto> findAllWithEagerRelationships(Pageable pageable) {
        return jobHistoryRepository.findAllWithEagerRelationships(pageable).map(jobHistoryMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<JobHistoryDto> findOne(Long id) {
        log.debug("Request to get JobHistory : {}", id);
        return jobHistoryRepository.findOneWithEagerRelationships(id).map(jobHistoryMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete JobHistory : {}", id);
        jobHistoryRepository.deleteById(id);
    }
}
