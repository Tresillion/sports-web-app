package ie.eqsports.trade;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;

import ie.eqsports.shareholding.Shareholding;

@Entity
@Table(name = "OFFER", schema="SPORTSEQ" )
public class Offer {
	
	//  `OFR_ID` INT NOT NULL AUTO_INCREMENT,
	  //`OFR_QUANTITY` INT NOT NULL,
	  //`OFR_MIN_PRICE` DECIMAL(10,2) NOT NULL,
	  //`OFR_BUY_NOW_PRICE` DECIMAL(10,2) NOT NULL,
	  //`OFR_STATUS` INT NOT NULL,
	  //`OFR_DATE_OFFERED` VARCHAR(45) NOT NULL,
	  //`OFR_CLOSE_DATETIME` DATETIME NOT NULL,
	  //`OFR_DATE_CREATED` DATETIME NOT NULL,
	  //`OFR_DATE_UPDATED` DATETIME NOT NULL,
	  //`SHAREHOLDING_SHD_ID` INT NOT NULL,//
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "OFR_ID")	
	private long id;
	
	@Column(name = "OFR_QUANTITY")
	private int quantity;
	
	@Column(name = "OFR_MIN_PRICE", precision=10, scale=2)
	private BigDecimal minimumPrice;
	
	@Column(name = "OFR_BUY_NOW_PRICE", precision=10, scale=2)
	private BigDecimal buyNowPrice;
	
	@Column(name = "OFR_STATUS")
	private int status;
	
	@Column(name = "OFR_DATE_OFFERED", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOffered; 
	
	@Column(name = "OFR_CLOSE_DATETIME", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTimeClosed; 	
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "offerId", cascade=CascadeType.ALL)
	private Set<Bid> bids;
	
	/*
    @ManyToOne(optional = false)
    @JoinColumn(name="SHAREHOLDING_SHD_ID")
	private Shareholding shareholding;
    */
    
    @Column(name = "SHAREHOLDING_SHD_ID")
    private long shareholdingId;

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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getDateOffered() {
		return dateOffered;
	}

	public void setDateOffered(Date dateOffered) {
		this.dateOffered = dateOffered;
	}

	public Date getDateTimeClosed() {
		return dateTimeClosed;
	}

	public void setDateTimeClosed(Date dateTimeClosed) {
		this.dateTimeClosed = dateTimeClosed;
	}
/*
	public Shareholding getShareholding() {
		return shareholding;
	}

	public void setShareholding(Shareholding shareholding) {
		this.shareholding = shareholding;
	}
*/
	public Set<Bid> getBids() {
		return bids;
	}

	public void setBids(Set<Bid> bids) {
		this.bids = bids;
	}

	public long getShareholdingId() {
		return shareholdingId;
	}

	public void setShareholdingId(long shareholdingId) {
		this.shareholdingId = shareholdingId;
	}
    
    
    

}
