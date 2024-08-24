package bank;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Client {
	private static int indexCredit = 0;
	
	private final String name;
	private String address;
	private double moneyCash;
	private double salary;
	private List<Deposit> deps;
	private List<Credit> credits;
	
	public Client(String name, String address, double moneyCash, double salary) {
		if(address != null && moneyCash >= 0 && salary >= 0) {
			this.name = name;
			this.address = address;
			this.moneyCash = moneyCash;
			this.salary = salary;
			this.deps = new ArrayList<>();
			this.credits = new ArrayList<Credit>();
		}
		else {
			this.name = null;
			System.out.println("Bad input data for a client");
		}
	}
	
	public void addDeposit(Deposit dep) {
		if(dep != null) {
			this.deps.add(dep);
		}
	}
	
	public void addCredit(Credit credit) {
		if(credit != null) {
			this.credits.add(credit);
		}
	}
	
	List<Credit> getCredits() {
		return credits;
	}

	double getSalary() {
		return salary;
	}


	void setMoneyCash(double moneyCash) {
		if(moneyCash >= 0) {
			this.moneyCash = moneyCash;
		}
	}



	public double getMoneyCash() {
		return moneyCash;
	}



	public void openDeposit(Bank bank, double money, Deposit dep) {
		bank.acceptDeposit(this, money, dep);
	}
	
	public void creditRequest(Bank bank, Credit credit, double creditMoney, int period) {
		bank.giveCredit(this, credit, creditMoney, period);
	}
	
	public void payCredit() {
		if(this.credits != null) {
			Credit cred = this.credits.get(indexCredit);
			if(this.moneyCash >= cred.getMonthlyPayment()) {
				this.moneyCash -= cred.getMonthlyPayment();
				cred.setCurrentBalance(cred.getCurrentBalance() + cred.getMonthlyPayment());//credits balances are < 0
				if(cred.getCurrentBalance() == 0) {
					this.credits.remove(indexCredit);
				}
				indexCredit++;
			}
		}
		if(indexCredit >= this.credits.size()) {
			indexCredit = 0;
		}
	}
	
	//compare name and address
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public void printInfoForDeposits() {
		if(this.deps != null) {
			for (Deposit dep : deps) {
				dep.printInfo();
			}
			
		}
	}
	
	public void printInfoForCredits() {
		if(this.credits != null) {
			for (Credit credit : credits) {
				credit.printInfo();
			}
		}
	}
	
	@Override
	public String toString() {
		return this.name + " " + this.address + " " + this.moneyCash + " " + this.salary;
	}
	
	
}
