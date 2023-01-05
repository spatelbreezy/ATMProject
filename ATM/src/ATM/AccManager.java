package ATM;

import java.util.ArrayList;

public class AccManager {
	private ArrayList<Account> allAccs;
	
	public AccManager() {
		allAccs = new ArrayList<>();
	}
	
	public boolean addAccount(String name, String pin, String accountNum) {
		if (name == null || pin == null || accountNum == null) {
			throw new IllegalArgumentException("Invalid parameters");
		}
		
		Account account = new Account(name, pin, accountNum);
		allAccs.add(account);
		return true;
	}
	
	public boolean addAccount(Account acc) {
		if (acc == null) {
			throw new IllegalArgumentException("Error");
		} else {
			allAccs.add(acc);
			return true;
		}
	}

	@Override
	public String toString() {
		return "AccManager [allAccs=" + allAccs + "]";
	}
	
	public Account findAccount(String accountNum, String pin) {
		for (Account acc : allAccs) {
			if (acc.getAccountNum().equals(accountNum) && acc.getPin().equals(pin)) {
				return acc;
			}
		}
		return null;
	}
	
	public int findIndex(Account acc) {
		Account account = (Account) acc;
		int idx = allAccs.indexOf(account);
		
		return idx;
	}
	
}
