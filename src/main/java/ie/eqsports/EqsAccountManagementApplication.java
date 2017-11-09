package ie.eqsports;

import javax.sql.DataSource;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import ie.eqsports.userAccount.Account;
import ie.eqsports.userAccount.AccountRepository;

@SpringBootApplication
@EnableScheduling
public class EqsAccountManagementApplication {

	@Autowired
	AccountRepository repository;
	
	@Autowired
	DataSource dataSource;
	
	public static void main(String[] args) {
		SpringApplication.run(EqsAccountManagementApplication.class, args);
	}
	
/*
    @Transactional(readOnly = true)
    public void run(String... args) throws Exception {

        System.out.println("DATASOURCE = " + dataSource);

        System.out.println("\n1.findAll()...");
        
        
        Account account = this.repository.findByAccountName("jbond");
        System.out.println(account);
        
 
        System.out.println("Done!");

    }

	
	*/
	
}
