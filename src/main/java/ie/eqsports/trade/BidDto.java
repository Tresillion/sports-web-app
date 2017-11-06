package ie.eqsports.trade;

import java.math.BigDecimal;

public class BidDto {

	
	private String bidderAccountName;
	
	private BigDecimal bidAmount;
	
	private long offerId;

	public String getBidderAccountName() {
		return bidderAccountName;
	}

	public void setBidderAccountName(String bidderAccountName) {
		this.bidderAccountName = bidderAccountName;
	}

	public BigDecimal getBidAmount() {
		return bidAmount;
	}

	public void setBidAmount(BigDecimal bidAmount) {
		this.bidAmount = bidAmount;
	}

	public long getOfferId() {
		return offerId;
	}

	public void setOfferId(long offerId) {
		this.offerId = offerId;
	}
	
	
	
	
}
