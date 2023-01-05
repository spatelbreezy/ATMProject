package ATM;
public class Account {
	int balance; 
	StringBuffer histroy; 
	String name, pin, accountNum;
	
	
	public Account(String name, String pin, String accountNum) {
		this.name = name; 
		this.accountNum = accountNum;
		this.pin = pin;
	}
	
	public Account(String pin, String accountNum) {
		this("NONAME", pin, accountNum);
	}
	
	public Account(Account acc) {
		this.name = acc.name; 
		this.accountNum = acc.accountNum; 
		this.histroy = new StringBuffer(acc.histroy);
		this.balance = acc.balance; 
		this.pin = acc.pin;
	}
	
	public int getBalance() {
		return this.balance;
	}
	
	public String getAccountNum() {
		return this.accountNum;
	}
	
	public String getHistory() {
		return this.histroy.toString();
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getPin() {
		return this.pin;
	}
	
	public void addBalance(int delta) {
		this.balance += delta;
	}
	
	public boolean withdrawBalance(int delta) {
		if (delta <= this.balance) {
			this.balance -= delta;
			return true;
		} 
		return false;
	}
	@Override
	public String toString() {
		return "Account [balance=" + balance + ", histroy=" + histroy + ", name=" + name + ", pin=" + pin
				+ ", accountNum=" + accountNum + "]";
	}

}

