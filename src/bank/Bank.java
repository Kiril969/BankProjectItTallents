package bank;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Bank {
	public static final double Bank_Reserve_Percent = 0.1; //10%
	
	private String name;
	private String address;
	private List<BankProduct> products;
	private double balance;
	private double bankReserve;
	private Set<Client> clients;
	
	
	public Bank(String name, String address) {
		if(name != null && address != null) {
			this.name = name;
			this.address = address;
			this.products = new ArrayList<BankProduct>();
			this.balance = 0;
			this.bankReserve = 0;
			this.clients = new HashSet<Client>();
		}
		else {
			System.out.println("Bad input data for a bank!");
		}
	}

	public void addClient(Client c) {
		if(c != null) {
			this.clients.add(c);
		}
	}

	void acceptDeposit(Client c, double money, Deposit dep) {
		if(c.getMoneyCash() > 0) {
			c.setMoneyCash(c.getMoneyCash() - money);
			this.products.add(dep);
			c.addDeposit(dep);
			dep.setCurrentBalance(money);
			this.balance += money;
			this.bankReserve += money * Bank_Reserve_Percent;
		}	
		else {
			System.out.println("Sorry, you have no money!");
		}
	}
	
	public void payInterestForDeposits() {
		for (BankProduct product : products) {
			if(product instanceof Deposit) {
				double money = product.getCurrentBalance() * product.getAnnualInterestRate()/100*((double)product.getPeriod()/12);;
				if(this.balance > money && this.balance - money > this.bankReserve) {
					product.setCurrentBalance(product.getCurrentBalance() + money);
					((Deposit) product).setMonthlyAmount(money/product.getPeriod());
					//TODO lower bank money
					this.balance -= money;
				}
				else {
					System.out.println("Sorry we have a problem, try again after few days!");
					continue;
				}
			}
		}
	}
	
	public static double monthlyPayment(double creditMoney, double annualInterestRate, int period) {
		double monthlyInterestRate = (annualInterestRate / 100) / 12;
		return (creditMoney * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, period)) / (Math.pow(1 + monthlyInterestRate, period) - 1);
	}
	
	void giveCredit(Client c, Credit credit, double creditMoney, int period) {
		//all monthly payments for client
		double sum = 0;
		for (Credit cred: c.getCredits()) {
			sum += cred.getMonthlyPayment();
		}
		
		//add monthly payment for the new credit
		double totalMonthlyPayments = sum + monthlyPayment(creditMoney, credit.getAnnualInterestRate(), period);
		if(this.balance - creditMoney < this.bankReserve) {
			System.out.println("No money in the bank! Try again after few months or ask for less money!");
		}
		else {
			//total monthly paymnets for all the credits must be lower than 50% of client's monthly salary
			if(totalMonthlyPayments <= c.getSalary()/2) {
				credit.setCurrentBalance(-creditMoney);
				credit.setPeriod(period);
				credit.setMonthlyPayment(monthlyPayment(creditMoney, credit.getAnnualInterestRate(), period));
				c.addCredit(credit);
				c.setMoneyCash(c.getMoneyCash() + creditMoney);
				this.balance -= creditMoney;
			}
			else {
				System.out.println("Sorry, your salary is not enough for this amount of money!");
			}
		}
	}
	
	public void printBankBalance() {
		System.out.println(this.balance);
	}

}
