package ie.eqsports;

import org.springframework.context.annotation.Configuration;

import ie.eqsports.transaction.PaymentRepository;
import ie.eqsports.transaction.PaymentService;
import ie.eqsports.userAccount.AccountDAO;
import ie.eqsports.userAccount.JdbcAccountDAO;

import org.springframework.context.annotation.Bean;

@Configuration
public class BeanConfiguration {
	
	
	@Bean
	public AccountDAO getAccountDao() {
		return new JdbcAccountDAO();
	}

	@Bean
	PaymentService getPaymentService() {
		return new PaymentService();
	}
}
