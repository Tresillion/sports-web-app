package ie.eqsports.userAccount;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ie.eqsports.shareholding.ShareholdingService;
import ie.eqsports.trade.Bid;
import ie.eqsports.trade.Offer;
import ie.eqsports.trade.OfferRepository;
import ie.eqsports.trade.TradeService;
import ie.eqsports.transaction.Payment;
import ie.eqsports.transaction.PaymentService;

@RestController
@CrossOrigin
public class AccountController {
	
	@Autowired
	AccountDAO accountDao;
	
	@Autowired
	AccountRepository repository;
	
	@Autowired
	PaymentService paymentService;
	
	@Autowired
	ShareholdingService shareholdingService;
	
	@Autowired
	OfferRepository offerRepository;
	
	@Autowired
	TradeService tradeService;
	
	
    @RequestMapping(value ="/userLogin", method = RequestMethod.POST)
    public ResponseEntity getAccounts(@RequestBody Account account) {
    //public Account getAccounts(@RequestBody Account account) {
    	
    	System.out.println("Account name = " + account.accountName);
    	

    	Account userAccount = repository.findByAccountName(account.accountName);
    	
    	if(userAccount == null) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{'message':'No account found'}");
    	} else if( !userAccount.getPassword().equals(account.password)) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{'message':'Password is incorrect'}");
    	}

    	return ResponseEntity.ok(userAccount);
    	
    	    	
    }

    @RequestMapping(value ="/bid", method = RequestMethod.POST)
    public ResponseEntity<Bid> getAccounts(@RequestBody Bid bid) {
    //public Account getAccounts(@RequestBody Account account) {
    	
    	tradeService.buyNow(bid);

    	return ResponseEntity.ok(bid);
    	
    	    	
    }    
    
    
    @RequestMapping(value = "/payment", method = RequestMethod.POST) 
    public ResponseEntity makePayment(@RequestBody Payment payment, @RequestParam(name="accountId") long accountId) {
    	
    	
    	paymentService.processPayment(accountId, payment);
    	
    	return ResponseEntity.ok(payment);
    }

    @RequestMapping(value = "/holding", method = RequestMethod.GET) 
    public ResponseEntity makePayment(@RequestParam(name="holdingId") long holdingId) {
    	
    	
    	long result = shareholdingService.getActiveShares(holdingId);
    	
    	return ResponseEntity.ok(result);
    }    
    
    
    @RequestMapping(value ="/register", method = RequestMethod.POST)
    public String registerAccount(@RequestBody Account account) {
    	
    	accountDao.createNewAccount(account);
    	return "success";
    	
    }
    

    @RequestMapping(value = "/offer", method = RequestMethod.POST) 
    public ResponseEntity<Offer> offerShares(@RequestBody Offer offer ) {
    	
    	
    	offer.setDateOffered(new Date());
    	
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(new Date());
    	calendar.add(Calendar.DATE, 5);
    	
    	offer.setDateTimeClosed(calendar.getTime());
    	
    	offerRepository.save(offer);
    	return ResponseEntity.ok(offer);
    }    
	
	
//todo: register service
}
