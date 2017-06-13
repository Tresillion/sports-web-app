package ie.eqsports.trade;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "BID", schema="SPORTSEQ" )
public class Bid {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BID_ID")	
	private long id;	
	
	@Column(name="BID_BIDDER_ACCOUNT_ID")
	private long bidderAccountId;
	
	@Column(name="BID_DATETIME_MADE", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTimeMade;
	
	@Column(name = "BID_AMOUNT_PER_SHARE", precision=10, scale=2)
	private BigDecimal amountPerShare;
	
	@Column(name = "BID_STATUS")
	private int status;
	
	@Column(name = "OFFER_OFR_ID")
	private long offerId;
	
	/*
	@ManyToOne(optional = false)
	@JoinColumn(name="OFFER_OFR_ID")
	private Offer offer;
	*/

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getBidderAccountId() {
		return bidderAccountId;
	}

	public void setBidderAccountId(long bidderAccountId) {
		this.bidderAccountId = bidderAccountId;
	}

	public Date getDateTimeMade() {
		return dateTimeMade;
	}

	public void setDateTimeMade(Date dateTimeMade) {
		this.dateTimeMade = dateTimeMade;
	}

	public BigDecimal getAmountPerShare() {
		return amountPerShare;
	}

	public void setAmountPerShare(BigDecimal amountPerShare) {
		this.amountPerShare = amountPerShare;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
/*
	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}
*/

	public long getOfferId() {
		return offerId;
	}

	public void setOfferId(long offerId) {
		this.offerId = offerId;
	}
	
	
	
	
	
}
