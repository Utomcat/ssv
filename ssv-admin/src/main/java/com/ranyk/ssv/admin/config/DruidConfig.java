package com.ranyk.ssv.admin.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Title: DruidConfig.java
 * 
 * @author: ranyk
 * @Description: druid数据源配置类
 * @date：2019年12月16日 下午4:23:24
 * @version 1.0
 */
@Configuration
@SuppressWarnings("all")
@EnableConfigurationProperties({ DruidDataSourceProperties.class })
public class DruidConfig {

	/**
	 *创建数据源配置参数类对象 通过此类进行数据源的参数读取
	 */
	@Autowired
	private DruidDataSourceProperties druidDataSourceProperties;

	/**
	 * 创建Druid数据库连接池的数据源对象，同时初始化数据源
	 * @return 返回数据库连接池的数据源对象
	 */
	@Bean
	@ConditionalOnMissingBean
	public DataSource druidDataSource() {
		//声明数据源(数据库连接)对象
		DruidDataSource druidDataSource = new DruidDataSource();

		//为数据源(数据库丽连接)对象设置参数

		//设置数据库连接的驱动
		druidDataSource.setDriverClassName(druidDataSourceProperties.getDriverClassName());
		//设置数据库连接的的URL
		druidDataSource.setUrl(druidDataSourceProperties.getUrl());
		//设置数据库连接的用户名
		druidDataSource.setUsername(druidDataSourceProperties.getUsername());
		//设置数据库连接的密码
		druidDataSource.setPassword(druidDataSourceProperties.getPassword());
		//设置数据库连接池中初始化的连接数量
		druidDataSource.setInitialSize(druidDataSourceProperties.getInitialSize());
		//设置数据库连接池中最小的连接数量
		druidDataSource.setMinIdle(druidDataSourceProperties.getMinIdle());
		//设置数据库连接池中最大的连接数量
		druidDataSource.setMaxActive(druidDataSourceProperties.getMaxActive());
		//获取到连接时需要等待的最长时间
		druidDataSource.setMaxWait(druidDataSourceProperties.getMaxWait());
		//设置连接有效检测的时间间隔，单位为毫秒  ==> 即连接上数据库后，经过多少毫秒后进行检测此连接是否还是有效连接
		druidDataSource.setTimeBetweenEvictionRunsMillis(druidDataSourceProperties.getTimeBetweenEvictionRunsMillis());
		//设置Destory线程是否需要关闭连接的时间间隔，单位为毫秒  ==> 检测到当前连接的最后活跃时间和当前时间的差值大于minEvictableIdleTimeMillis的值时，关闭当前连接
		druidDataSource.setMinEvictableIdleTimeMillis(druidDataSourceProperties.getMinEvictableIdleTimeMillis());
		//设置检测连接是否为有效的SQL，其值要求是一个查询语句。  ==> 不配置的其值为null，则testOnBorrow、testOnReturn、testWhileIdle都不会其作用  在mysql中通常为 select 'x'，在oracle中通常为 select 1 from dual
		druidDataSource.setValidationQuery(druidDataSourceProperties.getValidationQuery());
		//设置在检测连接申请时需不需要检测连接是否有效  ==> 在申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
		druidDataSource.setTestWhileIdle(druidDataSourceProperties.isTestWhileIdle());
		//设置在申请连接时需不需要执行validationQuery检测连接是否有效，做了这个配置会降低性能。
		druidDataSource.setTestOnBorrow(druidDataSourceProperties.isTestOnBorrow());
		//设置归还连接时需不需要执行validationQuery检测连接是否有效，做了这个配置会降低性能
		druidDataSource.setTestOnReturn(druidDataSourceProperties.isTestOnReturn());
		//设置是否缓存preparedStatement，也就是PSCache。PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql5.5以下的版本中没有PSCache功能，建议关闭掉。5.5及以上版本有PSCache，建议开启。
		druidDataSource.setPoolPreparedStatements(druidDataSourceProperties.isPoolPreparedStatements());
		//要启用PSCache，必须配置大于0，当大于0时，poolPreparedStatements自动触发修改为true。在Druid中，不会存在Oracle下PSCache占用内存过多的问题，可以把这个数值配置大一些，比如说100
		druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(druidDataSourceProperties.getMaxPoolPreparedStatementPerConnectionSize());
		try {
			//属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：监控统计用的filter:stat 、 日志用的filter:log4j 、 防御sql注入的filter:wall
			druidDataSource.setFilters(druidDataSourceProperties.getFilters());
			//初始化数据库连接池的数据源对象
			druidDataSource.init();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//返回数据库连接池的数据源对象
		return druidDataSource;
	}

	/**
	 * 注册Servlet信息，配置监控视图 ==> 配置Druid后台监控系统的Servlet相关信息
	 * 
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public ServletRegistrationBean<Servlet> druidServlet() {

		//创建Servlet注册对象
		ServletRegistrationBean<Servlet> servletRegistrationBean = new ServletRegistrationBean<Servlet>(new StatViewServlet(), "/druid/*");

		// 配置IP白名单
		servletRegistrationBean.addInitParameter("allow", "127.0.0.1,139.196.87.48");

		// 配置IP黑名单
		// 若黑名单和白名单中同时存在同一个IP地址时，deny(黑名单)优先于allow(白名单)，满足deny(黑名单)的话，则提示Sorry，you are not permitted(允许) to view this page.
		servletRegistrationBean.addInitParameter("deny", "192.168.1.119");

		// 配置登录查看新歘的扎根囊啊哦和密码，用于登录Druid监控后台
		servletRegistrationBean.addInitParameter("loginUsername", "admin");
		servletRegistrationBean.addInitParameter("loginPassword", "admin");

		// 配置是否能够重置数据
		servletRegistrationBean.addInitParameter("resetEnable", "true");

		return servletRegistrationBean;
	}

	/**
	 * 注册Filter信息，监控拦截器  ==> 配置Druid拦截器的相关信息
	 * 
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public FilterRegistrationBean<Filter> filterRegistrationBean() {

		//创建拦截器注册Bean对象
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>();
		//设置过滤器进行注册  ==> 注册网络统计过滤器
		filterRegistrationBean.setFilter(new WebStatFilter());
		//根据Servlet规范中的定义添加URL模式，过滤器将根据该URL模式进行注册。
		filterRegistrationBean.addUrlPatterns("/*");
		//添加一个初始化参数，将所有现有参数替换为相同的名称。
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");

		return filterRegistrationBean;
	}
}
