package com.bank;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class OptionMenu 
{
	Scanner menuInput=new Scanner(System.in);
	////each # rep 0-9
	DecimalFormat moneyFormat=new DecimalFormat("'₹'###,##0.00");
	HashMap<Integer,Account> data =new HashMap<Integer,Account>();
	
	
	public void getLogin() throws IOException
	{
		boolean end=false;
		int customerNumber =0;
		int pinNumber=0;
		while(!end)
		{
			try
			{
				System.out.println("\n Enter Your customer Number");
				customerNumber=menuInput.nextInt();
				System.out.println("\n Enter your Pin Number");
				pinNumber=menuInput.nextInt();
				Iterator it = data.entrySet().iterator();
				while(it.hasNext())
				{
					Map.Entry pair=(Map.Entry)it.next();
					Account acc=(Account) pair.getValue();
					if(data.containsKey(customerNumber)&& pinNumber==acc.getPinNumber())
					{
						getAccountType(acc);
						end =true;
						break;

					}


				}
				if(!end)
				{
					System.out.println("\n Wrong Customer Number or Pin Number");
				}

			}
			catch( InputMismatchException ex)

			{
				System.out.println("\n Invalid Character(s).Only Number are Allowed");

			}

		}

	}
	public void getAccountType(Account acc)
	{
		boolean end=false;
		while(!end)
		{
			try {
				System.out.println("\n Select the account you want to access :");
				System.out.println("Type 1- Checking Account");
				System.out.println("Type 2- Savings Account");
				System.out.println("Type 3- Exit");
				System.out.println("\nChoice:");

				int selection=menuInput.nextInt();
				switch(selection) 
				{
				case 1:
					getChecking(acc);
					break;
				case 2:
					getSaving(acc);
					break;
				case 3:
					end=true;
					break;
				default:
					System.out.println("\nInvalid Choice");

				}

			}
			catch(InputMismatchException ex)
			{
				System.out.println("\n Invalid Choice");
				menuInput.next();//TODO handle Exception

			}

		}
	}

	public void getChecking(Account acc) 
	{
		boolean end=false;
		while(!end)
		{
			try
			{
				System.out.println("\nCheckings Account");
				System.out.println("Type 1 - View Balance ");
				System.out.println("Type 2 - Withdraw Funds");
				System.out.println("Type 3 - Deposit Funds");
				System.out.println("Type 4 - Transfer Funds");
				System.out.println("Type 5 - Exit");
				System.out.println("\nChoice: ");

				int selection = menuInput.nextInt();

				switch (selection)
				{
				case 1:
					System.out.println("\n Checking Account Balance :"+moneyFormat.format(acc.getCheckingBalance()));
					break;
				case 2:
					acc.getCheckingWithdrawInput();
					break;
				case 3:
					acc.getCheckingDepositInput();
					break;
				case 4:
					acc.getTransferInput("Checkings");
					break;
				case 5:
					end =true;
					break;
				default:
					System.out.println("\nInvalid Choice:");

				}

			}
			catch(InputMismatchException e)
			{
				System.out.println("\n Invalid Choices");
				menuInput.next();


			}

		}
	}


	public void getSaving(Account acc) 
	{
		boolean end=false;
		while(!end)
		{
			try
			{
				System.out.println("\nSaving Account");
				System.out.println("Type 1 - View Balance ");
				System.out.println("Type 2 - Withdraw Funds");
				System.out.println("Type 3 - Deposit Funds");
				System.out.println("Type 4 - Transfer Funds");
				System.out.println("Type 5 - Exit");
				System.out.println("\nChoice: ");

				int selection = menuInput.nextInt();

				switch (selection)
				{
				case 1:
					System.out.println("\n Savings Account Balance :"+ moneyFormat.format(acc.getSavingBalance()));
					break;
				case 2:
					acc.getSavingWithdrawInput();
					break;
				case 3:
					acc.getSavingDepositInput();
					break;
				case 4:
					acc.getTransferInput("Savings");
					break;
				case 5:
					end =true;
					break;
				default:
					System.out.println("\nInvalid Choice:");

				}

			}
			catch(InputMismatchException e)
			{
				System.out.println("\n Invalid Choices");
				menuInput.next();


			}

		}
	}
	public void createAccount() throws IOException
	{
		int cst_no=0;
		boolean end =false;
		while(!end)
		{
			try 
			{
				System.out.println("\n Enter your Customer Number");
				cst_no =menuInput.nextInt();
				Iterator it=data.entrySet().iterator();
				while(it.hasNext())
				{
					Map.Entry pair = (Map.Entry) it.next();
					if(!data.containsKey(cst_no))
					{
						end =true;

					}

				}
				if(!end) 
				{
					System.out.println("\n This Customer Number is Already Registered");


				}

			}
			catch(InputMismatchException ex)
			{
				System.out.println("\n Invalid Choice");
				menuInput.next();

			}

		}
		System.out.println("\n Enter Pin To Be Registered");
		int pin = menuInput.nextInt();
		data.put(cst_no,new Account(cst_no,pin));
		System.out.println("\n your New Account Has Succesfully Registered");
		System.out.println("\n Redirecting to login ..............");
		getLogin();
	}
	public void mainMenu() throws IOException
	{
		data.put(952178,new Account(952141,191904,1000,5000));
		data.put(952178,new Account(123,123,20000,50000));
		boolean end=false;
		while(!end)
		{
			try {
				System.out.println("\n Type-1 Login");
				System.out.println("\n Type-2 Create Account");
				System.out.println("\n  Choices:");
				int choice=menuInput.nextInt();
				switch(choice)
				{
				case 1:
					getLogin();
					end =true;
					break;
				case 2:
					createAccount();
					end =true;
					break;
				default:
					System.out.println("\nInvalid Choices");

				}

			}
			catch(InputMismatchException ex)
			{
				System.out.println("\nInvalid Choice");
				menuInput.next();

			}
		}

		System.out.println("\n Thankyou for Using This ATM.\n");
		menuInput.close();
		System.exit(0);

	}

}
