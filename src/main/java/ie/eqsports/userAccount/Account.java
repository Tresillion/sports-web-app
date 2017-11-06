package ie.eqsports.userAccount;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Basic;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;

import ie.eqsports.shareholding.*;

@Entity
@Table(name = "ACCOUNT", schema="SPORTSEQ")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ACC_ID")
	long id;
	
	
	@Column(name = "ACC_NAME")
	String accountName;

	@Column(name = "ACC_PASSWORD")
	String password;
	
	@Column(name = "ACC_TYPE")
	int accountType;
	
	@Column(name = "ACC_DATE", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	Date dateCreated;
	
	
	
	@OneToOne(optional=false)
    @JoinColumn(
      name="CUSTOMER_CSR_ID")
	private Customer customer;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "accountId" )
	private Set<Shareholding> shareholdings;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Transient
	private BigDecimal balance;

	public Set<Shareholding> getShareholdings() {
		return this.shareholdings;
	}
	
	
	
	public Account() {
		super();
	}

	
		
	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}



	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}


	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}



	@Override
	public String toString() {

		return accountName;

	}



	public BigDecimal getBalance() {
		return balance;
	}



	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}


	
}
