package ie.eqsports.transaction;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

	@Query(value = "select sum(t.TRN_AMOUNT) AS 'Balance' from SPORTSEQ.ACCOUNT a join SPORTSEQ.TRANSACTION t on t.ACCOUNT_ACC_ID = a.ACC_ID where a.ACC_ID = :id",
			nativeQuery=true)
	BigDecimal getAccountBalance(@Param("id") long accountId);
	
	
	
}
