package ie.eqsports.userAccount;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;

import ch.qos.logback.core.status.Status;
import ie.eqsports.shareholding.Share;
import ie.eqsports.shareholding.ShareholdingService;
import ie.eqsports.trade.Bid;
import ie.eqsports.trade.BidDto;
import ie.eqsports.trade.Offer;
import ie.eqsports.trade.OfferDto;
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
	AccountService accountService;
	
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
    	

    	Account userAccount = accountService.getAccountByName(account.accountName);
    	
    	if(userAccount == null) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{'message':'No account found'}");
    	} else if( !userAccount.getPassword().equals(account.password)) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{'message':'Password is incorrect'}");
    	}

    	return ResponseEntity.ok(userAccount);
    	
    	    	
    }
    
    @RequestMapping(value ="/account", method = RequestMethod.GET)
    public ResponseEntity getAccountDetails(@RequestParam(name="userName") String userName) {
    //public Account getAccounts(@RequestBody Account account) {
    	
    	System.out.println("Account name = " + userName);
    	

    	Account userAccount = accountService.getAccountByName(userName);
    	
    	if(userAccount == null) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{'message':'No account found'}");
    	} 

    	return ResponseEntity.ok(userAccount);
    	
    	    	
    }
    
    

    @RequestMapping(value ="/buyNow", method = RequestMethod.POST)
    public ResponseEntity<BidDto> buyNow(@RequestBody BidDto bid) {
    //public Account getAccounts(@RequestBody Account account) {
    	
    	try{
        	tradeService.buyNow(bid);    		
    	} catch(Exception e) {
    		e.printStackTrace();
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}


    	return ResponseEntity.ok(bid);
    	
    	    	
    }    
    
    @RequestMapping(value ="/bid", method = RequestMethod.POST)
    public ResponseEntity<Bid> makeBid(@RequestBody Bid bid) {
    	
    	try{
        	tradeService.placeBid(bid);    		
    	} catch(Exception e) {
    		e.printStackTrace();
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
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
    public Account registerAccount(@RequestBody Account account) {
    	
    	
    	Account newAccount = accountService.createAccount(account);
    	return newAccount;
    }
    

    @RequestMapping(value = "/offer", method = RequestMethod.POST) 
    public ResponseEntity<Offer> offerShares(@RequestBody Offer offer ) {
    	
    	
    	offer.setDateOffered(new Date());
    	
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(new Date());
    	calendar.add(Calendar.DATE, 5);
    	//will let the seller decide the time from a few options
    	
    	offer.setDateTimeClosed(calendar.getTime());
    	
    	//will need to increase number of shares held
    	// this will be used to calculate available shares = qty - held
    	
    	offerRepository.save(offer);
    	return ResponseEntity.ok(offer);
    }    
	
    @RequestMapping(value = "/offer", method = RequestMethod.GET) 
    public ResponseEntity<Offer[]> getOffers(@RequestParam(name="id") long id ) {
    	
    	List<Offer> offers = offerRepository.getOffersByShareholdingId(id);
    	Offer[] offArr = new Offer[offers.size()];
    	offArr = (Offer[]) offers.toArray(offArr);
    	
    	return ResponseEntity.ok(offArr);
    }  
    
    
    
    @RequestMapping(value="/shares/{id}", method = RequestMethod.GET)
    public ResponseEntity getShareById(@PathVariable("id") long id)
    {
    	
    	Share share = shareholdingService.getShare(id);

    	if(share == null ) {
    		return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found");
    	}
    	return ResponseEntity.ok(share);
    	
   	
    }    
   
    @RequestMapping(value="/shares", method = RequestMethod.GET)
    public ResponseEntity<Share[]> getShares(
    		@RequestParam(name="playerName", required = false)
    String playerName) {
    	
    	List<Share> shares;
    	if(playerName == null) {
    		shares = shareholdingService.getShares();
    	} else {
    		shares = shareholdingService.getShares(playerName);
    	}
    	
    	Share[] shArr = new Share[shares.size()];
    	shArr = (Share[])shares.toArray(shArr);
    	return ResponseEntity.ok(shArr);
    	
    	
    	
    }
 
    

}
