package moduloFaturamento.infra.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class TransactionManagerConfig {

	@Bean("chainedTransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("faturamentoPlatformTransactionManager") final PlatformTransactionManager faturamentoPlatformTransactionManager,
			@Qualifier("anexoPlatformTransactionManager") final PlatformTransactionManager anexoPlatformTransactionManager) {

		return new ChainedTransactionManager(faturamentoPlatformTransactionManager, anexoPlatformTransactionManager);

	}

	@Bean
	@ConfigurationProperties("spring.jpa")
	public JpaProperties jpaProperties() {
		return new JpaProperties();
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter(@Qualifier("jpaProperties") JpaProperties jpaProperties) {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(jpaProperties.isShowSql());

		return hibernateJpaVendorAdapter;
	}

}
