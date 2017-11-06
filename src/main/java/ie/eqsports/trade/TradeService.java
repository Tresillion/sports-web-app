package ie.eqsports.trade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ie.eqsports.shareholding.Shareholding;
import ie.eqsports.shareholding.ShareholdingRepository;
import ie.eqsports.transaction.Transaction;
import ie.eqsports.transaction.TransactionRepository;
import ie.eqsports.userAccount.AccountService;

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

	@Autowired
	private AccountService accountService;

	public void offerShares(Offer offer) {

		offerRepository.save(offer);

	}
	
	
	public void processExpiredOffer()  {
		
		//get a list of active offers that have passed the expiry date
		List<Offer> offers = offerRepository.getActiveExpiredOffers();
		for(Offer offer : offers) {
			
			//is there an active bid?
			Bid activeBid = bidRepository.findByIdAndStatus(offer.getId(), 1);
			if(activeBid != null) {
				try {
					acceptBid(activeBid);
				} catch(Exception e) {
					System.out.println("failed to process offer " + offer.getId());
				}
			}
			
			
		}
		
	}
	

	public void placeBid(Bid bid) throws Exception {

		
		//get the offer 
		Offer offer = offerRepository.findOne(bid.getOfferId());
		
		//if offer still valid
		if(offer.getDateTimeClosed().before(new Date())) {
			
			throw new Exception();
		}
				
		//check if there is an active bid for this offer
		Bid currentBid = bidRepository.findByIdAndStatus(offer.getId(), 1);
		
		//if there is a current bid then see if the new bid id higher
		if(currentBid != null && currentBid.getAmountPerShare().doubleValue() 
				>= bid.getAmountPerShare().doubleValue()) {
			throw new Exception();
		} 

			

		bid.setStatus(1);
		bid.setDateTimeMade(new Date());
		
		
		if(currentBid != null) {
			currentBid.setStatus(2);
			bidRepository.save(currentBid);
		}

		bidRepository.save(bid);

	}

	public List<Offer> getOffers(long holdingId) {

		return offerRepository.getOffersByShareholdingId(holdingId);
	}
	
	public List<OfferDto> getAllActiveOffers(long shareId) {
		
		
		//List<Offer> offers = offerRepository.getOffersByStatus(1);
		List<Offer> offers = offerRepository.getAllOffersForAShare(shareId);
		
		List<OfferDto> offerDtos = new ArrayList<OfferDto>();
		
		for(Offer offer : offers) {
			
			OfferDto od = new OfferDto();
			od.setId(offer.getId());
			od.setMinimumPrice(offer.getMinimumPrice() );
			od.setBuyNowPrice(offer.getBuyNowPrice());
			od.setOfferEndDate(offer.getDateTimeClosed());
			od.setQuantity(offer.getQuantity());
			
			
			Bid bid = this.bidRepository.findByOfferIdAndStatus(offer.getId(), 1);
			if(bid != null) 
				od.setHighestOffer(bid.getAmountPerShare());
			else
				od.setHighestOffer(new BigDecimal(0.0));
			offerDtos.add(od);
			
		}
		
		return offerDtos;
	}
	
	
	public void placeBid() {
		
	}

	public void buyNow(BidDto thisBid) throws Exception {

		Bid bid = new Bid();
		
		bid.setAmountPerShare(thisBid.getBidAmount());
		bid.setBidderAccountId(this.accountService.getAccountByName(
				thisBid.getBidderAccountName()).getId());
		bid.setOfferId(thisBid.getOfferId());
		
		acceptBid(bid);

	}
	
	
	public void acceptBid(Bid bid) throws Exception {
		
		
		// set the bid as accepted and date
		bid.setStatus(2);
		bid.setDateTimeMade(new Date());

		// get the offer and add this bid to list
		Offer offer = offerRepository.findOne(bid.getOfferId());

		BigDecimal tradeTotalPrice = bid.getAmountPerShare().multiply(new BigDecimal(offer.getQuantity()));

		// Check the bidder account has enough cash
		if (tradeTotalPrice.doubleValue() > accountService.getAccountBalance(bid.getBidderAccountId()).doubleValue()) {

			throw new Exception("dohh!");
		}

		Set<Bid> bids = offer.getBids();
		bids.add(bid);

		// set offer as closed
		offer.setStatus(2);

		// get shareholding to use details in transactions
		Shareholding holding = holdingRepository.findOne(offer.getShareholdingId());

		// how to actually transfer the shareholding?
		// for now assume you have to offer all shares in a shareholding
		Shareholding buyerHolding = new Shareholding();

		buyerHolding.setAccountId(bid.getBidderAccountId());
		buyerHolding.setNominalValue(holding.getNominalValue());
		buyerHolding.setShare(holding.getShare());
		buyerHolding.setQuantity(holding.getQuantity());
		buyerHolding.setQuantityHeld(0);

		// set seller shareholding quantity to 0
		holding.setQuantity(0);
		// set status to closed as well

		// create the transaction to credit the money from buyer's acccount
		Transaction transaction = new Transaction();

		transaction.setAccountId(bid.getBidderAccountId());
		transaction.setAmount(tradeTotalPrice.negate());
		transaction.setStatus(2);
		transaction.setTransactionTypeId(3);
		transaction.setDateCreated(new Date());

		// create the transaction to to deposit the money to the seller
		Transaction sellTransaction = new Transaction();

		sellTransaction.setAccountId(holding.getAccountId());
		sellTransaction.setAmount(tradeTotalPrice);
		sellTransaction.setStatus(2);
		sellTransaction.setTransactionTypeId(3);
		sellTransaction.setDateCreated(new Date());

		// Save offer and transactions
		offerRepository.save(offer);
		holdingRepository.save(holding);
		holdingRepository.save(buyerHolding);

		transactionRepository.save(transaction);
		transactionRepository.save(sellTransaction);

		
		
		
	}
	
	

}
