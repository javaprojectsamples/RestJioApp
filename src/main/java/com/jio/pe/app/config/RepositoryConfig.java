package com.jio.pe.app.config;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class RepositoryConfig {

	
	/*
	@Value("${jdbc.driverClassName}")
	private String driverClassName;
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;

	@Value("${hibernate.dialect}")
	private String hibernateDialect;
	@Value("${hibernate.show_sql}")
	private String hibernateShowSql;
	@Value("${hibernate.hbm2ddl.auto}")
	private String hibernateHbm2ddlAuto;
	*/
	
	// Ehcache
	/*
	@Value("${hibernate.cache.provider_class}")
	private String hibernateCacheProviderClass;
	@Value("${hibernate.cache.use_second_level_cache}")
	private String hibernateCacheUseSecondLevelCache;
	@Value("${hibernate.cache.region.factory_class}")
	private String hibernateCacheRegionFactoryClass;
	*/
	@Value("${net.sf.ehcache.configurationResourceName}")
	private String ehcacheConfigurationResourceName;
	
	// C3P0 properties
	/*
	@Value("${min.pool.size}")
	private int minPoolSize;
	@Value("${max.pool.size}")
	private int maxPoolSize;
	@Value("${timeout}")
	private int timeout;
	@Value("${max.statements}")
	private int maxStatements;
	@Value("${idle.test.period}")
	private int idleConnectionTestPeriod;
	@Value("${acquire.increment}")
	private int acquireIncrement;
	*/
	@Value("${hibernate.properties.location}")
	private String hibernatePropertiesLocation;
	@Value("${datbase.properties.location}")
	private String datbasePropertiesLocation;

	@Value("${hibernate.entity.package.scan}")
	private String hibernateEntityPackageScan;

	@Bean()
	public DataSource getDataSource() {

		com.mchange.v2.c3p0.ComboPooledDataSource dataSource = new com.mchange.v2.c3p0.ComboPooledDataSource();
		Properties props =null;
		System.out.println("In Connection data source ");
		try {
			props = getProperties(datbasePropertiesLocation);
			dataSource.setProperties(props);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		try {
			dataSource.setDriverClass(props.getProperty("driver.class"));
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 dataSource.setJdbcUrl(props.getProperty("jdbc.url")); 
		 dataSource.setUser(props.getProperty("user"));
		 dataSource.setPassword(props.getProperty("password"));
		 
		// C3P0 properties
		
		 dataSource.setAcquireIncrement(Integer.parseInt(props.getProperty("acquire.increment")));
		 dataSource.setMaxPoolSize(Integer.parseInt(props.getProperty("max.pool.size")));
		 dataSource.setMinPoolSize(Integer.parseInt(props.getProperty("min.pool.size")));
		 dataSource.setMaxStatements(Integer.parseInt(props.getProperty("max.statements")));
		 dataSource.setIdleConnectionTestPeriod(Integer.parseInt(props.getProperty("idle.connection.test.period")));
		 
		// dataSource.setTimeOut

		return dataSource;
	}

	public Properties getProperties(String propLocation) throws IOException {
		Properties properties = new Properties();

		try {
			// properties.load(this.getClass().getResourceAsStream("properties/hibernate.properties"));

			properties.load(this.getClass().getClassLoader().getResourceAsStream(propLocation));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw e;
		}

		return properties;
	}

	@Bean
	public Properties getHibernateProperties() {
		Properties properties = new Properties();

		System.out.println("hibernatePropertiesLocation:" + hibernatePropertiesLocation);

		try {
			properties = getProperties(hibernatePropertiesLocation);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
			System.exit(-1);
		}
		/*
		 * try { //properties.load(this.getClass().getResourceAsStream(
		 * "properties/hibernate.properties"));
		 * 
		 * properties.load(this.getClass().getClassLoader().getResourceAsStream(
		 * hibernatePropertiesLocation)); } catch (IOException e) { // TODO
		 * Auto-generated catch block System.out.println(e.getMessage());
		 * e.printStackTrace(); System.exit(-1); }
		 */

		/*
		 * properties.put("hibernate.dialect", hibernateDialect);
		 * properties.put("hibernate.show_sql", hibernateShowSql);
		 * 
		 * //properties.put("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);
		 * //Ecache
		 * 
		 * System.out.println("ehcacheConfigurationResourceName:"+
		 * ehcacheConfigurationResourceName);
		 * 
		 * //properties.put("hibernate.cache.provider_class",
		 * hibernateCacheProviderClass);
		 * //properties.put("hibernate.cache.use_second_level_cache",
		 * hibernateCacheUseSecondLevelCache);
		 * 
		 * 
		 * //properties.put("hibernate.cache.region.factory_class",
		 * "org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory");
		 * //properties.put("net.sf.ehcache.configurationResourceName",
		 * "ehchache.xml");
		 * 
		 */
		return properties;
	}

	@Bean
	public org.springframework.orm.hibernate5.LocalSessionFactoryBean getSessionFactory() {
		org.springframework.orm.hibernate5.LocalSessionFactoryBean sessionFactory = new org.springframework.orm.hibernate5.LocalSessionFactoryBean();
		sessionFactory.setDataSource(getDataSource());
		sessionFactory.setHibernateProperties(getHibernateProperties());
		// sessionFactory.set
		sessionFactory.setPackagesToScan(new String[] { hibernateEntityPackageScan });
		return sessionFactory;

	}

	@Bean
	public CacheManager cacheManager() {
		return new EhCacheCacheManager(ehCacheCacheManager().getObject());
	}

	@Bean
	public EhCacheManagerFactoryBean ehCacheCacheManager() {
		EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
		cmfb.setConfigLocation(new ClassPathResource(ehcacheConfigurationResourceName));
		cmfb.setShared(true);
		return cmfb;
	}
}
