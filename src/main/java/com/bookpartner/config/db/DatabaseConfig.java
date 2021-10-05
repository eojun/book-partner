/**
 * 
 */
package com.bookpartner.config.db;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


public abstract class DatabaseConfig {

	private static final Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);

	@Bean
	public abstract DataSource dataSource();

	protected void configureDataSource(DataSource dataSource, DatabaseProperties databaseProperties) {
		dataSource.setDriverClassName(databaseProperties.getDriverClassName());
		dataSource.setUrl(databaseProperties.getUrl());
		dataSource.setUsername(databaseProperties.getUserName());
		dataSource.setPassword(databaseProperties.getPassword());
		dataSource.setInitialSize(databaseProperties.getInitialSize());
		dataSource.setMaxActive(databaseProperties.getMaxActive());
		dataSource.setMaxIdle(databaseProperties.getMaxIdle());
		dataSource.setMinIdle(databaseProperties.getMinIdle());
		dataSource.setMaxWait(databaseProperties.getMaxWait());
		dataSource.setValidationQuery(databaseProperties.getValidationQuery());
		dataSource.setValidationInterval(databaseProperties.getValidationInterval());
		dataSource.setTestOnBorrow(databaseProperties.isTestOnBorrow());
		dataSource.setTestWhileIdle(databaseProperties.isTestWhileIdle());
		logger.info("configureDataSource = {}", databaseProperties);
		logger.info("dataSource = {}", dataSource);
	}
}

@Configuration
@EnableTransactionManagement
@EnableConfigurationProperties(com.bookpartner.config.db.OracleDatabaseProperties.class)
class OracleDatabaseConfig extends DatabaseConfig {

	@Autowired
	private OracleDatabaseProperties oracleDatabaseProperties;

	@Primary
	@Bean(name = "oracleDataSource", destroyMethod = "close")
	public DataSource dataSource() {
		DataSource oracleDataSource = new DataSource();
		configureDataSource(oracleDataSource, oracleDatabaseProperties);
		return oracleDataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager(@Qualifier("oracleDataSource") DataSource oracleDataSource) {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(oracleDataSource);
		transactionManager.setGlobalRollbackOnParticipationFailure(false);
		return transactionManager;
	}
}