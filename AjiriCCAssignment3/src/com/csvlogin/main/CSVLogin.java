package com.csvlogin.main;

import java.util.Scanner;

import com.csvlogin.user.User;
import com.csvlogin.user.UserService;

public class CSVLogin 

{
	
	public static void main(String[] args)
	
	{
		System.out.println("Please login below with your username and password.");
		
		Scanner scanner;
		int attemptCount = 0;
		boolean userFound = false;
		String enteredUsername = null;
		String enteredPassword = null;
		
		UserService service = new UserService();
		User user = null;
		
		
		while ((attemptCount < 5) && (userFound == false))
			
		{
			System.out.println("Enter your username:");
			scanner = new Scanner(System.in);
			enteredUsername = scanner.nextLine();
			System.out.println("Enter your password:");
			enteredPassword = scanner.nextLine();
			
			user = service.readUser(enteredUsername, enteredPassword);
			
			if (user == null)
			{
				System.out.println("Invalid login, please try again.");
			}
			else
			{
				System.out.println("Welcome, " + user.getName());
				userFound = true;
			}
			
			attemptCount++;
			
			if (attemptCount == 5 || userFound == true)
			{
				scanner.close();
			}
		}
		
		
		
		if (attemptCount == 5 && userFound == false)
			
		{
			System.out.println("Too many failed login attempts, you are now locked out.");
		}
		
		
	}

}
