package ie.eqsports.shareholding;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "SHARE", schema="SPORTSEQ" )
public class Share {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SHR_ID")
	long id;
	
	@Column(name = "SHARE_NAME")
	String name;
	
	@Column(name = "SHR_PRICE", precision=8, scale=2)
	private BigDecimal sharePrice;
	
	@Column(name = "SHR_ISSUED_DATE")
	@Temporal(TemporalType.DATE)
	Date dateIssued;
	
	public Share() {
		
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateIssued() {
		return dateIssued;
	}

	public void setDateIssued(Date dateIssued) {
		this.dateIssued = dateIssued;
	}

	public BigDecimal getSharePrice() {
		return sharePrice;
	}

	public void setSharePrice(BigDecimal sharePrice) {
		this.sharePrice = sharePrice;
	}
	

	
	
	
	
}
