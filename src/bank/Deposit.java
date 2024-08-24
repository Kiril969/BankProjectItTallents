package bank;

public class Deposit extends BankProduct{

	private double monthlyAmount; //изплатена месечна сума
	
	
	public Deposit(String name, double annualInterestRate) {
		super(name, annualInterestRate);
	}

	public Deposit(String name, int annualInterestRate, int period) {
		super(name, annualInterestRate, period);
	}

	void setMonthlyAmount(double monthlyAmount) {
		if(monthlyAmount >= 0) {
			this.monthlyAmount = monthlyAmount;
		}
	}

	@Override
	void setCurrentBalance(double currentBalance) {
		if(currentBalance >= 0) {
			super.setCurrentBalance(currentBalance);
		}
	}
	
	@Override
	public void printInfo() {
		super.printInfo();
		System.out.println("Monthly Amount: " + this.monthlyAmount);
	}
	
}
