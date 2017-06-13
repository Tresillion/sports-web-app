package ie.eqsports.userAccount;

public interface AccountDAO {
	
	public Account getAccountByUserId(String userId, String password) throws NoAccountFoundException;

	public void createNewAccount(Account account);
}
