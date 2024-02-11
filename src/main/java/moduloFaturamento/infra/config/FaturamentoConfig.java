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
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "moduloFaturamento.repository", entityManagerFactoryRef = "faturamentoEntityManagerFactory", transactionManagerRef = "faturamentoPlatformTransactionManager")
public class FaturamentoConfig {

	@Autowired
	private JpaVendorAdapter jpaVendorAdapter;

	@Primary
	@Bean
	@ConfigurationProperties("spring.datasource")
	public DataSourceProperties faturamentoDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Primary
	@Bean
	public DataSource faturamentoDataSource(
			@Qualifier("faturamentoDataSourceProperties") DataSourceProperties faturamentoDataSourceProperties) {
		return faturamentoDataSourceProperties.initializeDataSourceBuilder().build();
	}

	@Primary
	@Bean
	public LocalContainerEntityManagerFactoryBean faturamentoEntityManagerFactory(
			@Qualifier("faturamentoDataSource") DataSource faturamentoDataSource) {

		HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");

		properties.put("hibernate.implicit_naming_strategy",
				"org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy");
		properties.put("hibernate.physical_naming_strategy",
				"org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setDataSource(faturamentoDataSource);
		entityManager.setJpaVendorAdapter(jpaVendorAdapter);
		entityManager.setPackagesToScan("moduloFaturamento");
		entityManager.setPersistenceUnitName("faturamentoPersistenceUnit");
		entityManager.setJpaPropertyMap(properties);
		return entityManager;
	}

	@Primary
	@Bean
	public PlatformTransactionManager faturamentoPlatformTransactionManager(
			@Qualifier("faturamentoEntityManagerFactory") EntityManagerFactory faturamentoEntityManagerFactory) {
		return new JpaTransactionManager(faturamentoEntityManagerFactory);
	}
}
