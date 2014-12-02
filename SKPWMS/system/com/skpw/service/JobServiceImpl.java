package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.skpw.bean.TSysjob;
import com.skpw.repository.JobRepository;
@Service("jobService")
public class JobServiceImpl implements JobService {
	@Resource
	private JobRepository jobRepository;

	public List<TSysjob> showJobInfo() {

		return jobRepository.findAll();
	}

	public Page<TSysjob> showJobListByCondition(Specification<TSysjob> spec,
			Pageable pager) {

		return jobRepository.findAll(spec, pager);
	}

	public void saveJob(TSysjob job) {

		jobRepository.save(job);
	}

	public void deleteJob(String jobid) {

		jobRepository.delete(jobid);
	}

	public void updateJob(TSysjob job) {

		jobRepository.save(job);
	}

	public TSysjob initUpdateJob(String jobid) {

		return jobRepository.findOne(jobid);
	}

	public long count() {

		return jobRepository.count();
	}

}
