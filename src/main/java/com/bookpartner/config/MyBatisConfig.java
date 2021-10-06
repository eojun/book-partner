/**
 * 
 */
package com.bookpartner.config;

import com.bookpartner.config.annotation.Oracle;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

public abstract class MyBatisConfig {
	
	public static final String BASE_PACKAGE = "com.bookpartner.mappers";
	public static final String ORAGLE_PACKAGE = BASE_PACKAGE+".oracle";
	public static final String TYPE_ALIASES_PACKAGE = "com.bookpartner.web.dto";
	public static final String MAPPER_LOCATIONS_PATH = "classpath:mappers/**/*.xml";
	
	protected void configureSqlSessionFactory(SqlSessionFactoryBean sessionFactoryBean, DataSource dataSource) throws IOException {
		sessionFactoryBean.setDataSource(dataSource);
		sessionFactoryBean.setTypeAliasesPackage(TYPE_ALIASES_PACKAGE);
	}
}

@Configuration
@MapperScan(basePackages = MyBatisConfig.ORAGLE_PACKAGE, annotationClass = Oracle.class, sqlSessionFactoryRef = "oracleSqlSessionFactory")
class OracleMyBatisConfig extends MyBatisConfig {

	@Bean
	public SqlSessionFactory oracleSqlSessionFactory(@Qualifier("oracleDataSource") DataSource oracleDataSource) throws Exception {
		
		PathMatchingResourcePatternResolver pathResolver = new PathMatchingResourcePatternResolver();
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		
		configureSqlSessionFactory(sessionFactoryBean, oracleDataSource);
		sessionFactoryBean.setMapperLocations(pathResolver.getResources("classpath:mappers/oracle/*.xml"));
		
		return sessionFactoryBean.getObject();
	}
	
	@Bean 
	public SqlSessionTemplate oracleSqlSessionTemplate(SqlSessionFactory oracleSqlSessionFactory) throws Exception {
		
		
		return new SqlSessionTemplate(oracleSqlSessionFactory); 
	}	
}

