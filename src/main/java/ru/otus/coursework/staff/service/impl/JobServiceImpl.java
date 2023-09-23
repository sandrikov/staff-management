package ru.otus.coursework.staff.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.coursework.staff.domain.Job;
import ru.otus.coursework.staff.repository.JobRepository;
import ru.otus.coursework.staff.service.JobService;
import ru.otus.coursework.staff.service.dto.JobDto;
import ru.otus.coursework.staff.service.mapper.JobMapper;

/**
 * Service Implementation for managing {@link ru.otus.coursework.staff.domain.Job}.
 */
@Service
@Transactional
public class JobServiceImpl implements JobService {

    private final Logger log = LoggerFactory.getLogger(JobServiceImpl.class);

    private final JobRepository jobRepository;

    private final JobMapper jobMapper;

    public JobServiceImpl(JobRepository jobRepository, JobMapper jobMapper) {
        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
    }

    @Override
    public JobDto save(JobDto jobDto) {
        log.debug("Request to save Job : {}", jobDto);
        Job job = jobMapper.toEntity(jobDto);
        job = jobRepository.save(job);
        return jobMapper.toDto(job);
    }

    @Override
    public JobDto update(JobDto jobDto) {
        log.debug("Request to update Job : {}", jobDto);
        Job job = jobMapper.toEntity(jobDto);
        job = jobRepository.save(job);
        return jobMapper.toDto(job);
    }

    @Override
    public Optional<JobDto> partialUpdate(JobDto jobDto) {
        log.debug("Request to partially update Job : {}", jobDto);

        return jobRepository
            .findById(jobDto.getId())
            .map(existingJob -> {
                jobMapper.partialUpdate(existingJob, jobDto);

                return existingJob;
            })
            .map(jobRepository::save)
            .map(jobMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<JobDto> findAll() {
        log.debug("Request to get all Jobs");
        return jobRepository.findAll().stream().map(jobMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get all the jobs where JobHistory is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<JobDto> findAllWhereJobHistoryIsNull() {
        log.debug("Request to get all jobs where JobHistory is null");
        return StreamSupport
            .stream(jobRepository.findAll().spliterator(), false)
            .filter(job -> job.getJobHistory() == null)
            .map(jobMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<JobDto> findOne(Long id) {
        log.debug("Request to get Job : {}", id);
        return jobRepository.findById(id).map(jobMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Job : {}", id);
        jobRepository.deleteById(id);
    }
}
