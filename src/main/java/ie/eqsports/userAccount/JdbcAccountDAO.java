package ie.eqsports.userAccount;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;


public class JdbcAccountDAO implements AccountDAO {

	@Autowired
	DataSource dataSource;
	
	

	@Override
	public void createNewAccount(Account account) {
		// TODO Auto-generated method stub
		
		//first check too see if this account already exists
		if(!checkAccountExistenceByUserId(account.accountName)) {
			
			Connection con = null;
			Account newAccount = new Account();
			try{
			
				
				con = this.dataSource.getConnection();
				PreparedStatement pstmt = 
						con.prepareStatement("insert into SPORTS.USER_ACCOUNT (ACC_ID, ACC_NAME,ACC_PASSWORD,ACC_FIRST_NAME,ACC_SURNAME,ACC_TYPE, ACC_DATE ) values(?,?,?,?,?,?,?)");
				pstmt.setInt(1, 3);
				pstmt.setString(2, account.accountName);
				pstmt.setString(3, account.password);
				//pstmt.setString(4, account.firstName);
				//pstmt.setString(5, account.surname);
				pstmt.setInt(6, 1);
				pstmt.setDate(7,java.sql.Date.valueOf(java.time.LocalDate.now()));

				
				pstmt.execute();
				con.close();
				
			}
			catch(Exception e) {
				e.printStackTrace();
				
			} 
			return;
			
		}




	
		
	}

	private boolean checkAccountExistenceByUserId(String userId) {
		
		Connection con = null;
		boolean result = false;
		try{
		
			con = this.dataSource.getConnection();
			PreparedStatement pstmt = 
					con.prepareStatement("select * from SPORTS.USER_ACCOUNT where ACC_NAME = ?");
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();
			

			
			if (rs.next()) {
				result = true;
			} else  {
				result = false;
			}

		}
		catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				
			} 
			catch(SQLException s) {/* ignore */ }

		}
		return result;
	
	}


	public Account getAccountByUserId(String userId, String password) throws NoAccountFoundException {
		// TODO Auto-generated method stub

		Connection con = null;
		Account newAccount = new Account();
		try{
		
			con = this.dataSource.getConnection();
			PreparedStatement pstmt = 
					con.prepareStatement("SELECT ACC.ACC_PASSWORD, ACC.ACC_NAME, ACC.ACC_TYPE, CUST.CSR_FIRST_NAME, CUST.CSR_SURNAME FROM SPORTSEQ.ACCOUNT AS ACC inner join SPORTSEQ.CUSTOMER AS CUST ON CUST.CSR_ID = ACC.CUSTOMER_CSR_ID   WHERE ACC_NAME = ?");
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();
			

			
			if (rs.next()) {
				
				String thisPassword = rs.getString("ACC_PASSWORD");
				if(thisPassword.equals(password)) {
				
					newAccount.setAccountName(rs.getString("ACC_NAME"));
					//newAccount.setFirstName(rs.getString("CSR_FIRST_NAME"));
					//newAccount.setSurname(rs.getString("CSR_SURNAME"));
					newAccount.setAccountType(rs.getInt("ACC_TYPE"));
				} else {
					throw new NoAccountFoundException();
				}
				
				
				
			} else  {
				throw new NoAccountFoundException();
			}

		}
		catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} 
			catch(SQLException s) {/* ignore */ }
		}
		return newAccount;
	}

}
