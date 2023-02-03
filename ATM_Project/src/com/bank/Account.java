package com.bank;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Account 
{
	//variables
	private int customerNumber;
	private int pinNumber;
	private double checkingBalance =0.0;
	private double savingBalance =0.0;


	Scanner input=new Scanner (System.in);

	DecimalFormat moneyFormat=new DecimalFormat("'₹'###,##0.00");


	//

	public Account() 
	{

	}

	public Account(int customerNumber,int pinNumber)
	{
		this.customerNumber=customerNumber;
		this.pinNumber=pinNumber;
	}
	public Account(int customerNumber, int pinNumber,double checkingBalance,double savingBalance)
	{
		this.customerNumber=customerNumber;
		this.pinNumber=pinNumber;
		this.checkingBalance=checkingBalance;
		this.savingBalance=savingBalance;

	}

	public double getSavingBalance() 
	{
		return savingBalance;
	}

	public int setCustomerNumber(int customerNumber)
	{
		this.customerNumber=customerNumber;
		return customerNumber;
	}

	public int getCustomerNumber()
	{
		return customerNumber;
	}

	public int getPinNumber() {
		return pinNumber;
	}

	public void setPinNumber(int pinNumber) {
		this.pinNumber = pinNumber;
	}

	public double getCheckingBalance() {
		return checkingBalance;
	}
	public double calcCheckingWithdraw(double amount)
	{
		checkingBalance = (checkingBalance - amount);
		return checkingBalance;
	}
	public double calcSavingWithdraw(double amount)
	{
		savingBalance =(savingBalance - amount);
		return savingBalance;

	}
	public double calcCheckingDeposit(double amount)
	{
		checkingBalance = (checkingBalance + amount);
		return checkingBalance;

	}
	public double calcSavingDeposit(double amount)
	{
		savingBalance = (savingBalance + amount);
		return savingBalance;

	}
	// transferring money from checking to saving
	public void calcCheckTransfer(double amount)
	{
		checkingBalance = checkingBalance - amount;
		savingBalance = savingBalance + amount;
	}
	// transferring money from saving to checking
	public void calcSavingTransfer(double amount)
	{
		savingBalance=savingBalance - amount;
		checkingBalance = checkingBalance + amount;

	}
	public void getCheckingWithdrawInput()
	{
		boolean end = false;
		while(!end) 
		{
			try 
			{
				System.out.println("\nCurrent Checking Account Balance: " + moneyFormat.format(checkingBalance));
				System.out.println("\n Amount you want to withdraw from checking account");
				double amount=input.nextDouble();
				if((checkingBalance - amount)>=0 && amount >=0)
				{
					calcCheckingWithdraw(amount);
					System.out.println("\nCurrent Checking Account Balance: " + moneyFormat.format(checkingBalance));
					end =true;
				}
				else 
				{
					System.out.println("\n Balance Cannot be Negative");

				}


			}
			catch(InputMismatchException ex)
			{
				System.out.println("\n Invalid choice");
				input.next();	
			}
		}

	}
	public void getSavingWithdrawInput()
	{
		boolean end = false;
		while(!end) 
		{
			try 
			{
				System.out.println("\nCurrent Saving Account Balance: " + moneyFormat.format(savingBalance));
				System.out.println("\n Amount you want to withdraw from saving account");
				double amount=input.nextDouble();
				if((savingBalance - amount)>=0 && amount >=0)
				{
					calcSavingWithdraw(amount);
					System.out.println("\nCurrent Saving Account Balance: " + moneyFormat.format(savingBalance));
					end =true;
				}
				else 
				{
					System.out.println("\n Balance Cannot be Negative");

				}


			}
			catch(InputMismatchException ex)
			{
				System.out.println("\n Invalid choice");
				input.next();	
			}
		}

	}

	public void getCheckingDepositInput()
	{
		boolean end = false;
		while(!end) 
		{
			try 
			{
				System.out.println("\nCurrent Checking Account Balance: " + moneyFormat.format(checkingBalance));
				System.out.println("\n Amount you want to deposit from checking account");
				double amount=input.nextDouble();



				if(!(amount <= 0) && (checkingBalance + amount)> 0 && amount >=0)
				{
					calcCheckingDeposit(amount);
					System.out.println("\nCurrent Checking Account Balance: " + moneyFormat.format(checkingBalance));
					end =true;
				}
				else {
					System.out.println("Amount Cannot be Negative or 0");

				}

			}
			catch(InputMismatchException ex)
			{
				System.out.println("\n Invalid choice");
				input.next();	
			}
		}


	}

	public void getSavingDepositInput()
	{
		boolean end = false;
		while(!end) 
		{
			try 
			{
				System.out.println("\nCurrent Saving Account Balance: " + moneyFormat.format(savingBalance));
				System.out.println("\n Amount you want to withdraw from saving account");
				double amount=input.nextDouble();
				if(!(amount <= 0) &&(savingBalance + amount)>0 && amount >0)
				{
					calcSavingDeposit(amount);
					System.out.println("\nCurrent Saving Account Balance: " + moneyFormat.format(savingBalance));
					end =true;
				}
				else 
				{
					System.out.println("\n Cannot Deposit Negative Amount or 0 Amount");

				}


			}
			catch(InputMismatchException ex)
			{
				System.out.println("\n Invalid choice");
				input.next();	
			}
		}

	}

	public void getTransferInput(String accType)
	{
		boolean end = false;

		while(!end)
		{
			try
			{
				if(accType.equals("Checkings"))
				{
					System.out.println("\n Select an account you wish to transfer funds to: ");
					System.out.println("1. savings");
					System.out.println("2. Exit");
					System.out.println("\nChoice:");
					int choice = input.nextInt();
					switch(choice)
					{
					case 1: System.out.println("\nCurrent Checkings Account Balance: " + moneyFormat.format(checkingBalance));
					System.out.println("\nAmount you want to deposit into your Savings Account: ");
					double amount = input.nextDouble();
					if(!((amount <= 0) && (savingBalance + amount) >=0) && (checkingBalance - amount) >0 && amount >0)
					{
						calcCheckTranfer(amount);
						System.out.println("\nCurrent Savings Account Balance: " + moneyFormat.format(savingBalance));
						System.out.println("\nCurrent Checkings Account Balance: " + moneyFormat.format(checkingBalance));
						end = true;
					}
					else 
					{
						System.out.println("\nCannot Transer Negative or 0 amount.");
					}
					break;
					case 2:
						return;
					default:
						System.out.println("\nInvalid Choice");
						break;
					}
				}
				else if(accType.equals("Savings"))
				{
					System.out.println("\nSelect an Account you wish to transfer Funds to:");
					System.out.println("1.Checking");
					System.out.println("2.Exit");
					System.out.println("\n Choice");
					int choice=input.nextInt();
					switch(choice)
					{
					case 1:System.out.println("\n Current Savings Account Balance"+moneyFormat.format(savingBalance));
					System.out.println("\n Amount You want to Deposit into your Saving account");
					double amount=input.nextDouble();
					if(!(amount < 0)&& (checkingBalance + amount)>0 &&(savingBalance - amount)>0 && amount >=0)
					{
						calcSavingTransfer(amount);
						System.out.println("\n Current checking account Balance:"+moneyFormat.format(checkingBalance));
						System.out.println("\n Current savings account Balance:"+moneyFormat.format(savingBalance));
						end=true;
					}
					else
					{
						System.out.println("\n Cannot Transfer Negative or 0 Amount:");
					}
					break;
					case 2: 
						return;
					default:
						System.out.println("\n Invalid Choice>");
						break;
					}
				}
			}
			catch(InputMismatchException ex)
			{
				System.out.println(" \n Invalid Choice");
				input.next();
			}
		}
	}



	private void calcCheckTranfer(double amount) {
		// TODO Auto-generated method stub

	}





}
