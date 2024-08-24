package demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import bank.Bank;
import bank.BankProduct;
import bank.Client;
import bank.Credit;
import bank.Deposit;

public class Demo {
	
	public static double randomMoneyRange(double min, double max) {
		return min + (max - min) * new Random().nextDouble();
	}
	
	public static int randomPercentRange(int min, int max) {
		return new Random().nextInt(max - min + 1) + min;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Bank bulgarianBank = new Bank("Bulgarian Bank", "Bulgaria, Sofia");
		
		ArrayList<Client> clients = new ArrayList<Client>();
		for(int i = 0; i < 10; i++) {
			Client c = new Client("Client " + (i+1), "Address " + (i+1), Demo.randomMoneyRange(0, 1_000_000), Demo.randomMoneyRange(1000, 10_000));
			clients.add(c);
			bulgarianBank.addClient(c);
			System.out.println(clients.get(i));
		}
		
		
		int i = 1;
		for (Client client : clients) {
			int percent = Demo.randomPercentRange(80, 100);
			System.out.println(percent);
			double money = ((double)(percent)/100) * client.getMoneyCash();
			System.out.println(money);
			switch(new Random().nextInt(2)) {
				case 0: 
					Deposit shortDep = new Deposit("Short Deposit", 3, 3);	
					client.openDeposit(bulgarianBank, money, shortDep);
					break;
				case 1:
					Deposit longDep = new Deposit("Long Deposit", 5, 12);
					client.openDeposit(bulgarianBank, money, longDep);
					break;
			}
			System.out.println("After client " + i);
			bulgarianBank.printBankBalance();
			i++;
			
			
		}
		System.out.println("----------------Deposits Info----------------------");
		for (Client client : clients) {
			client.printInfoForDeposits();
		}
		
		System.out.println("----------------Pay Interest For Deposits----------------------");
		for (Client client : clients) {
			bulgarianBank.payInterestForDeposits();
		}
		
		System.out.println("----------------Deposits Info After Payments----------------------");
		for (Client client : clients) {
			client.printInfoForDeposits();
		}
		
		
		for (Client client : clients) {
			switch(new Random().nextInt(2)) {
				
				case 0:
					Credit homeCredit = new Credit("Home Credit", 6);
					client.creditRequest(bulgarianBank, homeCredit, Demo.randomMoneyRange(40_000, 1_000_000), new Random().nextInt(60) + 1);
					break;
				case 1:
					Credit consumerCredit = new Credit("Consumer Credit", 10);
					client.creditRequest(bulgarianBank, consumerCredit, Demo.randomMoneyRange(1000, 10_0009), new Random().nextInt(60) + 1);
					break;
			}
			
		}
		
		System.out.println("----------------Credits info----------------------");
		for (Client client : clients) {
			client.printInfoForCredits();
		}
		
		
		
	}

}
