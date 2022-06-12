package com.java.main.common;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:/application.properties")
@MapperScan(basePackages="com.java.main.batch.mapper")
public class DataSourceConfig {

	
	 @Bean
	 @ConfigurationProperties(prefix = "spring.datasource.hikari") 
	 public HikariConfig hikariConfig() { 
		 return new HikariConfig(); 
	}
	 
	  @Bean 
	  public DataSource dataSource() { 
		  return new HikariDataSource(hikariConfig());
      }
	 
	  /*
	  @Bean 
	  public DataSource dataSource() { 
		  return DataSourceBuilder.create().build();
      }
      */
	  
	@Bean 
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception { 
	
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean(); 
		sqlSessionFactory.setDataSource(dataSource); 
		sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/**/*.xml")); 
		return sqlSessionFactory.getObject(); 
	}
	
	@Bean 
	public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) { 
		return new SqlSessionTemplate(sqlSessionFactory); 
	}

}
