package moduloFaturamento.infra.config;

import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "moduloFaturamento.anexos.repository", entityManagerFactoryRef = "anexoEntityManagerFactory", transactionManagerRef = "anexoPlatformTransactionManager")
public class AnexoConfig {

	@Autowired
	private JpaVendorAdapter jpaVendorAdapter;

	@Bean
	@ConfigurationProperties("spring.anexo.datasource")
	public DataSourceProperties anexoDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	public DataSource anexoDataSource(
			@Qualifier("anexoDataSourceProperties") DataSourceProperties anexoDataSourceProperties) {
		return anexoDataSourceProperties.initializeDataSourceBuilder().build();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean anexoEntityManagerFactory(@Qualifier("anexoDataSource") DataSource anexoDataSource) {

		HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.dialect", "org.hibernate.dialect.SQLServer2012Dialect");

		properties.put("hibernate.implicit_naming_strategy","org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy");
		properties.put("hibernate.physical_naming_strategy","org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
		
		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setDataSource(anexoDataSource);
		entityManager.setJpaVendorAdapter(jpaVendorAdapter);
		entityManager.setPackagesToScan("moduloFaturamento");
		entityManager.setPersistenceUnitName("anexoPersistenceUnit");
		entityManager.setJpaPropertyMap(properties);
		return entityManager;

	}

	@Bean
	public PlatformTransactionManager anexoPlatformTransactionManager(
			@Qualifier("anexoEntityManagerFactory") EntityManagerFactory anexoEntityManagerFactory) {
		return new JpaTransactionManager(anexoEntityManagerFactory);
	}
}
