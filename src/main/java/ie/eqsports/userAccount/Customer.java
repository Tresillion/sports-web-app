package ie.eqsports.userAccount;

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
@Table(name = "CUSTOMER", schema="SPORTSEQ" )
public class Customer {
	
	
	
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CSR_ID")
	private long id;
	
	@Column(name = "CSR_FIRST_NAME")
	private String firstName;
	
	@Column(name = "CSR_SURNAME")	
	private String surname;
	
	@Column(name = "CSR_DOB")
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
	@Column(name = "CSR_EMAIL")
	private String email;
	
	@Column(name = "CSR_MOBILE_NO")
	private String mobileNumber;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return firstName + " " + surname;
	}
	
	

}
