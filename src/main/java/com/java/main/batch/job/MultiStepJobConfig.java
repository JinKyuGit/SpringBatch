package com.java.main.batch.job;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.java.main.batch.mapper.AdminMapper;
import com.java.main.batch.vo.AdminVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableBatchProcessing
public class MultiStepJobConfig {
	
	@Autowired public JobBuilderFactory jobBuilderFactory;
    @Autowired public StepBuilderFactory stepBuilderFactory;
    @Autowired private AdminMapper adminMapper;
    
    
    @Bean
    public Job MultiStepJob() {
    	
    	Job multiStepJob = jobBuilderFactory.get("multiStepJob")
    			.start(firstStep())
    			.next(secondStep())
    			.build();
    	
    	return multiStepJob;    	
    }
    

    @Bean
    public Step firstStep() {
        return stepBuilderFactory.get("firstStep")
                .tasklet((contribution, chunkContext) -> {             	
                	List<AdminVo> list = adminMapper.selectAdminList();
                	log.info("admin list size :::"+list.size());
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
    
    @Bean
    public Step secondStep() {
        return stepBuilderFactory.get("firstStep")
                .tasklet((contribution, chunkContext) -> {             	
                	log.info("second step!!!");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
    
    
    
}
