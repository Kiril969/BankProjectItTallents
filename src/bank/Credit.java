package bank;

public class Credit extends BankProduct{
	
	private double monthlyPayment; //месечна вноска
	
	public Credit(String name, int annualInterestRate) {
		super(name, annualInterestRate);
	}
	
	
	
	public Credit(String name, double annualInterestRate, int period) {
		super(name, annualInterestRate, period);
	}



	@Override
	public void printInfo() {
		super.printInfo();
		System.out.println(this.monthlyPayment);
	}

	double getMonthlyPayment() {
		return monthlyPayment;
	}

	void setMonthlyPayment(double monthlyPayment) {
		if(monthlyPayment >= 0) {
			this.monthlyPayment = monthlyPayment;
		}
	}

	@Override
	void setCurrentBalance(double currentBalance) {
		if(currentBalance < 0) {
			super.setCurrentBalance(currentBalance);
		}
	}
	
}
