package ie.eqsports.transaction;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PaymentService {
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	TransactionRepository transactionRepository;
	
		
	public void processPayment(long accountId, Payment payment) {
		
		Transaction transaction = new Transaction();
		
		transaction.setAccountId(accountId);
		transaction.setAmount(payment.getAmount());
		transaction.setStatus(2);
		transaction.setTransactionTypeId(2);
		transaction.setDateCreated(new Date());
		
		
		
		payment.setPaymentDate(new Date());
		paymentRepository.save(payment);
		transactionRepository.save(transaction);

			
	}
	

}
