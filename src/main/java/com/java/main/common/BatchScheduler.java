package com.java.main.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.java.main.batch.job.ExampleJobConfig;
import com.java.main.batch.job.MultiStepJobConfig;
import com.java.main.batch.job.ReaderWriterJobConfig;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class BatchScheduler {
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private ExampleJobConfig exampleJobConfig;
	
	@Autowired
	private MultiStepJobConfig multiStepJobConfig;
	
	@Autowired
	private ReaderWriterJobConfig readerWriterJobConfig;
	
	@Scheduled(cron = "30 * * * * *")
	public void runJob() {
		
		Map<String, JobParameter> confMap = new HashMap<>();
		confMap.put("param1", new JobParameter(System.currentTimeMillis()));
		JobParameters jobParamaters = new JobParameters(confMap);

		try {
			jobLauncher.run(exampleJobConfig.ExampleJob(), jobParamaters);
		} catch (Exception e) {
		    log.error("error :::"+e.getMessage());
		}
	}
	
	@Scheduled(cron = "45 * * * * *")
	public void runJob2() {
		
		Map<String, JobParameter> confMap = new HashMap<>();
		confMap.put("param1", new JobParameter(System.currentTimeMillis()));
		JobParameters jobParamaters = new JobParameters(confMap);

		try {
			jobLauncher.run(multiStepJobConfig.MultiStepJob(), jobParamaters);
		} catch (Exception e) {
			 log.error("error :::"+e.getMessage());
		}
	}
	
	@Scheduled(cron = "55 * * * * *")
	public void runJob3() {
		
		Map<String, JobParameter> confMap = new HashMap<>();
		confMap.put("param1", new JobParameter(System.currentTimeMillis()));
		JobParameters jobParamaters = new JobParameters(confMap);

		try {
			jobLauncher.run(readerWriterJobConfig.ReaderWriterJob(), jobParamaters);
		} catch (Exception e) {
			 log.error("error :::"+e.getMessage());
		}
	}
	
}
