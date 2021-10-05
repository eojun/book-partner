package com.bookpartner.config.db;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = OracleDatabaseProperties.PREFIX)
public class OracleDatabaseProperties extends DatabaseProperties {
	public static final String PREFIX = "datasource.oracle"; 
}