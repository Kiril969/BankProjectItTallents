package bank;

public abstract class BankProduct {
	
	private String name;
	private double annualInterestRate; //годишен лихвен процент >0
	private int period; //в месеци
	private double currentBalance = 0;
	
	
	
	public BankProduct(String name, double annualInterestRate) {
		if(name != null && annualInterestRate >=0 && annualInterestRate <= 100) {
			this.name = name;
			this.annualInterestRate = annualInterestRate;
		}
		else {
			System.out.println("Bad input data for a bank product!");
		}
	}

	public BankProduct(String name, double annualInterestRate, int period) {
		if(name != null && annualInterestRate >= 0 && annualInterestRate <= 100 && period >= 1 && period <= 60) {
			this.name = name;
			this.annualInterestRate = annualInterestRate;
			this.period = period;
		}
		else {
			System.out.println("Bad input data!");
		}
	}

	public double getCurrentBalance() {
		return currentBalance;
	}

	void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}

	double getAnnualInterestRate() {
		return annualInterestRate;
	}

	void setPeriod(int period) {
		if(period >= 1 && period <= 60) {
			this.period = period;
		}
		else {
			System.out.println("Invalid value for a period (1-60 months).");
		}
	}

	int getPeriod() {
		return period;
	}

	public void printInfo() {
		System.out.println("Name: " + this.name + " ; AnnualInterestRate: " + this.annualInterestRate + " ; Period: " + this.period + 
				" ; Current Balance: " + this.currentBalance);
	}
	
	
	
}
