package ie.eqsports.shareholding;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ie.eqsports.trade.Bid;
import ie.eqsports.trade.Offer;

@Entity
@Table(name = "SHAREHOLDING", schema="SPORTSEQ" )
public class Shareholding {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SHD_ID")
	long id;
	
	@Column(name = "SHD_QUANTITY")
	int quantity;

	@Column(name = "SHD_QUANTITY_HELD")
	int quantityHeld;
	
	
	@Column(name="SHD_NOMINAL_VALUE", precision=10, scale=2)
	BigDecimal nominalValue;
	
	@OneToOne(optional=false)
    @JoinColumn(
      name="SHARE_SHR_ID")
	Share share;
	
	@Column(name = "ACCOUNT_ACC_ID")
	long accountId;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "shareholdingId", cascade=CascadeType.ALL)
	private Set<Offer> offers;	
	
	public Shareholding() {
		super();
	}
	
	
	public Share getShare() {
		return share;
	}


	public void setShare(Share share) {
		this.share = share;
	}


	public Set<Offer> getOffers() {
		return offers;
	}


	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}


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

	public BigDecimal getNominalValue() {
		return nominalValue;
	}

	public void setNominalValue(BigDecimal nominalValue) {
		this.nominalValue = nominalValue;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}


	public int getQuantityHeld() {
		return quantityHeld;
	}


	public void setQuantityHeld(int quantityHeld) {
		this.quantityHeld = quantityHeld;
	}
	
	
}
