package com.csvlogin.user;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class UserService 

{
	private User[] users;
	
	public User readUser(String username, String password)
	
	{
		User user = null;
		
		storeUsers();
		
		if (this.users != null)
		{
			
			for (int count = 0; (count < this.users.length && this.users[count] != null); count++)
				
			{
				if (this.users[count].getUsername().equalsIgnoreCase(username) && this.users[count].getPassword().equals(password))
					
				{
					user = this.users[count];
				}
			}
		}
		
		return user;
	}
	
	private void storeUsers()
	
	{
		this.users = null;
		int count = 0;
		
		count = getUserCount();
		
		if (count > 0)
			
		{
			this.users = new User[count];
			
			
			BufferedReader reader = null;
			
			try 
			{
				reader = new BufferedReader(new FileReader("data.txt"));
				String line = null;
				int i = 0;
				
				while (((line = reader.readLine()) != null) && (i < count))
					
				{
					String[] credentials = line.split(",");
					this.users[i] = new User(credentials[0], credentials[1], credentials[2]);
					i++;
				}
				
			} 
			catch (FileNotFoundException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				if (reader != null)
				{
					try 
					{
						reader.close();
					} 
					catch (IOException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	private int getUserCount()
	
	{
		int count = 0;
		
		BufferedReader reader = null;
		
		try 
		{
			reader = new BufferedReader(new FileReader("data.txt"));
			
			while (reader.readLine() != null)
				
			{
				count++;
			}
			
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if (reader != null)
			{
				try 
				{
					reader.close();
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return count;
	}
}
