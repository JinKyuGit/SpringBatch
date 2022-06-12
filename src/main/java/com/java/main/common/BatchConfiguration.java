package com.java.main.common;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BatchConfiguration extends DefaultBatchConfigurer  {

	
	   //스프링 배치 메타테이블을 사용하지 않기 위함.
	   @Override
	   public void setDataSource(DataSource dataSource) {
	        // 여기를 비워놓는다
	   }
}
