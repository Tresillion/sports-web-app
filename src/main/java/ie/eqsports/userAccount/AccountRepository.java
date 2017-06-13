package ie.eqsports.userAccount;

import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {

	Account findByAccountName(String name);
	
	
}
