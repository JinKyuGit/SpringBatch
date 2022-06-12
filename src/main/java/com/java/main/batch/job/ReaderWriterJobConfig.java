package com.java.main.batch.job;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.java.main.batch.vo.AdminVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableBatchProcessing
public class ReaderWriterJobConfig {

	@Autowired public JobBuilderFactory jobBuilderFactory;
    @Autowired public StepBuilderFactory stepBuilderFactory;
    
    @Autowired
    private DataSource dataSource;
    private static final int chunkSize = 1000;
    private int count = 1;
    
    @Bean
    public Job ReaderWriterJob() {
    	
    	Job readerWriterJob = jobBuilderFactory.get("readerWriterJob")
    			.start(ReaderWirterStep())
    			.build();
    	
    	return readerWriterJob;    	
    }
    
    @Bean
    public Step ReaderWirterStep() {
        return stepBuilderFactory.get("readerWirterStep")            
                .<AdminVo, AdminVo>chunk(chunkSize)
                .reader(jdbcCursorItemReader())
                .writer(jdbcCursorItemWriter())
                .build();
    }
    
    @Bean
    public JdbcCursorItemReader<AdminVo> jdbcCursorItemReader(){
    	return new JdbcCursorItemReaderBuilder<AdminVo>() 
    			    .fetchSize(chunkSize)
    	            .dataSource(dataSource)
    	            .rowMapper(new BeanPropertyRowMapper<>(AdminVo.class))
    	            .sql("SELECT id, password, name, use_yn FROM ADMIN")
    	            .name("jdbcCursorItemReader")
    	            .build();	
    }
    
    private ItemWriter<AdminVo> jdbcCursorItemWriter(){
    	return list -> {
   
    		for(AdminVo loop : list) {
    			
    			
    			log.info("data count : "+count);
    			log.info("data : "+loop.getId());
    			log.info("data : "+loop.getPassword());
    			log.info("data : "+loop.getName());
    			log.info("data : "+loop.getUseYn());
    			log.info("===================");
    			this.count++;
    		}
    		
    		this.count = 1;
    	};
    }
}
