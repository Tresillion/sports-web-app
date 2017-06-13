package ie.eqsports.trade;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ie.eqsports.shareholding.Shareholding;
import ie.eqsports.shareholding.ShareholdingRepository;
import ie.eqsports.transaction.Transaction;
import ie.eqsports.transaction.TransactionRepository;




@Transactional
@Service
public class TradeService {
	
	@Autowired
	private OfferRepository offerRepository;	
	
	@Autowired
	private BidRepository bidRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private ShareholdingRepository holdingRepository;
	
	
	public void offerShares(Offer offer) {
		
		offerRepository.save(offer);
		
	}
	
	public void placeBid(Bid bid) {
		
		bidRepository.save(bid);
		
	}
	
	public void buyNow(Bid bid) {

		
		//set the bid as accepted
		bid.setStatus(2);

		
		//get the offer and add this bid to list
		Offer offer = offerRepository.findOne(bid.getOfferId());
		Set<Bid> bids = offer.getBids();
		bids.add(bid);
		
		// set offer as closed
		offer.setStatus(2);
		
		// get shareholding to use details in transactions
		Shareholding holding = holdingRepository.findOne(offer.getShareholdingId());
		
		// create the transaction to credit the money from buyer's acccount
		Transaction transaction = new Transaction();
		
		BigDecimal tradeTotalPrice = bid.getAmmountPerShare()
				.multiply(new BigDecimal(offer.getQuantity()));

		
		transaction.setAccountId(bid.getBidderAccountId());
		transaction.setAmount( tradeTotalPrice.negate() );
		transaction.setStatus(2);
		transaction.setTransactionTypeId(3);
		transaction.setDateCreated(new Date());
		
		//create the transaction to to deposit the money to the seller
		Transaction sellTransaction = new Transaction();
		
	
		transaction.setAccountId(holding.getAccountId());
		transaction.setAmount( tradeTotalPrice );
		transaction.setStatus(2);
		transaction.setTransactionTypeId(3);
		transaction.setDateCreated(new Date());		
		
		//Save offer and transactions
		offerRepository.save(offer);
		transactionRepository.save(transaction);
		transactionRepository.save(sellTransaction);

		
		
		
	}
	
	

}
