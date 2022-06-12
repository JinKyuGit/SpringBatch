package com.java.main.batch.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.java.main.batch.job.ReaderWriterJobConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class JobController {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    ReaderWriterJobConfig job;
    
    
    @RequestMapping("/CallReaderWriterJob")
    public void handle() throws Exception{
    	log.info("hadle job ::: ReaderWriterJob");
		Map<String, JobParameter> confMap = new HashMap<>();
		confMap.put("param1", new JobParameter(System.currentTimeMillis()));
        jobLauncher.run(job.ReaderWriterJob(), new JobParameters(confMap));
    }
	
}
