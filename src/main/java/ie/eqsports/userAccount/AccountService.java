package ie.eqsports.userAccount;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.hadoop.mapred.gethistory_jsp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ie.eqsports.transaction.TransactionRepository;

@Transactional
@Service
public class AccountService {

	@Autowired
	TransactionRepository transactionRepository;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	CustomerRepository customerRepository;
	
	public BigDecimal getAccountBalance(long accountId) {
		
		return transactionRepository.getAccountBalance(accountId);
		
	}
	
	public Account getAccountByName(String name) {
		
		Account account = accountRepository.findByAccountName(name);
		account.setBalance(getAccountBalance(account.getId()));
		return account;
	
	
	}
	
	public Account createAccount(Account custAccount) {
		
		//Customer customer = new Customer();
		//Account account = new Account();
	/*	
		customer.setFirstName(custAccount.getFirstName());
		customer.setSurname(custAccount.getSurname());
		customer.setMobileNumber(custAccount.getMobileNumber());
		customer.setEmail(custAccount.getEmail());
		customer.setDateOfBirth(custAccount.getDob());
		*/
		customerRepository.save(custAccount.getCustomer());
		
		//account.setAccountName(custAccount.getUserName());
		//account.setPassword(custAccount.getPassword());
		custAccount.setDateCreated(new Date());
		//account.setCustomer(customer);
		
		accountRepository.save(custAccount);
		
		return custAccount;
		
	}
	
	
	
}
