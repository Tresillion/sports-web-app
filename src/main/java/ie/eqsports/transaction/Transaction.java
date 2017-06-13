package ie.eqsports.transaction;

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

import ie.eqsports.userAccount.Account;

@Entity
@Table(name = "TRANSACTION", schema="SPORTSEQ" )
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TRN_ID")	
	private long id;
	
	@Column(name = "TRN_TYPE")
	private long transactionTypeId;
	
	@Column(name = "TRN_AMOUNT", precision=10, scale=2)	
	private BigDecimal amount;
	
	@Column(name = "TRN_DATE_CREATED",columnDefinition="DATETIME" )
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	
	@Column(name = "TRANSACTION_STATUS_TST_ID")
	private long status;
	
	/*
    @ManyToOne(optional = false)
    @JoinColumn(name="ACCOUNT_ACC_ID")
	private Account account;*/
	
	@Column(name = "ACCOUNT_ACC_ID")
	private long accountId; 

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTransactionTypeId() {
		return transactionTypeId;
	}

	public void setTransactionTypeId(long transactionTypeId) {
		this.transactionTypeId = transactionTypeId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	/*
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	*/
	
	
    
    
    
	
}
