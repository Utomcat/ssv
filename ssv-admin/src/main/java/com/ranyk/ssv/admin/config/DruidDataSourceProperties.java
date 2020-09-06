package com.ranyk.ssv.admin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Title: DruidDataSourceProperties.java
 * 
 * @author: ranyk
 * @Description: druid数据源配置参数实体类
 * @date：2019年12月16日 下午4:15:19
 * @version 1.0
 */
@Data
@ConfigurationProperties(prefix = "spring.datasource.druid")
public class DruidDataSourceProperties {

	/**
	 * jdbc配置参数
	 */
	private String driverClassName;
	private String url;
	private String username;
	private String password;

	/**
	 * 连接池的配置参数
	 */
	private int initialSize;
	private int minIdle;
	private int maxActive = 100;
	private long maxWait;
	private long timeBetweenEvictionRunsMillis;
	private long minEvictableIdleTimeMillis;
	private String validationQuery;
	private boolean testWhileIdle;
	private boolean testOnBorrow;
	private boolean testOnReturn;
	private boolean poolPreparedStatements;
	private int maxPoolPreparedStatementPerConnectionSize;

	/**
	 * filters配置参数
	 */
	private String filters;
}
