package ie.eqsports.trade;

import java.math.BigDecimal;
import java.util.Date;

public class OfferDto {

	
	private long id;
	private int quantity;
	private long shareholdingId;
	private BigDecimal minimumPrice;
	private BigDecimal buyNowPrice;
	private BigDecimal highestOffer;
	private String accountName;
	
	private int numOfOffers;
	private int offerDuration;
	
	private Date offerEndDate;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public long getShareholdingId() {
		return shareholdingId;
	}
	public void setShareholdingId(long shareholdingId) {
		this.shareholdingId = shareholdingId;
	}
	
	
	public Date getOfferEndDate() {
		return offerEndDate;
	}
	public void setOfferEndDate(Date offerEndDate) {
		this.offerEndDate = offerEndDate;
	}
	public BigDecimal getMinimumPrice() {
		return minimumPrice;
	}
	public void setMinimumPrice(BigDecimal minimumPrice) {
		this.minimumPrice = minimumPrice;
	}
	public BigDecimal getBuyNowPrice() {
		return buyNowPrice;
	}
	public void setBuyNowPrice(BigDecimal buyNowPrice) {
		this.buyNowPrice = buyNowPrice;
	}
	public int getNumOfOffers() {
		return numOfOffers;
	}
	public void setNumOfOffers(int numOfOffers) {
		this.numOfOffers = numOfOffers;
	}
	public int getOfferDuration() {
		return offerDuration;
	}
	public void setOfferDuration(int offerDuration) {
		this.offerDuration = offerDuration;
	}
	public BigDecimal getHighestOffer() {
		return highestOffer;
	}
	public void setHighestOffer(BigDecimal highestOffer) {
		this.highestOffer = highestOffer;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	
	
	
	
	
	
	
}
